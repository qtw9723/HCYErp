package manageAttendance;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.HCYErp;

@SuppressWarnings("serial")
public class ManagePersonalAttendance extends JPanel{
	private JButton jbtnAttendName;
	private JCheckBox jcbAbsent;
	private JCheckBox jcbDayOff;
	private JComboBox<String> jcbEmp;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;


	private HCYErp hcyE;
	
	public ManagePersonalAttendance(HCYErp hcyE) { 
		this.hcyE = hcyE;
		setLayout(null);
		ManagePersonalAttendanceEvt event = new ManagePersonalAttendanceEvt(this);
		
		//달력날짜
		List<JLabel> dayList = new ArrayList<JLabel>();
		Calendar cal = Calendar.getInstance();
		JLabel jlblTempDay = new JLabel();
		for(int i=1;i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH);i++) {
			cal.set(Calendar.DATE, i);
			jlblTempDay=new JLabel(Integer.toString(i));
			jlblTempDay.setBounds(205+cal.get(Calendar.DAY_OF_WEEK)*77,161+cal.get(Calendar.WEEK_OF_MONTH)*59,100,20);
			add(jlblTempDay);
			dayList.add(jlblTempDay);
		}//for
		
		//연도 라벨
		Font ymFont = new Font("맑은 고딕",Font.BOLD,17);
		JLabel jlblyear =new JLabel(Integer.toString(cal.get(Calendar.YEAR)));
		jlblyear.setBounds(260,137,100,100);
		jlblyear.setFont(ymFont);
		add(jlblyear);
		
		//월 라벨
		JLabel jlblmonth =new JLabel(Integer.toString(cal.get(Calendar.MONTH)));
		jlblmonth.setBounds(380,137,100,100);
		jlblmonth.setFont(ymFont);
		add(jlblmonth);
		
		//사원 콤박
		jcbEmp = new JComboBox<String>();
		for(int i = 0 ;i<100;i++) {
		jcbEmp.addItem(i+"번사원");
		}//다오 나오면 삭제
		jcbEmp.setBounds(300,70,130,40);
		jcbEmp.setBackground(new Color(0xffffff));
		add(jcbEmp);
		
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


	public JButton getJbtnAttendName() {
		return jbtnAttendName;
	}

	public JCheckBox getJcbAbsent() {
		return jcbAbsent;
	}

	public JCheckBox getJcbDayOff() {
		return jcbDayOff;
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
