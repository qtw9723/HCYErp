package VO;

public class DailyReportVO {
	private int empNo;
	private String reportDate;
	private String reportContent;
	
	public DailyReportVO() {
	}//constructor

	public DailyReportVO(int empNo, String reportDate, String reportContent) {
		this.empNo = empNo;
		this.reportDate = reportDate;
		this.reportContent = reportContent;
	}//constructor

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	
}//class
