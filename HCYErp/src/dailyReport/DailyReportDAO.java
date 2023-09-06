package dailyReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
//	
//	public void insertDailyReport( DailyReportVO dRVO ) {
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		
//		
//	}//insertDailyReport
	
}//class
