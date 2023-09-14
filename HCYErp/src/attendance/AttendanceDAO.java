
package attendance;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import DB.DbConn;
import VO.DayOffApplyVO;
import VO.EmpVO;

public class AttendanceDAO {
	private static AttendanceDAO attendanceDAO;
	public static int WORK_START_TIME = 9;

	private AttendanceDAO() {

	}// constructor

	public static AttendanceDAO getInstance() {
		if (attendanceDAO == null) {
			attendanceDAO = new AttendanceDAO();
		} // end if
		return attendanceDAO;
	}// getInstance

	public Map<Integer, AttendanceStatus> selectPersonalAttendance(int empno) throws SQLException {
		Map<Integer, AttendanceStatus> attendance = new HashMap<Integer, AttendanceStatus>();
		DbConn db = DbConn.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			// 휴가일 검색
			String selectPersonalAttendance = "SELECT to_date(STARTDATE,'yyyy-mm-dd'), DAYOFFDAYS FROM DAYOFF_APPLY where empno = ? and APPROVE = 'Y'";

			pstmt = con.prepareStatement(selectPersonalAttendance);
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			java.util.Date tempDate = null;
			Calendar targetCal = Calendar.getInstance();
			Calendar currentCal = Calendar.getInstance();
			while (rs.next()) {
				tempDate = new java.util.Date(rs.getDate(1).getTime());
				targetCal.setTime(tempDate);
				if (targetCal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR)
						&& targetCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH)) {
					for (int i = 0; i < rs.getInt(2); i++) {
						System.out.println(targetCal.get(Calendar.DAY_OF_MONTH)+"/"+rs.getInt(2));
						attendance.put(targetCal.get(Calendar.DAY_OF_MONTH), AttendanceStatus.DAY_OFF);
						targetCal.set(Calendar.DAY_OF_MONTH, targetCal.get(Calendar.DAY_OF_MONTH)+1);
						if(targetCal.get(Calendar.DAY_OF_MONTH) == 1) {break;}
					} // for
				} // if
			} // while

			// 출근일 및 지각일 검색
			rs.close();
			pstmt.close();

			selectPersonalAttendance = "select to_date(starttime,'hh:mi:ss'),workdate from ATTENDANCE where empno = ? ";

			pstmt = con.prepareStatement(selectPersonalAttendance);
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tempDate = new java.util.Date(rs.getDate(1).getTime());
				targetCal.setTime(tempDate);
				if (targetCal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR)
						&& targetCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH)) {
					if (targetCal.get(Calendar.HOUR_OF_DAY) < WORK_START_TIME) {
						tempDate = new java.util.Date(rs.getDate(2).getTime());
						targetCal.setTime(tempDate);
						attendance.put(targetCal.get(Calendar.DAY_OF_MONTH), AttendanceStatus.ATTENDANCE);
					} else {
						tempDate = new java.util.Date(rs.getDate(2).getTime());
						targetCal.setTime(tempDate);
						attendance.put(targetCal.get(Calendar.DAY_OF_MONTH), AttendanceStatus.ABSENCE);
					} // else
				} // if
			} // while

			// 휴직일 검색
			rs.close();
			pstmt.close();

			selectPersonalAttendance = "SELECT STARTDATE, ABSENCEDAYS FROM ABSENCE where empno = ? ";

			pstmt = con.prepareStatement(selectPersonalAttendance);
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tempDate = new java.util.Date(rs.getDate(1).getTime());
				targetCal.setTime(tempDate);
				if (targetCal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR)
						&& targetCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH)) {
				for (int i = 0; i < rs.getInt(2); i++) {
						attendance.put(targetCal.get(Calendar.DAY_OF_MONTH), AttendanceStatus.LEAVE);
						targetCal.set(Calendar.DAY_OF_MONTH, targetCal.get(Calendar.DAY_OF_MONTH) + 1);
					} // for
				} //if
			} // while
		} finally {
			db.dbclose(rs, pstmt, con);
		} // finally

		return attendance;
	}// selectPersonalAttendance

	public int[] selectLeftDayOff(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int[] days = new int[2];

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "select nvl((select sum(d.dayoffdays) days from DAYOFF_APPLY d where d.approve='Y' and d.empno = e.empno and to_char(to_date(startdate,'yyyy-mm-dd'),'yyyy') = to_char(sysdate,'yyyy') group by d.empno),0) days, totaldayoff totaldays from emp e where e.empno = ? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

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

	public void insertAttendance(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "insert into attendance(empno,starttime,workdate) values(?,to_char(sysdate,'hh:mi:ss'),to_char(sysdate,'yyyy-mm-dd'))";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			} // if
		} // try
	}// insertAttendance

	public EmpVO selectEmp(int empno) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			String sql = "select e.empno empno,e.addr addr,e.email email,e.ename ename,to_char(e.hiredate,'yyyy-mm-dd') hiredate,e.input_date input_date, "
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
				eVO.setHiredate(rs.getString("hiredate"));
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

			String sql = "update attendance set endtime=to_char(sysdate,'hh:mi:ss') where empno= ? and endtime is null";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);

			flag = pstmt.executeUpdate();

		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			} // if
		} // finally
		return flag;
	}// updateGetOff

	public void insertDayOffApply(DayOffApplyVO doaVO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "insert into dayoff_apply(empno, startdate, enddate, dayoffdays,reason,submitdate) values(?,?,?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, doaVO.getEmpNo());
			pstmt.setString(2, doaVO.getStartDate());
			pstmt.setString(3, doaVO.getEndDate());
			pstmt.setInt(4, doaVO.getDayOffDays());
			pstmt.setString(5, doaVO.getReason());

			pstmt.execute();
		} finally {
			if (db != null) {
				db.dbclose(null, pstmt, con);
			}
		} // try
	}// insertDayOffApply

	public int updateChangePass(EmpVO eVO) throws SQLException {
		int rowCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "update emp set pass=? where empno=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, eVO.getPass());
			pstmt.setInt(2, eVO.getEmpNo());
			rowCnt = pstmt.executeUpdate();
		} finally {
			if (db != null) {
				db.dbclose(null, pstmt, con);
			} // if

		} // try
		return rowCnt;
	}// updateChangePass
	
	public boolean selectWorkedFlag(int empno) throws SQLException {
		boolean flag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql = "SELECT 1 FROM ATTENDANCE WHERE EMPNO = ? and ENDTIME IS NULL";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}//if
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			} // if
		} // try
		
		return flag;
	}//selectWorkedFlag
	
	public boolean selectWorkingFlag(int empno) throws SQLException {
		boolean flag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "SELECT 1 FROM ATTENDANCE WHERE EMPNO = ? and ENDTIME IS NULL and to_char(STARTTIME,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd')";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}//if
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			} // if
		} // try
		
		return flag;
	}//selectWorkingFlag
	
	public boolean selectTodayWork(int empno) throws SQLException {
		boolean flag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "SELECT 1 FROM ATTENDANCE WHERE EMPNO = ? and WORKDATE = to_char(sysdate,'yyyy-mm-dd')";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}//if
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			} // if
		} // try
		
		return flag;
	}//selectWorkingFlag
}// class



