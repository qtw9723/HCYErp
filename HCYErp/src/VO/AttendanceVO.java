package VO;

public class AttendanceVO {
	private int empNo;
	private String ename;
	private String startTime;
	private String endTime;
	private String workDate;
	
	public AttendanceVO() {
		
	}//constructor
	
	public AttendanceVO(int empNo,String ename, String startTime, String endTime, String workDate) {
		this.empNo = empNo;
		this.ename = ename;
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
	public String getEname() {
		return ename;
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
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
	
	
}//class
