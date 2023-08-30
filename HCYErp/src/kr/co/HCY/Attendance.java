package kr.co.HCY;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Attendance extends JPanel {
	private JButton jbtnAttend;
	private JButton jbtnOffWork;
	private JButton jbtnApplyDay;
	private JLabel jlblMonthlyAttendance;
	private JLabel jlblCalendar;
	private JLabel jlblLeftDay;
	private JButton jbtnChangePass;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	
	public Attendance() {
		jbtnLogOut = new JButton("아니");
		add(jbtnLogOut);
	}// constructor
}//class
