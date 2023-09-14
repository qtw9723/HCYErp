package manageEmpRegister;

import java.awt.Color;
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
			List<String> empList = ManageDailyReportDAO.getInstance().selectEmp();
			for(String emp : empList) {
				jcbEmpNoName.addItem(emp);
			}//for
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		jcbEmpNoName.setBounds(600,70,150,40);
		
		add(jcbEmpNoName);
		

		
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
		
		//add
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
		jcbStartYear.setBounds(170, 20, 100, 30);
		jcbStartMonth.setBounds(320, 20, 100, 30);
		jcbStartDay.setBounds(470, 20, 100, 30);
		jcbEndYear.setBounds(170, 100, 100, 30);
		jcbEndMonth.setBounds(320, 100, 100, 30);
		jcbEndDay.setBounds(470, 100, 100, 30);
		jcbReason.setBounds(120, 150, 120, 30);
		
		jlblPeriod.setBounds(35, 20, 100, 30);
		jlblDuring.setBounds(370, 50, 50, 50);
		jlblReason.setBounds(35, 150, 100, 30);
		
		jlblStartYear.setBounds(280, 20, 100, 30);
		jlblStartMonth.setBounds(430, 20, 100, 30);
		jlblStartDay.setBounds(580, 20, 100, 30);
		jlblEndYear.setBounds(280, 100, 100, 30);
		jlblEndMonth.setBounds(430, 100, 100, 30);
		jlblEndDay.setBounds(580, 100, 100, 30);
		
		jtaWriteReason.setBounds(250,150,420,200);
		
		jbtnApprove.setBounds(80,230,100,30);
		jbtnApprove.setBackground(new Color(0x8244AD));
		jbtnCancel.setBounds(80,300,100,30);
		jbtnCancel.setBackground(new Color(0x5E5E5E));

		ApproveAbsenceDialogEvt event=new ApproveAbsenceDialogEvt(this);
		jcbReason.addActionListener(event);
		jcbStartMonth.addActionListener(event);
		jcbStartDay.addActionListener(event);
		jcbEndMonth.addActionListener(event);
		jbtnApprove.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		setBounds(mer.getX()+100, mer.getY()+100, 800, 400);
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
