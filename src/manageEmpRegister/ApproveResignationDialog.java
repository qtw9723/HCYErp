package manageEmpRegister;

import java.awt.Button;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import manageDailyReport.ManageDailyReportDAO;

public class ApproveResignationDialog extends JDialog {
	JComboBox<String> jcbEmpNoName;
	JTextArea jtaReason;
	
	JButton jbtnApprove;
	JButton jbtnCancel;
	
	JLabel jlblLastDate;
	
	JComboBox<Integer> jcbYear;
	JComboBox<Integer> jcbMonth;
	JComboBox<Integer> jcbDay;
	
	private ManageEmpRegister mer;
	
	public ApproveResignationDialog(ManageEmpRegister mer) {
		this.mer=mer;
		
		//Calendar선언
		Calendar cal=Calendar.getInstance();
		
		//퇴사년,월,일 선언
		jcbYear=new JComboBox<Integer>();
		jcbMonth=new JComboBox<Integer>();
		jcbDay=new JComboBox<Integer>();
		
		//JLabel
		jlblLastDate=new JLabel("퇴사일 : ");
		
		//JButton선언
		jbtnApprove=new JButton("퇴사처리");
		jbtnCancel=new JButton("취소");
		
		//JTextarea선언
		jtaReason=new JTextArea();
		jtaReason.setLineWrap(true);
		
		//사원번호/이름 콤보박스 선언
		jcbEmpNoName=new JComboBox<String>();
		try {
			List<String> empList=ManageDailyReportDAO.getInstance().selectEmp();
			for(String emp:empList) {
				jcbEmpNoName.addItem(emp);
			}//end for
		
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		jcbEmpNoName.setBounds(100, 40, 150, 40);
		
		//현재 년 설정
		int year=cal.get(Calendar.YEAR);
		
		//JComboBox에 년 월 일 추가
		for(int i=year;i<year+2;i++) {
			jcbYear.addItem(i);
		}//end for
		
		for(int i=1;i<13;i++) {
			jcbMonth.addItem(i);
		}//end for
		
		for(int i=1;i<cal.getActualMaximum(Calendar.DAY_OF_MONTH);i++) {
			jcbDay.addItem(i);
		}//end for
		
		setLayout(null);
		
		add(jcbEmpNoName);
		add(jbtnApprove);
		add(jbtnCancel);
		add(jcbYear);
		add(jcbMonth);
		add(jcbDay);
		add(jlblLastDate);
		add(jtaReason);
		
		jtaReason.setBounds(30,100,300,200);
		jlblLastDate.setBounds(30,350,100,30);
		jcbYear.setBounds(110,350,100,30);
		jcbMonth.setBounds(230,350,100,30);
		jcbDay.setBounds(350,350,100,30);
		jbtnApprove.setBounds(50,400,100,30);
		jbtnApprove.setBackground(new Color(0x8244AD));
		jbtnCancel.setBounds(200,400,100,30);
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		
		ApproveResignationDialogEvt event=new ApproveResignationDialogEvt(this);
		jcbYear.addActionListener(event);
		jcbMonth.addActionListener(event);
		jcbDay.addActionListener(event);
		jbtnApprove.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		setVisible(true);
		setBounds(mer.getX()+150,mer.getY()+150,500,500);
		
	}// constructor


	//getter
	public JComboBox<String> getJcbEmpNoName() {
		return jcbEmpNoName;
	}

	public JTextArea getJtaReason() {
		return jtaReason;
	}

	public JButton getJbtnApprove() {
		return jbtnApprove;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JLabel getJlblLastDate() {
		return jlblLastDate;
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

	public ManageEmpRegister getMer() {
		return mer;
	}
	
}// class

