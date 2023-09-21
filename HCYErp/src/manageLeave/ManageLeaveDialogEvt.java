package manageLeave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import VO.MsgVO;
import VO.RejectDayOffVO;

public class ManageLeaveDialogEvt implements ActionListener {

	private ManageLeaveDialog mld; 
	
	public ManageLeaveDialogEvt(ManageLeaveDialog mld) {
		this.mld=mld; 
		mld.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			mld.dispose();
		}//windowClosing
		});
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		//승인버튼
		if(e.getSource()==mld.getJbtnApprove()) {
			try {
				approve();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//catch
		}//if
		
		//반려버튼
		if(e.getSource()==mld.getJbtnReject()) {
			reject();
		}//if
		
		//취소버튼
		if(e.getSource()==mld.getJbtnCancel()) {
			mld.dispose();
		}//if
		
	}//actionPerformed

	public void reject() {
		MsgVO msgVO = new MsgVO();
		msgVO.setEmpNo(mld.getDoaVO().getEmpNo());
		msgVO.setMsgCheck("N");
		msgVO.setMsgContent(mld.getJtaRejectReason().getText());
		RejectDayOffVO rdoVO = new RejectDayOffVO(msgVO, mld.getDoaVO().getDayOffNo());
		try {
			if(ManageLeaveDAO.getInstance().rejectDayOff(rdoVO)) {
				JOptionPane.showMessageDialog(mld, "성공적으로 반려되었습니다.");
				refresh(mld.getMl().getHcyE().getEvent().getSelectedIndex());
			}else {
				JOptionPane.showMessageDialog(mld, "반려가 실패되었습니다.");
			}//else
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//catch
		//리스트 초기화
		mld.getMl().addApplyList();
		mld.dispose();
	}//reject

	public void approve() throws SQLException {
		//업데이트
		ManageLeaveDAO.getInstance().updateDayOffApply(mld.getDoaVO().getDayOffNo());
		//성공 메세지
		JOptionPane.showMessageDialog(mld, mld.getDoaVO().getEname()+"님이 신청하신 "+mld.getDoaVO().getStartDate()+"부터 "+mld.getDoaVO().getEndDate()+"까지의 휴가를 승인하였습니다.");
		//리스트 초기화
		refresh(mld.getMl().getHcyE().getEvent().getSelectedIndex());
		mld.getMl().addApplyList();
		mld.dispose();
	}//approve
	
	public void refresh(int index) throws SQLException {
		mld.getMl().getHcyE().getTabbedPane().setVisible(false);
		mld.getMl().getHcyE().addComponent();
		mld.getMl().getHcyE().getEvent().login();
		mld.getMl().getHcyE().getTabbedPane().setSelectedIndex(index);
		
	}
	
}//class
