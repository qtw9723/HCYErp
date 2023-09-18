package manageEmp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.EmpVO;

public class ManageEmpDAO {

	private static ManageEmpDAO manageEmpDAO;

	private ManageEmpDAO() {
	}// constructor

	public static ManageEmpDAO getInstance() {
		if (manageEmpDAO == null) {
			manageEmpDAO = new ManageEmpDAO();
		}

		return manageEmpDAO;
	}// getInstance

	public List<String> selectDept() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select deptno,dname from dept union all select 0,null from dual union all select teamno,tname from team union all select 0,null from dual union all select empno,ename from emp";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("deptno")+"/"+rs.getString("dname"));
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try
		return list;
	}// selectDept


	public List<String> searchTeam(String dept) throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(dept);
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select t.tname,e.ename from team t,dept d,emp e where (t.deptno=d.deptno(+) and e.teamno(+)=t.teamno) and dname=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dept);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString("ename") != null) {
					list.add(rs.getString("tname") + "/" + rs.getString("ename"));
				} else {
					list.add(rs.getString("tname") + "/");
				} // if
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try
		return list;
	}// searchTeam

	public List<String> searchEmp(String team) throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select ename from emp e,team t where e.teamno=t.teamno(+) and tname=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, team);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ename"));
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;
	}// searchEmp

	public int updateEmpInfo(EmpVO eVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rowCnt = 0;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "update emp set TEAMNO=(select teamno from team where tname=?), JOBNO=(select jobno from job where jobname=?), LEVELNO=(select levelno from joblevel where lvname=?), SAL=?, JOBTEL=?, ENAME=?, EMAIL=?, ADDR=?, SSN=?, TEL=?, PASS=?, TOTALDAYOFF=? where empno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, eVO.getTeam());
			pstmt.setString(2, eVO.getJob());
			pstmt.setString(3, eVO.getLevel());
			pstmt.setInt(4, eVO.getSal());
			pstmt.setString(5, eVO.getJobTel());
			pstmt.setString(6, eVO.getEname());
			pstmt.setString(7, eVO.getEmail());
			pstmt.setString(8, eVO.getAddr());
			pstmt.setString(9, eVO.getSsn());
			pstmt.setString(10, eVO.getTel());
			pstmt.setString(11, eVO.getPass());
			pstmt.setInt(12, eVO.getTotalDayOff());
			pstmt.setInt(13, eVO.getEmpNo());

			rowCnt = pstmt.executeUpdate();
		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return rowCnt;

	}//updateEmpInfo
	
	public int updateEmpModifyInfo(EmpVO eVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rowCnt = 0;
		

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "update emp set TEAMNO=(select teamno from team where tname=?), JOBNO=(select jobno from job where jobname=?), LEVELNO=(select levelno from joblevel where lvname=?), SAL=?, ENAME=?, EMAIL=?, TEL=? where empno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, eVO.getTeam());
			pstmt.setString(2, eVO.getJob());
			pstmt.setString(3, eVO.getLevel());
			pstmt.setInt(4, eVO.getSal());
			pstmt.setString(5, eVO.getEname());
			pstmt.setString(6, eVO.getEmail());
			pstmt.setString(7, eVO.getTel());
			pstmt.setInt(8, eVO.getEmpNo()); 

			rowCnt = pstmt.executeUpdate();
		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return rowCnt;

	}//updateEmpModifyInfo

	public int selectTeamName(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int teamno = 0;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "SELECT teamno FROM EMP where empno=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			rs.next();
			teamno = rs.getInt("teamno");
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			} // if
		} // finally
		return teamno;
	}// selectTeamName
	
	//사원정보 들고오려고
	 public EmpVO getEmpDetails(int empno) throws SQLException {
	        EmpVO empVO = null;
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        DbConn db = DbConn.getInstance();

	        try {
	            con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

	            String sql ="SELECT e.ENAME,j.JOBNAME ,l.levelno ,e.TEL ,e.EMAIL,d.DNAME,t.TNAME,t.loc,e.SAL FROM emp e,job j,dept d,team t,joblevel l WHERE e.JOBNO = j.JOBNO(+)AND t.DEPTNO = d.DEPTNO(+)AND e.TEAMNO = t.TEAMNO(+)AND e.levelno = l.levelno(+) AND empno=?";

	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, empno);

	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                empVO = new EmpVO();
	                empVO.setEname(rs.getString("ename"));
	                empVO.setLevel(rs.getString("levelno"));
	                empVO.setTel(rs.getString("tel"));
	                empVO.setEmail(rs.getString("email"));
	                empVO.setJob(rs.getString("JOBNAME"));
	                empVO.setDept(rs.getString("DNAME"));
	                empVO.setTeam(rs.getString("TNAME"));
	                empVO.setDeptLoc(rs.getString("loc"));
	                empVO.setSal(rs.getInt("sal"));
	            }
	        } finally {
	            if (db != null) {
	                db.dbclose(rs, pstmt, con);
	            }
	        }
	        return empVO;
	    }

}// class