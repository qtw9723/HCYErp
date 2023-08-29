package kr.co.HCY;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class HCYErp extends JFrame {
	private JLabel jlblMainImg;
	private JLabel jlblEmpNo;
	private JLabel jlblPass;
	private JTextField jtfEmpNo;
	private JPasswordField jpfPass;
	private JButton jbtnLogIn;
	private JLabel jlblFindPass;
	private JLabel jlblQEmail;
	private JTabbedPane tabbedPane;
	private JPanel jpAttendance;
	private JPanel jpDoc;
	private JPanel jpWriteReport;
	private JPanel jpManageReport;
	private JPanel jpEmp;
	private JPanel jpManageStatus;
	private JPanel jpManageAttendance;
	private JPanel jpAuthorizeLeave;
	private JButton jbtnAttend;
	private JButton jbtnOffWork;
	private JButton jbtnApplyLeave;
	private JLabel jlblMonthlyAttendance;
	private JLabel jlblCalendar;
	private JLabel jlblLeftLeave;
	private JButton jbtnChangePass;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JScrollPane jspDocList;
	private JButton jbtnFileUpload;
	private JButton jbtnFileDownload;
	private JButton jbtnFileDelete;
	private JButton jbtnRef;
	private JButton jbtnReport;
	private JTextArea jtaReport;
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JComboBox<Integer> jcbDay;
	private JComboBox<String> jcbName;
	private JComboBox<String> jcbEmpNo;
	private JButton jbtnDateSearch;
	private JButton jbtnNameSearch;
	private JList<String> jlReport;
	private JList<String> jlDepartment;
	private JList<String> jlTeam;
	private JList<String> jlName;
	private JScrollPane jspDepartment;
	private JScrollPane jspTeam;
	private JScrollPane jspName;
	private JButton jbtnEmpRegister;
	private JButton jbtnResign;
	private JButton jbtnAbsence;
	private JTable jtRegiAbInfo;
	private JButton jbtnAttendDate;
	private JButton jbtnAttendName;
	private JCheckBox jcbAbsent;
	private JCheckBox jcbDayOff;
	private JList<String> jlLeaveProposal;
	private JLabel jlblBG;

	public HCYErp() {
		// 로고 설정
		jlblMainImg = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/조선 홍찬영.jpg"));
		jlblMainImg.setBounds(480, 20, 150, 150);
		add(jlblMainImg);
		// 텍스트 로고 설정
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErpTextLogo.png"));
		jlblLogoTxt.setBounds(470,160,180,150);
		add(jlblLogoTxt);
		
		// 로그인 폰트
		Font loginFont = new Font("맑은고딕",Font.BOLD,20);
		//사원번호 입력
		jlblEmpNo = new JLabel("사원번호");
		jlblEmpNo.setBounds(330,300,180,60);
		jlblEmpNo.setFont(loginFont);
		add(jlblEmpNo);
		
		jtfEmpNo = new JTextField();
		jtfEmpNo.setBounds(420,310,300,40);
		jtfEmpNo.setFont(loginFont);
		jtfEmpNo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		add(jtfEmpNo);
		
		//비밀번호 입력
		jlblPass = new JLabel("비밀번호");
		jlblPass.setBounds(330,350,180,60);
		jlblPass.setFont(loginFont);
		add(jlblPass);
		
		jpfPass = new JPasswordField();
		jpfPass.setBounds(420,360,300,40);
		jpfPass.setFont(loginFont);
		jpfPass.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		add(jpfPass);
		
		//로그인 버튼
		jbtnLogIn = new JButton("로그인");
		jbtnLogIn.setBounds(750,300,200,150);
		add(jbtnLogIn);

		// 배경 설정
		jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);

		setVisible(true);
		setBounds(400, 150, 1200, 700);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}// windowClosed
		});
	}// constructor

	public static void main(String[] args) {
		new HCYErp();
	}

	public JLabel getJlblMainImg() {
		return jlblMainImg;
	}

	public JLabel getJlblEmpNo() {
		return jlblEmpNo;
	}

	public JLabel getJlblPass() {
		return jlblPass;
	}

	public JTextField getJtfEmpNo() {
		return jtfEmpNo;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JButton getJbtnLogIn() {
		return jbtnLogIn;
	}

	public JLabel getJlblFindPass() {
		return jlblFindPass;
	}

	public JLabel getJlblQEmail() {
		return jlblQEmail;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JPanel getJpAttendance() {
		return jpAttendance;
	}

	public JPanel getJpDoc() {
		return jpDoc;
	}

	public JPanel getJpWriteReport() {
		return jpWriteReport;
	}

	public JPanel getJpManageReport() {
		return jpManageReport;
	}

	public JPanel getJpEmp() {
		return jpEmp;
	}

	public JPanel getJpManageStatus() {
		return jpManageStatus;
	}

	public JPanel getJpManageAttendance() {
		return jpManageAttendance;
	}

	public JPanel getJpAuthorizeLeave() {
		return jpAuthorizeLeave;
	}

	public JButton getJbtnAttend() {
		return jbtnAttend;
	}

	public JButton getJbtnOffWork() {
		return jbtnOffWork;
	}

	public JButton getJbtnApplyLeave() {
		return jbtnApplyLeave;
	}

	public JLabel getJlblMonthlyAttendance() {
		return jlblMonthlyAttendance;
	}

	public JLabel getJlblCalendar() {
		return jlblCalendar;
	}

	public JLabel getJlblLeftLeave() {
		return jlblLeftLeave;
	}

	public JButton getJbtnChangePass() {
		return jbtnChangePass;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public JScrollPane getJspDocList() {
		return jspDocList;
	}

	public JButton getJbtnFileUpload() {
		return jbtnFileUpload;
	}

	public JButton getJbtnFileDownload() {
		return jbtnFileDownload;
	}

	public JButton getJbtnFileDelete() {
		return jbtnFileDelete;
	}

	public JButton getJbtnRef() {
		return jbtnRef;
	}

	public JButton getJbtnReport() {
		return jbtnReport;
	}

	public JTextArea getJtaReport() {
		return jtaReport;
	}

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<Integer> getJcbDay() {
		return jcbDay;
	}

	public JComboBox<String> getJcbName() {
		return jcbName;
	}

	public JComboBox<String> getJcbEmpNo() {
		return jcbEmpNo;
	}

	public JButton getJbtnDateSearch() {
		return jbtnDateSearch;
	}

	public JButton getJbtnNameSearch() {
		return jbtnNameSearch;
	}

	public JList<String> getJlReport() {
		return jlReport;
	}

	public JList<String> getJlDepartment() {
		return jlDepartment;
	}

	public JList<String> getJlTeam() {
		return jlTeam;
	}

	public JList<String> getJlName() {
		return jlName;
	}

	public JScrollPane getJspDepartment() {
		return jspDepartment;
	}

	public JScrollPane getJspTeam() {
		return jspTeam;
	}

	public JScrollPane getJspName() {
		return jspName;
	}

	public JButton getJbtnEmpRegister() {
		return jbtnEmpRegister;
	}

	public JButton getJbtnResign() {
		return jbtnResign;
	}

	public JButton getJbtnAbsence() {
		return jbtnAbsence;
	}

	public JTable getJtRegiAbInfo() {
		return jtRegiAbInfo;
	}

	public JButton getJbtnAttendDate() {
		return jbtnAttendDate;
	}

	public JButton getJbtnAttendName() {
		return jbtnAttendName;
	}

	public JCheckBox getJcbAbsent() {
		return jcbAbsent;
	}

	public JCheckBox getJcbDayOff() {
		return jcbDayOff;
	}

	public JList<String> getJlLeaveProposal() {
		return jlLeaveProposal;
	}

}// class
