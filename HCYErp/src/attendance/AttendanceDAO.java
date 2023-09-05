package attendance;

public class AttendanceDAO {
	private static AttendanceDAO attendanceDAO; 
	
	private AttendanceDAO() {
		
	}//constructor
	
	public static AttendanceDAO getInstance() {
		if(attendanceDAO==null) {
			attendanceDAO=new AttendanceDAO();
		}//end if
		return attendanceDAO;
	}//getInstance
	
}//class
