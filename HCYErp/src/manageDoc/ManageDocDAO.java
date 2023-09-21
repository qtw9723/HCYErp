package manageDoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.DocPermissionVO;
import VO.DocVO;

public class ManageDocDAO {

	private static ManageDocDAO mdDAO;

	private ManageDocDAO() {

	}// constructor

	public static ManageDocDAO getInstance() {
		if (mdDAO == null) {
			mdDAO = new ManageDocDAO();
		} // if
		return mdDAO;
	}// getInstance

	// 업로드시 이름 중복 검사용
	public List<String> selectDoc() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select docname from doc";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("docname"));
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;

	}// selectDoc

	// 열람 문서 목록 조회용
	public List<DocVO> selectDoc(int empno) throws SQLException {
		List<DocVO> list = new ArrayList<DocVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select dc.deptno,dc.docno,dc.docname, dc.INPUT_DATE from doc dc where dc.docno in (select dp.docno from DOC_PERMISSION dp where dp.deptno = case  when ((select t.deptno from team t where t.teamno = (select e.teamno from emp e where e.empno = ? ))) = 90 then dp.deptno else ((select t.deptno from team t where t.teamno = (select e.teamno from emp e where e.empno = ? ))) end )";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);
			pstmt.setInt(2, empno);

			rs = pstmt.executeQuery();
			DocVO dVO = null;
			while (rs.next()) {
				dVO = new DocVO();
				dVO.setDocNo(rs.getInt("docno"));
				dVO.setDeptNo(rs.getInt("deptno"));
				dVO.setDocName(rs.getString("docname"));
				dVO.setInputDate(rs.getDate("input_date"));
				list.add(dVO);
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;

	}// selectDoc

	public void insertDoc(DocVO dVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			con.setAutoCommit(false);

			// doc 테이블에 인설트
			String sql = "insert into doc(deptno,docname) values((SELECT t.DEPTNO from TEAM t where t.TEAMNO=(select e.TEAMNO from emp e where e.empno= ? )),?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dVO.getDeptNo());
			pstmt.setString(2, dVO.getDocName());

			pstmt.execute();
			pstmt.close();

			// 권한 테이블 인설트
			sql = "insert into DOC_PERMISSION(DEPTNO, DOCNO) values((SELECT t.DEPTNO from TEAM t where t.TEAMNO=(select e.TEAMNO from emp e where e.empno= ? )),(select docno from DOC where DOCNAME = ? ))";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dVO.getDeptNo());
			pstmt.setString(2, dVO.getDocName());

			pstmt.execute();

		} finally {
			db.dbclose(null, pstmt, con);
		} // try
	}// insertDoc

	public void deleteDoc(int docNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "delete from doc where docno = ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, docNo);

			pstmt.execute();

		} finally {
			db.dbclose(null, pstmt, con);
		} // try

	}// deleteDoc

	public String selectDept(String docName) throws SQLException {
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select d.dname from doc dc, dept d where dc.deptno = d.deptno(+) and dc.docname= ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, docName);

			rs = pstmt.executeQuery();

			rs.next();

			result = rs.getString("dname");
		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return result;

	}// selectDept

	public void insertDocPermission(List<DocPermissionVO> dpVOList) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			con.setAutoCommit(false);
			// 권한 테이블 인설트
			String sql = "insert into DOC_PERMISSION(DEPTNO, DOCNO) values((SELECT DEPTNO from dept where DNAME = ? ), ? )";

			for (DocPermissionVO dpVO : dpVOList) {
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, dpVO.getdeptName());
				pstmt.setInt(2, dpVO.getDocNo());

				pstmt.execute();

				pstmt.close();
				con.commit();
			} // for
		} finally {
			db.dbclose(null, pstmt, con);
		} // try
	}// insertDocPermission

	public void deleteDocPermission(List<Integer> docNoList) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			con.setAutoCommit(false);
			// 권한 테이블 인설트
			String sql = "DELETE FROM DOC_PERMISSION WHERE DOCNO = ? ";

			for (int docNo : docNoList) {
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, docNo);

				pstmt.execute();

				pstmt.close();
			} // for
			con.commit();
		} finally {
			db.dbclose(null, pstmt, con);
		} // try
	}// insertDocPermission
}// class
