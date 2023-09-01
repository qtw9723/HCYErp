package VO;

public class MsgVO {
	private int empNo;
	private int msgNo; 
	private String msgContent;
	private String msgCheck;
	
	public MsgVO() {
	}//constructor

	public MsgVO(int empNo, int msgNo, String msgContent, String msgCheck) {
		this.empNo = empNo;
		this.msgNo = msgNo;
		this.msgContent = msgContent;
		this.msgCheck = msgCheck;
	}//constructor

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgCheck() {
		return msgCheck;
	}

	public void setMsgCheck(String msgCheck) {
		this.msgCheck = msgCheck;
	}
	
}//class
