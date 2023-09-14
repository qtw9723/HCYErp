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

	public List<DocVO> selectDoc(int empno) throws SQLException {
		List<DocVO> list = new ArrayList<DocVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select e.empno,t.teamno ,dp.deptno,dc.docno,dc.docname, dc.INPUT_DATE from emp e,team t,dept dp,doc dc where (e.teamno=t.teamno(+) and t.deptno=dp.deptno(+) and dc.deptno=dp.deptno(+)) and e.empno=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			DocVO dVO = null;
			while (rs.next()) {
				dVO = new DocVO();
				dVO.setDocNo(rs.getInt("docno"));
				dVO.setDeptNo(rs.getInt("deptno"));
				dVO.setDocName(rs.getString("docname"));
				dVO.setInputDate(rs.getDate("input_date"));
				System.out.println(dVO.getDocNo());
				list.add(dVO);
			}//while

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

			String sql = "insert into doc(deptno,docname) values((SELECT t.DEPTNO from TEAM t where t.TEAMNO=(select e.TEAMNO from emp e where e.empno= ? )),?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dVO.getDeptNo());
			pstmt.setString(2, dVO.getDocName());

			pstmt.execute();
		} finally {
			db.dbclose(null, pstmt, con);
		} // try
	}// insertDoc

	public void deleteDoc(List<Integer> docNoList) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "delete from doc where docno in ( ? ) ";

			pstmt = con.prepareStatement(sql);
			
			for(int i=1;i<docNoList.size();i++) {
				pstmt.setInt(i, docNoList.get(i));
			}//for
			
			pstmt.execute();

		} finally {
			db.dbclose(null, pstmt, con);
		} // try

	}// deleteDoc

	public List<String> selectDept() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select dname from dept";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dname"));
			}

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;

	}// selectDept

	public void insertDocPermission(DocPermissionVO dpVO) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rowCnt=0;
		
		DbConn db = DbConn.getInstance();
		for (int deptNo : dpVO.getDeptNo()) {
			try {
				con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

				String sql = "insert into doc_permission(docno,deptno) values(?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, dpVO.getDocNo());
				pstmt.setInt(2, deptNo);
				
				pstmt.executeQuery();
			} finally {
				db.dbclose(rs, pstmt, con);
			} // try
		}//for

	}// insertDocPermission
}// class
