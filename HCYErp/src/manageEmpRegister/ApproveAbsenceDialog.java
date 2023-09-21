package manageEmpRegister;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import attendance.ApplyDayOffDialogEvt;
import manageDailyReport.ManageDailyReportDAO;

public class ApproveAbsenceDialog extends JDialog {
	private ManageEmpRegister mer;

	private JComboBox<String> jcbEmpNoName;
	private JComboBox<Integer> jcbStartYear;
	private JComboBox<Integer> jcbStartMonth;
	private JComboBox<Integer> jcbStartDay;
	private JComboBox<Integer> jcbEndYear;
	private JComboBox<Integer> jcbEndMonth;
	private JComboBox<Integer> jcbEndDay;
	private JComboBox<String> jcbReason;
	private JTextArea jtaWriteReason;
	private JLabel jlblPeriod;
	private JLabel jlblDuring;
	private JLabel jlblReason;
	private JButton jbtnApprove;
	private JButton jbtnCancel;

	public ApproveAbsenceDialog(ManageEmpRegister mer) {
		this.mer = mer;

		getContentPane().setBackground(new Color(255,245,245));
		
		// Calendar 선언
		Calendar cal = Calendar.getInstance();

		// 콤보박스 선언
		jcbStartYear = new JComboBox<Integer>();
		jcbStartMonth = new JComboBox<Integer>();
		jcbStartDay = new JComboBox<Integer>();
		jcbEndYear = new JComboBox<Integer>();
		jcbEndMonth = new JComboBox<Integer>();
		jcbEndDay = new JComboBox<Integer>();
		jcbReason = new JComboBox<String>();

		// JButton 선언
		jbtnApprove = new JButton("휴직 처리");
		jbtnCancel = new JButton("취소");

		// JLabel 선언
		jlblPeriod = new JLabel("휴직기간 : ");
		jlblDuring = new JLabel("~");
		jlblReason = new JLabel("휴직사유 : ");

		JLabel jlblStartYear = new JLabel("년");
		JLabel jlblStartMonth = new JLabel("월");
		JLabel jlblStartDay = new JLabel("일");
		JLabel jlblEndYear = new JLabel("년");
		JLabel jlblEndMonth = new JLabel("월");
		JLabel jlblEndDay = new JLabel("일");

		// JTextField 선언
		jtaWriteReason = new JTextArea("사유를 입력해주세요");
		jtaWriteReason.setEditable(false);

		//이름 콤박
		jcbEmpNoName=new JComboBox<String>();
		try {
			List<String> empList = ManageDailyReportDAO.getInstance().selectEmp(mer.getHcyE().getUser());
			for(String emp : empList) {
				jcbEmpNoName.addItem(emp);
			}//for
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
		// 현재 년을 설정
		int year = cal.get(Calendar.YEAR);
		// JComboBox에 값 추가
		for (int i = year; i < year + 2; i++) {
			jcbStartYear.addItem(i);
			jcbEndYear.addItem(i);
		} // end for

		// 월 추가
		for (int i = 1; i <= 12; i++) {
			jcbStartMonth.addItem(i);
			jcbEndMonth.addItem(i);
		} // end for

		// 현재달의 일을 콤보박스에 설정
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbStartDay.addItem(i);
			jcbEndDay.addItem(i);
		} // end for

		// ResonComboBox에 사유 선언
		jcbReason.addItem("질병 휴직");
		jcbReason.addItem("육아 휴직");
		jcbReason.addItem("간병 휴직");
		jcbReason.addItem("자기계발 휴직");
		jcbReason.addItem("배우자 동반 휴직");
		jcbReason.addItem("기타(직접입력)");
		
		setLayout(null);
		
		//콤보박스 배경색
		jcbStartYear.setBackground(Color.white);
		jcbStartMonth.setBackground(Color.white);
		jcbStartDay.setBackground(Color.white);
		jcbEndYear.setBackground(Color.white);
		jcbEndMonth.setBackground(Color.white);
		jcbEndDay.setBackground(Color.white);
		jcbEmpNoName.setBackground(Color.white);
		jcbReason.setBackground(Color.white);
		
		//폰트
		Font jcbFont = new Font("맑은 고딕", Font.PLAIN, 14);
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 15);
		Font jtaFont = new Font("맑은 고딕", Font.PLAIN, 15);
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		
		jcbStartYear.setFont(jcbFont);
		jcbStartMonth.setFont(jcbFont);
		jcbStartDay.setFont(jcbFont);
		jcbEndYear.setFont(jcbFont);
		jcbEndMonth.setFont(jcbFont);
		jcbEndDay.setFont(jcbFont);
		jcbReason.setFont(jcbFont);
		jcbEmpNoName.setFont(jcbFont);

		jlblPeriod.setFont(jlblFont);
		jlblDuring.setFont(jlblFont);
		jlblReason.setFont(jlblFont);
		jlblStartYear.setFont(jlblFont);
		jlblStartMonth.setFont(jlblFont);
		jlblStartDay.setFont(jlblFont);
		jlblEndYear.setFont(jlblFont);
		jlblEndMonth.setFont(jlblFont);
		jlblEndDay.setFont(jlblFont);
		
		jtaWriteReason.setFont(jtaFont);
		
		jbtnApprove.setFont(jbtnFont);
		jbtnCancel.setFont(jbtnFont);
		
		//add
		add(jcbEmpNoName);
		add(jcbStartYear);
		add(jcbStartMonth);
		add(jcbStartDay);
		add(jcbEndYear);
		add(jcbEndMonth);
		add(jcbEndDay);
		add(jcbReason);
		
		add(jlblPeriod);
		add(jlblDuring);
		add(jlblReason);
		add(jlblStartYear);
		add(jlblStartMonth);
		add(jlblStartDay);
		add(jlblEndYear);
		add(jlblEndMonth);
		add(jlblEndDay);
		
		add(jtaWriteReason);
		
		add(jbtnApprove);
		add(jbtnCancel);

		// 컴포넌트 크기 및 위치 설정
		jcbEmpNoName.setBounds(35,30,180,30);
		
		jcbStartYear.setBounds(120, 82, 70, 30);
		jcbStartMonth.setBounds(230, 82, 50, 30);
		jcbStartDay.setBounds(320, 82, 50, 30);
		jcbEndYear.setBounds(445, 82, 80, 30);
		jcbEndMonth.setBounds(565, 82, 50, 30);
		jcbEndDay.setBounds(655, 82, 50, 30);
		jcbReason.setBounds(120, 130, 200, 30);
		
		jlblPeriod.setBounds(35, 80, 80, 30);
		jlblDuring.setBounds(413, 70, 50, 50);
		jlblReason.setBounds(35, 130, 100, 30);
		
		jlblStartYear.setBounds(195, 80, 100, 30);
		jlblStartMonth.setBounds(285, 80, 100, 30);
		jlblStartDay.setBounds(375, 80, 100, 30);
		jlblEndYear.setBounds(530, 80, 100, 30);
		jlblEndMonth.setBounds(620, 80, 100, 30);
		jlblEndDay.setBounds(710, 80, 100, 30);
		
		jtaWriteReason.setBounds(35,180,520,150);
		
		jbtnApprove.setBounds(590,200,135,40);
		jbtnApprove.setBackground(new Color(0x6D47B0));
		jbtnApprove.setForeground(Color.white);
		jbtnCancel.setBounds(590,270,135,40);
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		jbtnCancel.setForeground(Color.white);
		
		ApproveAbsenceDialogEvt event=new ApproveAbsenceDialogEvt(this);
		jcbReason.addActionListener(event);
		jcbStartMonth.addActionListener(event);
		jcbStartDay.addActionListener(event);
		jcbEndMonth.addActionListener(event);
		jbtnApprove.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		setTitle("휴직 처리");
		setResizable(false);
		setBounds(mer.getX()+100, mer.getY()+100, 780, 400);
		setVisible(true);
		
	}// constructor
	
	public JComboBox<Integer> getJcbStartYear() {
		return jcbStartYear;
	}

	public JComboBox<Integer> getJcbStartMonth() {
		return jcbStartMonth;
	}

	public JComboBox<Integer> getJcbStartDay() {
		return jcbStartDay;
	}

	public JComboBox<Integer> getJcbEndYear() {
		return jcbEndYear;
	}

	public JComboBox<Integer> getJcbEndMonth() {
		return jcbEndMonth;
	}

	public JComboBox<Integer> getJcbEndDay() {
		return jcbEndDay;
	}

	public JComboBox<String> getJcbReason() {
		return jcbReason;
	}

	public JTextArea getJtaWriteReason() {
		return jtaWriteReason;
	}

	public JLabel getJlblPeriod() {
		return jlblPeriod;
	}

	public JLabel getJlblDuring() {
		return jlblDuring;
	}

	public JLabel getJlblReason() {
		return jlblReason;
	}

	public JButton getJbtnApprove() {
		return jbtnApprove;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public ManageEmpRegister getMer() {
		return mer;
	}

	public JComboBox<String> getJcbEmpNoName() {
		return jcbEmpNoName;
	}

}// class
