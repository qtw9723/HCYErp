package manageEmpRegister;

import java.awt.Button;
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

import manageDailyReport.ManageDailyReportDAO;

public class ApproveResignationDialog extends JDialog {
	private ManageEmpRegister mer;
	private JComboBox<String> jcbEmpNoName;
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JComboBox<Integer> jcbDay;
	private JTextArea jtaReason;
	private JButton jbtnApprove;
	private JButton jbtnCancel;
	private JLabel jlblLastDate;
	private JLabel jlblYear;
	private JLabel jlblMonth;
	private JLabel jlblDay;
	
	public ApproveResignationDialog(ManageEmpRegister mer) {
		this.mer=mer;
		
		getContentPane().setBackground(new Color(255,245,245));
		
		//Calendar선언
		Calendar cal=Calendar.getInstance();
		
		//퇴사년,월,일 선언
		jcbYear=new JComboBox<Integer>();
		jcbMonth=new JComboBox<Integer>();
		jcbDay=new JComboBox<Integer>();
		
		//JLabel
		jlblLastDate=new JLabel("퇴사일 : ");
		jlblYear=new JLabel("년");
		jlblMonth=new JLabel("월");
		jlblDay=new JLabel("일");
				
		//JButton선언
		jbtnApprove=new JButton("퇴사처리");
		jbtnCancel=new JButton("취소");
		
		//JTextarea선언
		jtaReason=new JTextArea("퇴사 사유를 적어주세요.");
		jtaReason.setLineWrap(true);
		
		//사원번호/이름 콤보박스 선언
		jcbEmpNoName=new JComboBox<String>();
		try {
			List<String> empList=ManageDailyReportDAO.getInstance().selectEmp(mer.getHcyE().getUser());
			for(String emp:empList) {
				jcbEmpNoName.addItem(emp);
			}//end for
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
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
		
		//콤보박스 배경색
		jcbEmpNoName.setBackground(Color.white);
		jcbYear.setBackground(Color.white);
		jcbMonth.setBackground(Color.white);
		jcbDay.setBackground(Color.white);
		
		//폰트
		Font jcbFont = new Font("맑은 고딕", Font.PLAIN, 14);
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 15);
		Font jtaFont = new Font("맑은 고딕", Font.PLAIN, 15);
		
		jcbEmpNoName.setFont(jcbFont);
		jcbYear.setFont(jcbFont);
		jcbMonth.setFont(jcbFont);
		jcbDay.setFont(jcbFont);
		jbtnApprove.setFont(jbtnFont);
		jbtnCancel.setFont(jbtnFont);
		jlblLastDate.setFont(jlblFont);
		jlblYear.setFont(jlblFont);
		jlblMonth.setFont(jlblFont);
		jlblDay.setFont(jlblFont);
		jtaReason.setFont(jtaFont);
		
		//add
		add(jcbEmpNoName);
		add(jbtnApprove);
		add(jbtnCancel);
		add(jcbYear);
		add(jcbMonth);
		add(jcbDay);
		add(jlblLastDate);
		add(jlblYear);
		add(jlblMonth);
		add(jlblDay);
		add(jtaReason);
		
		jtaReason.setBounds(30,100,305,220);
		jlblLastDate.setBounds(30,350,100,30);
		jcbEmpNoName.setBounds(110, 40, 150, 30);
		jcbYear.setBounds(90,350,70,30);
		jcbMonth.setBounds(190,350,50,30);
		jcbDay.setBounds(270,350,50,30);
		jlblYear.setBounds(165,350,50,30);
		jlblMonth.setBounds(245,350,50,30);
		jlblDay.setBounds(325,350,50,30 );
		jbtnApprove.setBounds(40,410,135,40);
		jbtnApprove.setBackground(new Color(0x6D47B0));
		jbtnApprove.setForeground(Color.white);
		jbtnCancel.setBounds(190,410,135,40);
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		jbtnCancel.setForeground(Color.white);
		
		ApproveResignationDialogEvt event=new ApproveResignationDialogEvt(this);
		jcbYear.addActionListener(event);
		jcbMonth.addActionListener(event);
		jcbDay.addActionListener(event);
		jbtnApprove.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		setTitle("퇴사 처리");
		setVisible(true);
		setBounds(mer.getX()+150,mer.getY()+150,385,520);
		
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

