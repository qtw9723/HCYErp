package VO;

import java.util.Date;

public class DayOffApplyVO {
	private int empNo;
	private int dayOffDays;
	private String reason;
	private String startDate;
	private String endDate;
	private boolean grant;
	private Date submitDate;
	
	public DayOffApplyVO() {
	}//constructor

	public DayOffApplyVO(int empNo, int dayOffDays, String reason, String startDate, String endDate, boolean grant,
			Date submitDate) {
		this.empNo = empNo;
		this.dayOffDays = dayOffDays;
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
		this.grant = grant;
		this.submitDate = submitDate;
	}//constructor

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getDayOffDays() {
		return dayOffDays;
	}

	public void setDayOffDays(int dayOffDays) {
		this.dayOffDays = dayOffDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isGrant() {
		return grant;
	}

	public void setGrant(boolean grant) {
		this.grant = grant;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
}//class
