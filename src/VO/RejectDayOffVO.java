package VO;

public class RejectDayOffVO {
	private MsgVO msgVO;
	private int dayOffNo;
	
	public RejectDayOffVO(MsgVO msgVO, int dayOffNo) {
		this.msgVO = msgVO;
		this.dayOffNo = dayOffNo;
	}//constructor
	
	
	public MsgVO getMsgVO() {
		return msgVO;
	}
	public void setMsgVO(MsgVO msgVO) {
		this.msgVO = msgVO;
	}
	public int getDayOffNo() {
		return dayOffNo;
	}
	public void setDayOffNo(int dayOffNo) {
		this.dayOffNo = dayOffNo;
	}
}//class
