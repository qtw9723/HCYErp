package VO;

import java.util.List;

public class DocPermissionVO {
	private int docNo;
	private List<Integer> deptNo;
	
	public DocPermissionVO() {
		
	}//constructor
	
	public DocPermissionVO(int docNo, List<Integer> deptNo) {
		this.docNo = docNo;
		this.deptNo = deptNo;
	}//constructor
	
	//getter
	public int getDocNo() {
		return docNo;
	}
	public List<Integer> getDeptNo() {
		return deptNo;
	}
	
	//setter
	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}
	public void setDeptNo(List<Integer> deptNo) {
		this.deptNo = deptNo;
	}
	
	
}//class
