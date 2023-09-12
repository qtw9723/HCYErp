package dailyReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DbConn;
import VO.DailyReportVO;

public class DailyReportDAO {
	private static DailyReportDAO dailyReportDAO;
	
	private DailyReportDAO() {
	}//constructor
	
	public static DailyReportDAO getInstance() {
		
		if(dailyReportDAO==null) {
			dailyReportDAO=new DailyReportDAO();
		}//end if
		
		return dailyReportDAO;
	}//getInstance
	
	public void insertDailyReport( DailyReportVO drVO ) throws SQLException {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConn db=DbConn.getInstance();
		
		try {
			con = db.getConnection("192.168.10.145", "hcytravel", "boramsangjo");

			String sql = "insert into daily_report(empno, reportcontent) values(?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, drVO.getEmpNo());
			pstmt.setString(2, drVO.getReportContent());
						
			pstmt.execute( );
			
		} finally {
			if (db != null) {
				db.dbclose(null, pstmt, con);
			}//end if
		}//end try
		
	}//insertDailyReport
	
}//class
