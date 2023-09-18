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

			String sql = "select dname from dept";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dname"));
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try
		return list;
	}// selectDept

	public List<String> selectTeam() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select tname from team";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("tname"));
			} // while

		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;
	}// selectTeam

	public List<String> selectEmp() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select ename,empno from emp";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ename")+"/"+rs.getInt("empno"));
			}
		} finally {
			db.dbclose(rs, pstmt, con);
		} // try

		return list;
	}// selectEmp

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
	 public EmpVO getEmpDetails(String empName) throws SQLException {
	        EmpVO empVO = null;
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        DbConn db = DbConn.getInstance();

	        try {
	            con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

	            String sql = "SELECT ename, level, tel, email,jobno, dept, team, job, loc, sal FROM emp WHERE ename = ?";

	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, empName);

	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                empVO = new EmpVO();
	                empVO.setEname(rs.getString("ename"));
	                empVO.setLevel(rs.getString("level"));
	                empVO.setTel(rs.getString("tel"));
	                empVO.setEmail(rs.getString("email"));
	                empVO.setDept(rs.getString("dept"));
	                empVO.setTeam(rs.getString("team"));
	                empVO.setJob(rs.getString("job"));
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
