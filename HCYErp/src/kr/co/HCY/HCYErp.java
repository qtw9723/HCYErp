package kr.co.HCY;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
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
	private JLabel jlblMainImg;;
	private JLabel jlblEmpNo;;
	private JLabel jlblPass;;
	private JTextField jtfEmpNo;;
	private JPasswordField jpfPass;;
	private JButton jbtnLogIn;;
	private JLabel jlblFindPass;;
	private JLabel jlblQEmail;;
	private JTabbedPane tabbedPane;;
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
	private JDesktopPane desktopPane;

	public HCYErp() {
		desktopPane = new JDesktopPane();
		
		// 이벤트 객체 생성
		HCYErpEvt event = new HCYErpEvt(this);

		// 로고 설정
		jlblMainImg = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/hcytravel_mainlogo_3cm.png"));
		jlblMainImg.setBounds(480, 20, 180, 150);
		// 텍스트 로고 설정
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErpTextLogo.png"));
		jlblLogoTxt.setBounds(470, 160, 180, 150);

		// 로그인 폰트
		Font loginFont = new Font("맑은고딕", Font.BOLD, 20);
		// 사원번호 입력
		jlblEmpNo = new JLabel("사원번호");
		jlblEmpNo.setBounds(320, 295, 180, 60);
		jlblEmpNo.setFont(loginFont);

		jtfEmpNo = new JTextField();
		jtfEmpNo.setBounds(410, 305, 300, 40);
		jtfEmpNo.setFont(loginFont);
		jtfEmpNo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));

		// 비밀번호 입력
		jlblPass = new JLabel("비밀번호");
		jlblPass.setBounds(320, 370, 180, 60);
		jlblPass.setFont(loginFont);

		jpfPass = new JPasswordField();
		jpfPass.setBounds(410, 380, 300, 40);
		jpfPass.setFont(loginFont);
		jpfPass.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		jpfPass.addActionListener(event);

		// 로그인 버튼 폰트
		Font loginBtnFont = new Font("맑은고딕", Font.BOLD, 15);
		// 로그인 버튼
		jbtnLogIn = new JButton("로그인");
		jbtnLogIn.setBounds(728, 302, 80, 120);
		jbtnLogIn.setBackground(new Color(0x8244AD));
		jbtnLogIn.setFont(loginBtnFont);
		jbtnLogIn.setForeground(Color.white);
		jbtnLogIn.addActionListener(event);

		// 비밀번호 찾기 폰트
		Font findPWFont = new Font("맑은고딕", Font.BOLD, 15);
		// 비밀번호 찾기
		jlblFindPass = new JLabel("비밀번호 찾기");
		jlblFindPass.setBounds(517, 430, 180, 60);
		jlblFindPass.setFont(findPWFont);
		jlblFindPass.addMouseListener(event);

		// 문의 메일
		jlblQEmail = new JLabel("기타 문의: hcyadmin@travel.com");
		jlblQEmail.setBounds(450, 500, 300, 60);
		jlblQEmail.setFont(findPWFont);

		// 배경 설정
		jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		


		addComponent();
		
		// 탭설정
		tabbedPane = new JTabbedPane();
		tabbedPane.setVisible(true);
		tabbedPane.setBounds(100,100,100,100);
		add(tabbedPane);
		
		tabbedPane.add(jpAttendance,"근태관리");
		
		
		setVisible(true);
		setBounds(400, 150, 1200, 700);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
	}// constructor



	public void addComponent() {
		add(jlblQEmail);
		add(jlblFindPass);
		add(jbtnLogIn);
		add(jpfPass);
		add(jlblPass);
		add(jtfEmpNo);
		add(jlblEmpNo);
		add(jlblLogoTxt);
		add(desktopPane);
		add(jlblMainImg);
		add(jlblBG);
	}
	
	

	public JLabel getJlblBG() {
		return jlblBG;
	}



	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}



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
