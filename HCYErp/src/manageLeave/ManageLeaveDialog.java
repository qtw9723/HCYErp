package manageLeave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
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
		
		//타이틀 제목
		setTitle("휴가 신청 관리");
		
		getContentPane().setBackground(new Color(255,245,245));
		//휴가신청 사원 정보
		Font infoFont = new Font("맑은 고딕",Font.PLAIN,15);
		jlblInfoPerson = new JLabel();
		doaVO= ManageLeaveDAO.getInstance().selectPersonalDayOffApply(ml.getDoaVOList().get(ml.getJtLeaveProposal().getSelectedRow()).getDayOffNo());
		StringBuilder sbDoaVO = new StringBuilder();
		sbDoaVO.append("<html><b>사원번호&nbsp;:&nbsp;</b>").append(doaVO.getEmpNo()).append("<br>").append("<br><b>사원이름&nbsp;:&nbsp;</b>").append(doaVO.getEname()).append("<br>").append("<br><b>휴가 시작일&nbsp;:&nbsp;</b>")
		.append(doaVO.getStartDate()).append("<br>").append("<br><b>휴가 종료일&nbsp;:&nbsp;</b>").append(doaVO.getEndDate()).append("<br>").append("<br><b>신청사유&nbsp;:&nbsp;</b>").append(doaVO.getReason()).append("</html>");
		jlblInfoPerson.setText(sbDoaVO.toString());
		jlblInfoPerson.setBounds(70,43,1000,200);
		jlblInfoPerson.setFont(infoFont);
		add(jlblInfoPerson);
		
		//반려 사유 입력창
		jtaRejectReason = new JTextArea();
		Font jtaFont=new Font("맑은 고딕",Font.PLAIN,13);
		jtaRejectReason.setFont(jtaFont);
		jtaRejectReason.setBounds(400,40,340,240);
		TitledBorder titleBorder=BorderFactory.createTitledBorder("휴가 신청 목록");
		Font titleFont=new Font("맑은 고딕",Font.BOLD,15);
		titleBorder.setTitleFont(titleFont);
		titleBorder.setTitleJustification(titleBorder.CENTER);
		jtaRejectReason.setBorder(titleBorder);
		add(jtaRejectReason);
		
		//승인버튼
		jbtnApprove = new JButton("승인");
		Font buttonFont=new Font("맑은 고딕",Font.BOLD,15);
		jbtnApprove.setBounds(400,300,100,40);
		jbtnApprove.setFont(buttonFont);
		jbtnApprove.setForeground(Color.white);
		jbtnApprove.setBackground(new Color(0x6D47B0));
		jbtnApprove.addActionListener(event);
		add(jbtnApprove);
		
		//취소버튼
		jbtnCancel = new JButton("취소");
		jbtnCancel.setBounds(640,300,100,40);
		jbtnCancel.setFont(buttonFont);
		jbtnCancel.setBackground(new Color(0x919191));
		jbtnCancel.setForeground(Color.white);
		jbtnCancel.addActionListener(event);
		add(jbtnCancel);
		
		//반려버튼
		jbtnReject = new JButton("반려");
		jbtnReject.setBounds(520,300,100,40);
		jbtnReject.setFont(buttonFont);
		jbtnReject.setForeground(Color.white);
		jbtnReject.setBackground(new Color(0x5E5E5E));
		jbtnReject.addActionListener(event);
		add(jbtnReject);
		
		
		//다이얼로그 설정
		setBounds(ml.getHcyE().getX()+100,ml.getHcyE().getY()+100, 830, 430);
		setVisible(true);
		setResizable(false);
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
