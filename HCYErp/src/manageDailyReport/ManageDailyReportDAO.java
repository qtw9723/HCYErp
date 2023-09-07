package manageDailyReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DbConn;
import VO.DailyReportVO;

public class ManageDailyReportDAO {

	private static ManageDailyReportDAO mdrDAO;
	
	private ManageDailyReportDAO() {
		
	}//constructor
	
	public static ManageDailyReportDAO getInstance() {
		
		if(mdrDAO==null) {
			mdrDAO=new ManageDailyReportDAO();
		}//if
		return mdrDAO;
	}//getInstance
	
	public List<DailyReportVO> selectDailyReport(String days) throws SQLException{
		
		List<DailyReportVO> list=new ArrayList<DailyReportVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql="select dr.empno,dr.reportdate,dr.reportcontent,e.ename from daily_report dr, emp e where (dr.empno=e.empno) and dr.reportdate=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, days);
			rs = pstmt.executeQuery();
			DailyReportVO drVO=null;
			while(rs.next()) {
				drVO=new DailyReportVO(rs.getInt("empno"),rs.getString("reportdate"),rs.getString("reportcontent"),rs.getString("ename"));
				
				list.add(drVO);
			}//while
			
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
		return list;
		
	}//selectDailyReport
	
	public List<DailyReportVO> selectDailyReport(int empno) throws SQLException{
		
		List<DailyReportVO> list=new ArrayList<DailyReportVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();

		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql="select dr.empno,dr.reportdate,dr.reportcontent,e.ename from daily_report dr, emp e where (dr.empno=e.empno) and dr.empno=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			DailyReportVO drVO=null;
			while(rs.next()) {
				drVO=new DailyReportVO(rs.getInt("empno"),rs.getString("reportdate"),rs.getString("reportcontent"),rs.getString("ename"));
				
				list.add(drVO);
			}//while
			
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
		return list;
		
	}//selectDailyReport
	
	public int updateDailyReport(DailyReportVO drVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int rowCnt=0;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql="update daily_report set reportcontent=? where empno=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, drVO.getReportContent());
			pstmt.setInt(2, drVO.getEmpNo());
			rowCnt = pstmt.executeUpdate();
		} finally {
			if (db != null) {
				db.dbclose(rs, pstmt, con);
			}
		} // try
		return rowCnt;
	}//updateDailyReport
	
}//class
