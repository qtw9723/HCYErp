package manageAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.AttendanceVO;

public class ManageAttendanceDAO {

	private static ManageAttendanceDAO maDAO;
	
	private ManageAttendanceDAO() { 
		
	}//Constructor
	
	public static ManageAttendanceDAO getInstance() {
		if(maDAO==null) {
			maDAO=new ManageAttendanceDAO();
		}
		return maDAO;
	}//getInstance
	
	
	public List<AttendanceVO> selectNoAttend(String days) throws SQLException{
		List<AttendanceVO> list=new ArrayList<AttendanceVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select e.empno,e.ename,at.starttime,at.endtime from emp e,attendance at where (at.empno=e.empno) and at.workdate=?";
			
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
				list.add(atVO);
			}//while
			
		}finally {
			db.dbclose(rs, pstmt, con);
		}//try
		return list;
		
	}//selectNoAttend
	
	public List<AttendanceVO> selectNoAttend(int empno) throws SQLException{
		List<AttendanceVO> list=new ArrayList<AttendanceVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			
			con=db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");
			
			String sql="select e.empno,e.ename,at.starttime,at.endtime,at.workdate from emp e,attendance at where (at.empno=e.empno) and e.empno=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, empno);
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
}//class
