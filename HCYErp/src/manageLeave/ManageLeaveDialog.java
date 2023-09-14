package manageLeave;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import VO.DayOffApplyVO;

@SuppressWarnings("serial")
public class ManageLeaveDialog extends JDialog {
	private JButton jbtnApprove;
	private JButton jbtnReject;
	private JButton jbtnCancel;
	private JLabel jlblInfoPerson;
	private JTextArea jtaRejectReason;
	private DayOffApplyVO doaVO;
	private ManageLeave ml;

	public ManageLeaveDialog(ManageLeave ml) throws SQLException {
		this.ml = ml;
		ManageLeaveDialogEvt event = new ManageLeaveDialogEvt(this);
		setLayout(null);
		
		//휴가신청 사원 정보
		Font infoFont = new Font("맑은 고딕",Font.BOLD,17);
		jlblInfoPerson = new JLabel();
		doaVO= ManageLeaveDAO.getInstance().selectPersonalDayOffApply(ml.getDoaVOList().get(ml.getJtLeaveProposal().getSelectedRow()).getDayOffNo());
		StringBuilder sbDoaVO = new StringBuilder();
		sbDoaVO.append("<html>사원번호&nbsp;:&nbsp;").append(doaVO.getEmpNo()).append("<br>사원이름&nbsp;:&nbsp;").append(doaVO.getEname()).append("<br>휴가 시작 일&nbsp;:&nbsp;")
		.append(doaVO.getStartDate()).append("<br>휴가 끝 일&nbsp;:&nbsp;").append(doaVO.getEndDate()).append("<br>신청사유&nbsp;:&nbsp;").append(doaVO.getReason()).append("</html>");
		jlblInfoPerson.setText(sbDoaVO.toString());
		jlblInfoPerson.setBounds(70,10,1000,200);
		jlblInfoPerson.setFont(infoFont);
		add(jlblInfoPerson);
		
		//반려 사유 입력창
		jtaRejectReason = new JTextArea();
		jtaRejectReason.setBounds(400,40,340,240);
		jtaRejectReason.setBorder(new TitledBorder("반려 사유"));
		add(jtaRejectReason);
		
		//승인버튼
		jbtnApprove = new JButton("승인");
		jbtnApprove.setBounds(400,300,100,40);
		jbtnApprove.setBackground(new Color(0x8244AD));
		jbtnApprove.addActionListener(event);
		add(jbtnApprove);
		
		//취소버튼
		jbtnCancel = new JButton("취소");
		jbtnCancel.setBounds(640,300,100,40);
		jbtnCancel.setBackground(new Color(0xE0E0E0));
		jbtnCancel.addActionListener(event);
		add(jbtnCancel);
		
		//반려버튼
		jbtnReject = new JButton("반려");
		jbtnReject.setBounds(520,300,100,40);
		jbtnReject.setBackground(new Color(0x5E5E5E));
		jbtnReject.addActionListener(event);
		add(jbtnReject);
		
		
		//다이얼로그 설정
		setBounds(ml.getHcyE().getX()+100,ml.getHcyE().getY()+100, 800, 400);
		setVisible(true);
	}//Constructor 

	public JButton getJbtnApprove() {
		return jbtnApprove;
	}

	public JButton getJbtnReject() {
		return jbtnReject;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JLabel getJlblInfoPerson() {
		return jlblInfoPerson;
	}

	public JTextArea getJtaRejectReason() {
		return jtaRejectReason;
	}

	public DayOffApplyVO getDoaVO() {
		return doaVO;
	}

	public ManageLeave getMl() {
		return ml;
	}
	
	
	
}//class
