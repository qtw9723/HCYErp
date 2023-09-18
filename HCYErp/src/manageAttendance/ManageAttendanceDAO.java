package manageAttendance;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DbConn;
import VO.AttendanceVO;
import attendance.AttendanceStatus;

public class ManageAttendanceDAO {

	private static ManageAttendanceDAO maDAO;
	private static int WORK_START_TIME = 9;
	
	private ManageAttendanceDAO() { 
	}//Constructor
	
	public static ManageAttendanceDAO getInstance() {
		if(maDAO==null) {
			maDAO=new ManageAttendanceDAO();
		}//if
		return maDAO;
	}//getInstance
	
	
	public List<AttendanceVO> selectNoAttendBoss(String days) throws SQLException{
		List<AttendanceVO> list=new ArrayList<AttendanceVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select e.empno,e.ename,at.starttime,at.endtime, at.workdate from emp e,attendance at where at.empno=e.empno and substr(at.workdate,1,7) = ? ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, days);
			rs=pstmt.executeQuery();
			AttendanceVO atVO=null;
			while(rs.next()) {
				atVO=new AttendanceVO();
				atVO.setEmpNo(rs.getInt("empno"));
				atVO.setEname(rs.getString("ename"));
				atVO.setStartTime(rs.getString("starttime"));
				atVO.setEndTime(rs.getString("endtime"));
				atVO.setWorkDate(rs.getString("workdate"));
				list.add(atVO);
			}//while
			
		}finally {
			db.dbclose(rs, pstmt, con);
		}//try
		return list;
		
	}//selectNoAttend
	public List<AttendanceVO> selectNoAttend(String days,int empno) throws SQLException{
		List<AttendanceVO> list=new ArrayList<AttendanceVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select e.empno,e.ename,at.starttime,at.endtime, at.workdate from emp e,attendance at where at.empno=e.empno and substr(at.workdate,1,7) = ? and e.teamno=(select teamno from emp where empno=?) and e.levelno>=(select levelno from emp where empno=?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, days);
			pstmt.setInt(2, empno);
			pstmt.setInt(3, empno);
			rs=pstmt.executeQuery();
			AttendanceVO atVO=null;
			while(rs.next()) {
				atVO=new AttendanceVO();
				atVO.setEmpNo(rs.getInt("empno"));
				atVO.setEname(rs.getString("ename"));
				atVO.setStartTime(rs.getString("starttime"));
				atVO.setEndTime(rs.getString("endtime"));
				atVO.setWorkDate(rs.getString("workdate"));
				list.add(atVO);
			}//while
			
		}finally {
			db.dbclose(rs, pstmt, con);
		}//try
		return list;
		
	}//selectNoAttend
	
	public Map<Integer, AttendanceStatus> selectNoAttend(int empno) throws SQLException {
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
						attendance.put(targetCal.get(Calendar.DAY_OF_MONTH), AttendanceStatus.DAY_OFF);
					} // for
				} // if
			} // while

			// 출근일 및 지각일 검색
			rs.close();
			pstmt.close();

			selectPersonalAttendance = "select to_date(starttime,'hh:mi:ss'),workdate from ATTENDANCE where empno = ? and ENDTIME is not null";

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
				for (int i = 0; i < rs.getInt(2); i++) {
					if (targetCal.get(Calendar.YEAR) == currentCal.get(Calendar.YEAR)
							&& targetCal.get(Calendar.MONTH) == currentCal.get(Calendar.MONTH)) {
						attendance.put(targetCal.get(Calendar.DAY_OF_MONTH), AttendanceStatus.LEAVE);
						targetCal.set(Calendar.DAY_OF_MONTH, targetCal.get(Calendar.DAY_OF_MONTH) + 1);
					} else {
						break;
					} // else
				} // for
			} // while
		} finally {
			db.dbclose(rs, pstmt, con);
		} // finally

		return attendance;
	}// selectPersonalAttendance
	
	public List<String> selectEmpBoss() throws SQLException{
		List<String> list = new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="SELECT EMPNO, ENAME FROM EMP ";
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			StringBuilder sbEmp = new StringBuilder();
			while(rs.next()) {
				sbEmp.replace(0, sbEmp.length(), "");
				sbEmp.append(rs.getInt("empno")).append("/").append(rs.getString("ename"));
				list.add(sbEmp.toString());
			}//while
			
		}finally {
			db.dbclose(rs, pstmt, con);
		}//try
		
		return list;
	}//selectEmp
	public List<String> selectEmp(int empno) throws SQLException{
		List<String> list = new ArrayList<String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="SELECT EMPNO, ENAME FROM EMP where teamno=(select teamno from emp where empno=?) and levelno>=(select levelno from emp where empno=?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			pstmt.setInt(2, empno);
			rs=pstmt.executeQuery();
			StringBuilder sbEmp = new StringBuilder();
			while(rs.next()) {
				sbEmp.replace(0, sbEmp.length(), "");
				sbEmp.append(rs.getInt("empno")).append("/").append(rs.getString("ename"));
				list.add(sbEmp.toString());
			}//while
			
		}finally {
			db.dbclose(rs, pstmt, con);
		}//try
		
		return list;
	}//selectEmp
	
	public int selectTeamNo(int empno) throws SQLException {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		int teamNo=0;
		
		try {
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select teamno from emp where empno=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs=pstmt.executeQuery();
			rs.next();
			teamNo=rs.getInt("teamno");
		}finally {
			db.dbclose(rs, pstmt, con);
		}//try
		
		return teamNo;
		
	}//selectTeamNo
}//class
