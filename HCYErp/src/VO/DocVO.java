package VO;

import java.util.Date;

public class DocVO {
	private int docNo;
	private int deptNo;
	private Date inputDate;
	private String docName;
	
	
	
	public DocVO() {
		
	}//constructor

	public DocVO(int docNo, int deptNo, Date inputDate, String docName) {
		this.docNo = docNo;
		this.deptNo = deptNo;
		this.inputDate = inputDate;
		this.docName = docName;
	}//constructor
	
	//getter
	public int getDocNo() {
		return docNo;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public String getDocName() {
		return docName;
	}
	
	
	//setter
	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	
	
}//class
