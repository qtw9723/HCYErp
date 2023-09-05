package manageAttendance;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.HCYErp;

@SuppressWarnings("serial")
public class ManagePersonalAttendance extends JPanel{
	private JButton jbtnAttendDate;
	private JButton jbtnAttendName;
	private JCheckBox jcbAbsent;
	private JCheckBox jcbDayOff;
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JComboBox<String> jcbEmp;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;


	private HCYErp hcyE;
	
	public ManagePersonalAttendance(HCYErp hcyE) { 
		this.hcyE = hcyE;
		setLayout(null);
		ManagePersonalAttendanceEvt event = new ManagePersonalAttendanceEvt(this);
		
		//연월일 콤박
		//연 콤박
		jcbYear = new JComboBox<Integer>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		jcbYear.addItem(year-1);
		jcbYear.addItem(year);
		jcbYear.addItem(year+1);
		jcbYear.setBounds(100,100,130,40);
		jcbYear.setBackground(new Color(0xffffff));
		add(jcbYear);
		//월 콤박
		jcbMonth = new JComboBox<Integer>();
		for(int i = 1;i<13;i++) {
			jcbMonth.addItem(i);
		}//for
		jcbMonth.setBounds(260,100,130,40);
		jcbMonth.setBackground(new Color(0xffffff));
		add(jcbMonth);
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
	}//Constructor

	public HCYErp getHcyE() {
		return hcyE;
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

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<String> getJcbEmp() {
		return jcbEmp;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}
	
}//class
