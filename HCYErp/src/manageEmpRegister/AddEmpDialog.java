package manageEmpRegister;

import java.awt.Color;
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

	private JTextField jtfSal;
	private JTextField jtfLoc;

	private JButton jbtnAddEmp;
	private JButton jbtnCancel;

	public AddEmpDialog(ManageEmpRegister mer) {
		this.mer = mer;

		// Calendar선언
		Calendar cal = Calendar.getInstance();

		setLayout(null);

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

		// 입사일
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

		add(jtfSal);
		add(jtfLoc);

		add(jbtnAddEmp);
		add(jbtnCancel);

		// 배치
		jlblEname.setBounds(30, 50, 100, 50);
		jlblEmail.setBounds(30, 90, 100, 50);
		jlblHyphenEmail.setBounds(280, 90, 100, 50);
		jlblAddr.setBounds(30, 130, 100, 50);
		jlblSsn.setBounds(30, 210, 100, 50);
		jlblHyphenSsn.setBounds(240, 210, 100, 50);
		jlblDept.setBounds(30, 250, 100, 50);
		jlblTeam.setBounds(30, 290, 100, 50);
		jlblJob.setBounds(30, 330, 100, 50);
		jlblTel.setBounds(30, 370, 100, 50);
		jlblJobTel.setBounds(30, 410, 100, 50);
		jlblLevel.setBounds(30, 450, 100, 50);
		jlblYearHiredate.setBounds(510, 20, 100, 30);
		jlblMonthHiredate.setBounds(670, 20, 100, 30);
		jlblDayHiredate.setBounds(820, 20, 100, 30);
		jlblSal.setBounds(400, 130, 100, 50);
		jlblLoc.setBounds(400, 170, 100, 50);

		jtfEname.setBounds(130, 60, 100, 30);
		jtfStartEmail.setBounds(130, 100, 150, 30);
		jtfEndEmail.setBounds(295, 100, 150, 30);
		jtfAddr.setBounds(130, 140, 200, 30);
		jtfDetailAddr.setBounds(130, 180, 200, 30);
		jtfStartSsn.setBounds(130, 220, 100, 30);
		jtfEndSsn.setBounds(260, 220, 100, 30);
		jtfDept.setBounds(130, 260, 100, 30);
		jtfTeam.setBounds(130, 300, 100, 30);
		jtfJob.setBounds(130, 340, 100, 30);
		jtfTel.setBounds(130, 380, 150, 30);
		jtfJobTel.setBounds(130, 420, 150, 30);
		jtfLevel.setBounds(130, 460, 100, 30);

		jcbYearHiredate.setBounds(400, 10, 100, 50);
		jcbMonthHiredate.setBounds(550, 10, 100, 50);
		jcbDayHiredate.setBounds(700, 10, 100, 50);

		jtfSal.setBounds(500, 140, 150, 30);
		jtfLoc.setBounds(500, 180, 100, 30);

		jbtnAddEmp.setBounds(700, 500, 100, 50);
		jbtnAddEmp.setBackground(new Color(0x8244AD));
		jbtnCancel.setBounds(850, 500, 100, 50);
		jbtnCancel.setBackground(new Color(0xE0E0E0));

		// 이벤트 발생
		AddEmpDialogEvt event = new AddEmpDialogEvt(this);
		jbtnAddEmp.addActionListener(event);
		jbtnCancel.addActionListener(event);
		jcbMonthHiredate.addActionListener(event);
		jcbDayHiredate.addActionListener(event);

		setVisible(true);
		setSize(1000, 600);

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
