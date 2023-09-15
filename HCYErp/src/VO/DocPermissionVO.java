package VO;

public class DocPermissionVO {
	private int docNo;
	private String deptName;
	
	public DocPermissionVO() {
		
	}//constructor
	
	public DocPermissionVO(int docNo, String deptName) {
		this.docNo = docNo;
		this.deptName = deptName;
	}//constructor
	
	//getter
	public int getDocNo() {
		return docNo;
	}
	public String getdeptName() {
		return deptName;
	}
	
	//setter
	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}
	public void setdeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}//class
