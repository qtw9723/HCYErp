package login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import attendance.Attendance;
import dailyReport.DailyReport;
import manageDoc.ManageDoc;

@SuppressWarnings("serial")
public class HCYErp extends JFrame {
	private JPasswordField jpfPass;
	private JTabbedPane tabbedPane;
	private JTextField jtfEmpNo;
	private JButton jbtnLogIn;
	private JLabel jlblFindPass;
	private JLabel jlblLogoTxt;
	private JLabel jlblMainImg;
	private JLabel jlblQEmail;
	private JLabel jlblEmpNo;
	private JLabel jlblPass;
	private JLabel jlblBG;
	private int user;

	public HCYErp() {
		super("마 자신있나");
		// 이벤트 객체 생성
		HCYErpEvt event = new HCYErpEvt(this);

		// 로고 설정
		jlblMainImg = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/hcytravel_mainlogo_3cm.png"));
		jlblMainImg.setBounds(480, 20, 180, 150);
		
		// 텍스트 로고 설정
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErpTextLogoSmall.png"));
		jlblLogoTxt.setBounds(470, 140, 180, 150);

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
		
		// 탭드 패인 생성
		tabbedPane = new JTabbedPane();
		tabbedPane.add("출근",new Attendance());
		tabbedPane.add("문서관리",new ManageDoc());
		tabbedPane.add("업무일지 작성",new DailyReport());
//		addComponent();
		add(tabbedPane);
		
		
		setVisible(true);
		setBounds(400, 150, 1200, 700);
	}// constructor

	public void removeComponent() {
		remove(jlblFindPass);
		remove(jlblLogoTxt);
		remove(jlblMainImg);
		remove(jlblQEmail);
		remove(jbtnLogIn);
		remove(jlblEmpNo);
		remove(jtfEmpNo);
		remove(jlblPass);
		remove(jpfPass);
		remove(jlblBG);
	}//removeComponent



	public void addComponent() {
		add(jlblFindPass);
		add(jlblLogoTxt);
		add(jlblMainImg);
		add(jlblQEmail);
		add(jbtnLogIn);
		add(jlblEmpNo);
		add(jlblPass);
		add(jtfEmpNo);
		add(jpfPass);
		add(jlblBG);
	}// addComponent



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



	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}



	public JLabel getJlblBG() {
		return jlblBG;
	}



	public int getUser() {
		return user;
	}



	public void setUser(int user) {
		this.user = user;
	}
	
	

}// class
