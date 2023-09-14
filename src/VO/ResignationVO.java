package VO;

import java.util.Date;

public class ResignationVO {
	private int empNo;
	private String reason;
	private Date grantDate;
	private Date lastDate;
	private String ename;
	
	public ResignationVO() {
	}//constructor

	public ResignationVO(int empNo, String reason, Date grantDate, Date lastDate) {
		this.empNo = empNo;
		this.reason = reason;
		this.grantDate = grantDate;
		this.lastDate = lastDate;
	}//constructor

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
}//class
