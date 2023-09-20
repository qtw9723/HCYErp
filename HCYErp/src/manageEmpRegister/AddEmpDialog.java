package manageEmpRegister;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddEmpDialog extends JDialog {
	private ManageEmpRegister mer;
	private JLabel jlblEname;
	private JLabel jlblEmail;
	private JLabel jlblHyphenEmail;
	private JLabel jlblAddr;
	private JLabel jlblSsn;
	private JLabel jlblHyphenSsn;
	private JLabel jlblDept;
	private JLabel jlblTeam;
	private JLabel jlblJob;
	private JLabel jlblTel;
	private JLabel jlblJobTel;
	private JLabel jlblLevel;
	private JLabel jlblYearHiredate;
	private JLabel jlblMonthHiredate;
	private JLabel jlblDayHiredate;
	private JLabel jlblSal;
	private JLabel jlblLoc;
	private JLabel jlblHiredate;

	private JTextField jtfEname;
	private JTextField jtfStartEmail;
	private JTextField jtfEndEmail;
	private JTextField jtfAddr;
	private JTextField jtfDetailAddr;
	private JTextField jtfStartSsn;
	private JTextField jtfEndSsn;
	private JTextField jtfDept;
	private JTextField jtfTeam;
	private JTextField jtfJob;
	private JTextField jtfTel;
	private JTextField jtfJobTel;
	private JTextField jtfLevel;

	private JComboBox<Integer> jcbYearHiredate;
	private JComboBox<Integer> jcbMonthHiredate;
	private JComboBox<Integer> jcbDayHiredate; 
	private JComboBox<String> jcbDept;
	private JComboBox<String> jcbTeam;
	private JComboBox<String> jcbJob;
	private JComboBox<String> jcbLevel;

	private JTextField jtfSal;
	private JTextField jtfLoc;

	private JButton jbtnAddEmp;
	private JButton jbtnCancel;

	public AddEmpDialog(ManageEmpRegister mer) {
		this.mer = mer;

		// Calendar선언
		Calendar cal = Calendar.getInstance();

		setLayout(null);

		getContentPane().setBackground(new Color(255,245,245));
		
		// JLabel 선언
		jlblEname = new JLabel("이름");
		jlblEmail = new JLabel("이메일");
		jlblHyphenEmail = new JLabel("@");
		jlblAddr = new JLabel("주소");
		jlblSsn = new JLabel("주민번호");
		jlblHyphenSsn = new JLabel("-");
		jlblDept = new JLabel("부서");
		jlblTeam = new JLabel("팀");
		jlblJob = new JLabel("직무");
		jlblTel = new JLabel("전화번호");
		jlblJobTel = new JLabel("회사번호");
		jlblLevel = new JLabel("직급");
		jlblYearHiredate = new JLabel("년");
		jlblMonthHiredate = new JLabel("월");
		jlblDayHiredate = new JLabel("일");
		jlblSal = new JLabel("연봉");
		jlblLoc = new JLabel("근무지");
		jlblHiredate = new JLabel("입사일");

		// 입사일 날짜 콤보박스
		jcbYearHiredate = new JComboBox<Integer>();
		jcbMonthHiredate = new JComboBox<Integer>();
		jcbDayHiredate = new JComboBox<Integer>();

		// 현재 년을 설정
		int year = cal.get(Calendar.YEAR);

		// JComboBox에 값 추가
		for (int i = year; i < year + 2; i++) {
			jcbYearHiredate.addItem(i);
		} // end for

		for (int i = 1; i <= 12; i++) {
			jcbMonthHiredate.addItem(i);
		} // end for

		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbDayHiredate.addItem(i);
		} // end for

		// JTextField선언
		jtfEname = new JTextField();
		jtfStartEmail = new JTextField();
		jtfEndEmail = new JTextField();
		jtfAddr = new JTextField("시/구/동 입력");
		jtfDetailAddr = new JTextField("상세주소 입력");
		jtfStartSsn = new JTextField();
		jtfEndSsn = new JTextField();
		jtfDept = new JTextField();
		jtfTeam = new JTextField();
		jtfJob = new JTextField();
		jtfTel = new JTextField();
		jtfJobTel = new JTextField();
		jtfLevel = new JTextField();
		jtfSal = new JTextField();
		jtfLoc = new JTextField();

		jbtnAddEmp = new JButton("추가완료");
		jbtnCancel = new JButton("취소");

		// 주소에 대한 정렬
		jtfAddr.setHorizontalAlignment(JTextField.CENTER);
		jtfDetailAddr.setHorizontalAlignment(JTextField.CENTER);

		//부서 등 콤보박스 선언
		jcbDept = new JComboBox<String>();
		jcbTeam = new JComboBox<String>();
		jcbJob = new JComboBox<String>();
		jcbLevel = new JComboBox<String>();
				
		//콤박 배경색
		jcbDayHiredate.setBackground(Color.white);
		jcbDept.setBackground(Color.white);
		jcbJob.setBackground(Color.white);
		jcbLevel.setBackground(Color.white);
		jcbMonthHiredate.setBackground(Color.white);
		jcbTeam.setBackground(Color.white);
		jcbYearHiredate.setBackground(Color.white);
		
		// add
		add(jlblEname);
		add(jlblEmail);
		add(jlblHyphenEmail);
		add(jlblAddr);
		add(jlblSsn);
		add(jlblHyphenSsn);
		add(jlblDept);
		add(jlblTeam);
		add(jlblJob);
		add(jlblTel);
		add(jlblJobTel);
		add(jlblLevel);
		add(jlblYearHiredate);
		add(jlblMonthHiredate);
		add(jlblDayHiredate);
		add(jlblSal);
		add(jlblLoc);
		add(jlblHiredate);

		add(jtfEname);
		add(jtfStartEmail);
		add(jtfEndEmail);
		add(jtfAddr);
		add(jtfDetailAddr);
		add(jtfStartSsn);
		add(jtfEndSsn);
		add(jtfDept);
		add(jtfTeam);
		add(jtfJob);
		add(jtfTel);
		add(jtfJobTel);
		add(jtfLevel);

		add(jcbYearHiredate);
		add(jcbMonthHiredate);
		add(jcbDayHiredate);

		add(jcbDept);
		add(jcbTeam);
		add(jcbJob);
		add(jcbLevel);
		
		add(jtfSal);
		add(jtfLoc);

		add(jbtnAddEmp);
		add(jbtnCancel);

		//폰트
		Font jcbFont = new Font("맑은 고딕", Font.PLAIN, 14);
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 14);
		Font jtfFont = new Font("맑은 고딕", Font.PLAIN, 14);
				
		jlblEname.setFont(jlblFont);
		jlblEmail.setFont(jlblFont);
		jlblHyphenEmail.setFont(jlblFont);
		jlblAddr.setFont(jlblFont);
		jlblSsn.setFont(jlblFont);
		jlblHyphenSsn.setFont(jlblFont);
		jlblDept.setFont(jlblFont);
		jlblTeam.setFont(jlblFont);
		jlblJob.setFont(jlblFont);
		jlblTel.setFont(jlblFont);
		jlblJobTel.setFont(jlblFont);
		jlblLevel.setFont(jlblFont);
		jlblYearHiredate.setFont(jlblFont);
		jlblMonthHiredate.setFont(jlblFont);
		jlblDayHiredate.setFont(jlblFont);
		jlblSal.setFont(jlblFont);
		jlblLoc.setFont(jlblFont);
		jlblHiredate.setFont(jlblFont);
		
		jcbYearHiredate.setFont(jcbFont);
		jcbMonthHiredate.setFont(jcbFont);
		jcbDayHiredate.setFont(jcbFont);
		jcbDept.setFont(jcbFont);
		jcbTeam.setFont(jcbFont);
		jcbJob.setFont(jcbFont);
		jcbLevel.setFont(jcbFont);
		
		jtfEname.setFont(jtfFont);
		jtfStartEmail.setFont(jtfFont);
		jtfEndEmail.setFont(jtfFont);
		jtfAddr.setFont(jtfFont);
		jtfDetailAddr.setFont(jtfFont);
		jtfStartSsn.setFont(jtfFont);
		jtfEndSsn.setFont(jtfFont);
		jtfDept.setFont(jtfFont);
		jtfTeam.setFont(jtfFont);
		jtfJob.setFont(jtfFont);
		jtfTel.setFont(jtfFont);
		jtfJobTel.setFont(jtfFont);
		jtfLevel.setFont(jtfFont);
		
		// 배치
		jlblEname.setBounds(40, 50, 100, 50);
		jlblEmail.setBounds(40, 90, 100, 50);
		jlblHyphenEmail.setBounds(260, 90, 100, 50);
		jlblAddr.setBounds(40, 130, 100, 50);
		jlblSsn.setBounds(40, 210, 100, 50);
		jlblHyphenSsn.setBounds(240, 210, 100, 50);
		jlblTel.setBounds(40, 250, 100, 50);
		jlblLoc.setBounds(40, 290, 100, 50);
		jlblTeam.setBounds(40, 330, 100, 50);
		jlblHiredate.setBounds(40,370,100,50);
		jlblSal.setBounds(40, 410, 100, 50);
		
		jlblJobTel.setBounds(330, 250, 100, 50);
		jlblDept.setBounds(330, 290, 100, 50);
		jlblJob.setBounds(330, 330, 100, 50);
		jlblLevel.setBounds(610, 330, 100, 50);
		jlblYearHiredate.setBounds(183, 380, 30, 30);
		jlblMonthHiredate.setBounds(263, 380, 30, 30);
		jlblDayHiredate.setBounds(343, 380, 30, 30);

		jtfEname.setBounds(110, 60, 100, 30);
		jtfStartEmail.setBounds(110, 100, 150, 30);
		jtfEndEmail.setBounds(275, 100, 200, 30);
		jtfAddr.setBounds(110, 140, 315, 30);
		jtfDetailAddr.setBounds(110, 180, 200, 30);
		jtfStartSsn.setBounds(110, 220, 100, 30);
		jtfEndSsn.setBounds(240, 220, 100, 30);
		jtfTel.setBounds(110, 260, 150, 30);
		jtfLoc.setBounds(110, 300, 150, 30);
		jtfSal.setBounds(110, 420, 150, 30);
		jtfJobTel.setBounds(400, 260, 150, 30);

		jcbDept.setBounds(400, 300, 150, 30);
		jcbTeam.setBounds(110, 340, 150, 30);
		jcbJob.setBounds(400, 340, 150, 30);
		jcbLevel.setBounds(660, 340, 150, 30);
		jcbYearHiredate.setBounds(110, 380, 70, 30);
		jcbMonthHiredate.setBounds(210, 380, 50, 30);
		jcbDayHiredate.setBounds(290, 380, 50, 30);

		jbtnAddEmp.setBounds(265, 480, 135, 40);
		jbtnAddEmp.setBackground(new Color(0x6D47B0));
		Font AddEmpBtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		jbtnAddEmp.setFont(AddEmpBtnFont);
		jbtnAddEmp.setForeground(Color.white);
		jbtnCancel.setBounds(455, 480, 135, 40);
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		Font CancelBtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		jbtnCancel.setFont(CancelBtnFont);
		jbtnCancel.setForeground(Color.white);

		// 이벤트 발생
		AddEmpDialogEvt event = new AddEmpDialogEvt(this);
		jbtnAddEmp.addActionListener(event);
		jbtnCancel.addActionListener(event);
		jcbMonthHiredate.addActionListener(event);
		jcbDayHiredate.addActionListener(event);

		setTitle("입사자 추가");
		setVisible(true);
		setSize(870, 600);

	}// constructor

	// getter
	public JTextField getJtfDetailAddr() {
		return jtfDetailAddr;
	}

	public ManageEmpRegister getMer() {
		return mer;
	}

	public JLabel getJlblEname() {
		return jlblEname;
	}

	public JLabel getJlblEmail() {
		return jlblEmail;
	}

	public JLabel getJlblHyphenEmail() {
		return jlblHyphenEmail;
	}

	public JLabel getJlblAddr() {
		return jlblAddr;
	}

	public JLabel getJlblSsn() {
		return jlblSsn;
	}

	public JLabel getJlblHyphenSsn() {
		return jlblHyphenSsn;
	}

	public JLabel getJlblDept() {
		return jlblDept;
	}

	public JLabel getJlblTeam() {
		return jlblTeam;
	}

	public JLabel getJlblJob() {
		return jlblJob;
	}

	public JLabel getJlblTel() {
		return jlblTel;
	}

	public JLabel getJlblJobTel() {
		return jlblJobTel;
	}

	public JLabel getJlblLevel() {
		return jlblLevel;
	}

	public JLabel getJlblYearHiredate() {
		return jlblYearHiredate;
	}

	public JLabel getJlblMonthHiredate() {
		return jlblMonthHiredate;
	}

	public JLabel getJlblDayHiredate() {
		return jlblDayHiredate;
	}

	public JLabel getJlblSal() {
		return jlblSal;
	}

	public JLabel getJlblLoc() {
		return jlblLoc;
	}

	public JTextField getJtfEname() {
		return jtfEname;
	}

	public JTextField getJtfStartEmail() {
		return jtfStartEmail;
	}

	public JTextField getJtfEndEmail() {
		return jtfEndEmail;
	}

	public JTextField getJtfAddr() {
		return jtfAddr;
	}

	public JTextField getJtfStartSsn() {
		return jtfStartSsn;
	}

	public JTextField getJtfEndSsn() {
		return jtfEndSsn;
	}

	public JTextField getJtfDept() {
		return jtfDept;
	}

	public JTextField getJtfTeam() {
		return jtfTeam;
	}

	public JTextField getJtfJob() {
		return jtfJob;
	}

	public JTextField getJtfTel() {
		return jtfTel;
	}

	public JTextField getJtfJobTel() {
		return jtfJobTel;
	}

	public JTextField getJtfLevel() {
		return jtfLevel;
	}

	public JComboBox<Integer> getJcbYearHiredate() {
		return jcbYearHiredate;
	}

	public JComboBox<Integer> getJcbMonthHiredate() {
		return jcbMonthHiredate;
	}

	public JComboBox<Integer> getJcbDayHiredate() {
		return jcbDayHiredate;
	}

	public JTextField getJtfSal() {
		return jtfSal;
	}

	public JTextField getJtfLoc() {
		return jtfLoc;
	}

	public JButton getJbtnAddEmp() {
		return jbtnAddEmp;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

}// class
