package VO;

public class AttendanceVO {
	private int empNo;
	private String startTime;
	private String endTime;
	private String workDate;
	
	public AttendanceVO() {
		
	}//constructor
	
	public AttendanceVO(int empNo, String startTime, String endTime, String workDate) {
		this.empNo = empNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workDate = workDate;
	}//constructor
	
	//getter
	public int getEmpNo() {
		return empNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getWorkDate() {
		return workDate;
	}
	
	//setter
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	
	
	
}//class
