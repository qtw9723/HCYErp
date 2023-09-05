package manageLeave;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import login.HCYErp;

@SuppressWarnings("serial")
public class ManageLeave extends JPanel{

	private HCYErp hcyE;
	private JList<String> jlLeaveProposal;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JDesktopPane desktopPane;
	private JScrollPane jspLeave;
	
	public ManageLeave(HCYErp hcyE) {
		setLayout(null);
		ManageLeaveEvt event = new ManageLeaveEvt(this);
		
		DefaultListModel<String> dlmLeave = new DefaultListModel<String>();
		for(int i = 0 ; i<100 ; i++) {
		dlmLeave.addElement("클래드 열받긴 해");
		}
		jlLeaveProposal = new JList<String>(dlmLeave);
		jspLeave = new JScrollPane(jlLeaveProposal);
		jspLeave.setBounds(100,50,800,500);
		jspLeave.setBorder(new TitledBorder("휴가 신청 목록"));
		jlLeaveProposal.addMouseListener(event);
		add(jspLeave);
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
	}//Constructor

	public HCYErp getHcyE() {
		return hcyE;
	}

	public JList<String> getJlLeaveProposal() {
		return jlLeaveProposal;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
	
	 
}//class
