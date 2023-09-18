package manageAttendance;


import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import attendance.AttendanceDAO;
import attendance.AttendanceStatus;
import login.HCYErp;

@SuppressWarnings("serial")
public class ManagePersonalAttendance extends JPanel{
	private JButton jbtnAttendName;
	private JCheckBox jcbAbsent;
	private JCheckBox jcbDayOff;
	private JComboBox<String> jcbEmp;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JLabel jlblCalendar;
	private List<JLabel> dayList;
	private List<JLabel> jlblAttendList;


	private HCYErp hcyE;
	
	public ManagePersonalAttendance(HCYErp hcyE) { 
		this.hcyE = hcyE;
		setLayout(null);
		ManagePersonalAttendanceEvt event = new ManagePersonalAttendanceEvt(this);
		
		//달력날짜
		dayList = new ArrayList<JLabel>();
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
		JLabel jlblmonth =new JLabel(Integer.toString(cal.get(Calendar.MONTH)+1));
		jlblmonth.setBounds(380,137,100,100);
		jlblmonth.setFont(ymFont);
		add(jlblmonth);
		
		
		Font jcbFont = new Font("맑은 고딕", Font.PLAIN, 13);
		//사원 콤박
		jcbEmp = new JComboBox<String>();
		List<String> empList = new ArrayList<String>();
		try {
			if(ManageAttendanceDAO.getInstance().selectTeamNo(hcyE.getUser())==13||ManageAttendanceDAO.getInstance().selectTeamNo(hcyE.getUser())==91) {
			empList = ManageAttendanceDAO.getInstance().selectEmpBoss();
			}else {
			empList = ManageAttendanceDAO.getInstance().selectEmp(hcyE.getUser());
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(jlblmonth, "데이터 베이스에 오류가 발생했습니다.");
		}//catch
		for(String emp:empList) {
			jcbEmp.addItem(emp);
		}//for
		jcbEmp.setFont(jcbFont);
		jcbEmp.setBounds(300,70,130,40);
		jcbEmp.setBackground(new Color(0xffffff));
		add(jcbEmp);
		
		//조회버튼
		jbtnAttendName = new JButton("조회");
		jbtnAttendName.setBounds(700,70,130,40);
		jbtnAttendName.setBackground(new Color(0x5E5E5E));
		Font AttendBtnFont = new Font("맑은 고딕",  Font.BOLD, 13);
		jbtnAttendName.setFont(AttendBtnFont);
		jbtnAttendName.setForeground(Color.white);
		jbtnAttendName.addActionListener(event);
		add(jbtnAttendName);
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		jbtnLogOut.setFont(LogOutFont);
		jbtnLogOut.setForeground(Color.BLACK);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		// 달력배경
		jlblCalendar = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceCalendar.png"));
		jlblCalendar.setBounds(200, 180, 580, 400);
		add(jlblCalendar);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		hcyE.getList().add(this);
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

	public JLabel getJlblCalendar() {
		return jlblCalendar;
	}

	public List<JLabel> getDayList() {
		return dayList;
	}

	public List<JLabel> getJlblAttendList() {
		return jlblAttendList;
	}

	public void setJlblAttendList(List<JLabel> jlblAttendList) {
		this.jlblAttendList = jlblAttendList;
	}
	
}//class
