package attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DB.DbConn;
import VO.AttendanceVO;
import VO.DayOffApplyVO;
import VO.EmpVO;

public class AttendanceDAO {
	private static AttendanceDAO attendanceDAO;

	private AttendanceDAO() {

	}// constructor

	public static AttendanceDAO getInstance() {
		if (attendanceDAO == null) {
			attendanceDAO = new AttendanceDAO();
		} // end if
		return attendanceDAO;
	}// getInstance

	public List<String> selectPersonalAttendance(int empno) {

		return null;
	}// selectPersonalAttendance

	public int[] selectLeftDayOff(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int[] days = new int[2];

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select (select totaldayoff from emp where empno = ? )-dayoffdays days,(select totaldayoff from emp where empno = ? ) totaldays from dayoff_apply where empno = ? and approve='Y'";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);
			pstmt.setInt(2, empno);
			pstmt.setInt(3, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				days[0] = rs.getInt("days");
				days[1] = rs.getInt("totaldays");
			} // while

		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
		return days;

	}// selectLeftDayOff

	public void insertAttendance(AttendanceVO adVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "insert into attendance(empno,starttime,endtime,workdate) values(?,to_char(sysdate,'hh:mi:ss'),null,to_char(sysdate,'yyyy-mm-dd'))";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, adVO.getEmpNo());

			rs = pstmt.executeQuery();

		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
	}// insertAttendance

	public EmpVO selectEmp(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String sql = "select e.empno empno,e.addr addr,e.email email,e.ename ename,e.hiredate hiredate,e.input_date input_date, "
					+ "e.jobno jobno,e.levelno levelno,e.pass pass,e.sal sal "
					+ ",e.ssn ssn,e.teamno teamno,e.tel tel,e.totaldayoff totaldayoff,e.jobtel jobtel,t.tname tname, "
					+ "t.deptno deptno,d.dname dname,j.jobname jobname,t.loc loc,jl.lvname lvname "
					+ "from emp e,team t,dept d,job j,joblevel jl "
					+ "where (e.teamno=t.teamno(+) and t.deptno=d.deptno(+) and e.jobno=j.jobno(+) and e.levelno=jl.levelno(+)) "
					+ "and empno=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpVO eVO = new EmpVO();
				eVO.setEmpNo(rs.getInt("empno"));
				eVO.setAddr(rs.getString("addr"));
				eVO.setDept(rs.getString("dname"));
				eVO.setDeptLoc(rs.getString("loc"));
				eVO.setEmail(rs.getString("email"));
				eVO.setEname(rs.getString("ename"));
				eVO.setHiredate(rs.getDate("hiredate"));
				eVO.setInputDate(rs.getDate("input_date"));
				eVO.setJob(rs.getString("jobname"));
				eVO.setLevel(rs.getString("lvname"));
				eVO.setPass(rs.getString("pass"));
				eVO.setSal(rs.getInt("sal"));
				eVO.setSsn(rs.getString("ssn"));
				eVO.setTeam(rs.getString("tname"));
				eVO.setTel(rs.getString("tel"));
				eVO.setTotalDayOff(rs.getInt("totaldayoff"));
			} // while
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
		return null;
	}// selectEmp

	public int updateGetOff(int empno) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 0;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "update attendance set endtime=to_char(sysdate,'hh:mi:ss') where empno=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

			flag = pstmt.executeUpdate();

		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
		return flag;
	}// updateGetOff

	public void insertDayOffApply(DayOffApplyVO doaVO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		java.util.Date utilDate = doaVO.getSubmitDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "insert into dayoff_apply(empno, startdate, endate, dayoffdays,reason,approve,submitdate) values(?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, doaVO.getEmpNo());
			pstmt.setString(2, doaVO.getStartDate());
			pstmt.setString(3, doaVO.getEndDate());
			pstmt.setInt(4, doaVO.getDayOffDays());
			pstmt.setString(5, doaVO.getReason());
			pstmt.setString(6, doaVO.getApprove());
			pstmt.setDate(7, sqlDate);
			// new java.sql.Date(utilDate.getTime());
			
			pstmt.execute();
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
	}// insertDayOffApply

	public int updateChangePass(EmpVO eVO) throws SQLException {
		int rowCnt=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "update emp set pass=? where empno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, eVO.getPass());
			pstmt.setInt(1, eVO.getEmpNo());
			rowCnt=pstmt.executeUpdate();
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}//if
			

		}//try
		return rowCnt;
	}//updateChangePass
}// class
