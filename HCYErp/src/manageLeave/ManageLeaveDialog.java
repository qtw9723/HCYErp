package manageLeave;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import VO.DayOffApplyVO;

@SuppressWarnings("serial")
public class ManageLeaveDialog extends JDialog {
	private JButton jbtnApprove;
	private JButton jbtnReject;
	private JButton jbtnCancel;
	private JLabel jlblInfoPerson;
	private JLabel jlblInfoReason;
	private JTextArea jtaRejectReason;

	public ManageLeaveDialog(ManageLeave ml) throws SQLException {
		ManageLeaveDialogEvt event = new ManageLeaveDialogEvt(this);
		setLayout(null);
		
		//휴가신청 사원 정보
		jlblInfoPerson = new JLabel();
		DayOffApplyVO doaVO= ManageLeaveDAO.getInstance().selectPersonalDayOffApply(ml.getDoaVOList().get(ml.getJtLeaveProposal().getSelectedRow()).getEmpNo());
		StringBuilder sbDoaVO = new StringBuilder();
		sbDoaVO.append("<html>     사원번호 / 사원이름 / 휴가 시작 일 / 휴가 끝 일<br>").append(doaVO.getEmpNo()).append(" / ").append(doaVO.getEname()).append(" / ")
		.append(doaVO.getStartDate()).append(" / ").append(doaVO.getEndDate()).append("</html>");
		jlblInfoPerson.setText(sbDoaVO.toString());
		jlblInfoPerson.setBounds(50,30,1000,50);
		add(jlblInfoPerson);
		
		//휴가신청 사유
		jlblInfoReason = new JLabel();
		sbDoaVO.delete(0, sbDoaVO.length());
		sbDoaVO.append("<html>신청사유<br>").append(doaVO.getReason()).append("</html>");
		jlblInfoReason.setText(sbDoaVO.toString());
		jlblInfoReason.setBounds(100,100,100,100);
		add(jlblInfoReason);
		
		
		//승인버튼
		jbtnApprove = new JButton("승인");
		jbtnApprove.setBounds(400,300,100,40);
		jbtnApprove.addActionListener(event);
		add(jbtnApprove);
		
		//취소버튼
		jbtnCancel = new JButton("취소");
		jbtnCancel.setBounds(640,300,100,40);
		jbtnCancel.addActionListener(event);
		add(jbtnCancel);
		
		//반려버튼
		jbtnReject = new JButton("반려");
		jbtnReject.setBounds(520,300,100,40);
		jbtnReject.addActionListener(event);
		add(jbtnReject);
		
		
		//다이얼로그 설정
		setBounds(ml.getHcyE().getX()+100,ml.getHcyE().getY()+100, 800, 400);
		setVisible(true);
	}//Constructor 
	
}//class
