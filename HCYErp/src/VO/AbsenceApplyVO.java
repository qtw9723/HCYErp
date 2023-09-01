package VO;

import java.util.Date;

public class AbsenceApplyVO {
	private int empNo;
	private int absenceDays;
	private Date startDate;
	private Date endDate; 
	private String reason;
	private boolean grant;
	private Date submitDate;
	
	public AbsenceApplyVO() {
	}//constructor

	public AbsenceApplyVO(int empNo, int absenceDays, Date startDate, Date endDate, String reason, boolean grant,
			Date submitDate) {
		this.empNo = empNo;
		this.absenceDays = absenceDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.grant = grant;
		this.submitDate = submitDate;
	}//constructor

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getAbsenceDays() {
		return absenceDays;
	}

	public void setAbsenceDays(int absenceDays) {
		this.absenceDays = absenceDays;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
