package attendance;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Attendance extends JPanel {
	private JButton jbtnAttend;
	private JButton jbtnOffWork;
	private JButton jbtnApplyDayOff;
	private JLabel jlblMonthlyAttendance;
	private JLabel jlblCalendar;
	private JLabel jlblLeftDayOff;
	private JButton jbtnChangePass;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	
	public Attendance() {
		List<JLabel> dayList = new ArrayList<JLabel>();
		
		setLayout(null);
		
		//출근버튼
		jbtnAttend = new JButton("출근");
		jbtnAttend.setBounds(230,100,150,50);
		add(jbtnAttend);
		//퇴근버튼
		jbtnOffWork = new JButton("퇴근");
		jbtnOffWork.setBounds(480,100,150,50);
		add(jbtnOffWork);
		//휴가신청버튼
		jbtnApplyDayOff = new JButton("휴가신청");
		jbtnApplyDayOff.setBounds(730,100,150,50);
		add(jbtnApplyDayOff);
		//비밀번호 초기화버튼
		jbtnChangePass = new JButton("비밀번호초기화");
		jbtnChangePass.setBounds(1000,450,150,40);
		add(jbtnChangePass);
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		add(jbtnLogOut);
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		//이번달 근태 현황 라벨
		jlblMonthlyAttendance = new JLabel("이번달 근태 현황");
		jlblMonthlyAttendance.setBounds(200,180,200,20);
		jlblMonthlyAttendance.setFont(new Font("맑은 고딕",Font.BOLD,20));
		add(jlblMonthlyAttendance);
		
		//달력날짜
		Calendar cal = Calendar.getInstance();
		JLabel jlblTempDay = new JLabel();
		for(int i=1;i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH);i++) {
			cal.set(Calendar.DATE, i);
			jlblTempDay=new JLabel(Integer.toString(i));
			jlblTempDay.setBounds(205+cal.get(Calendar.DAY_OF_WEEK)*77,191+cal.get(Calendar.WEEK_OF_MONTH)*59,100,20);
			add(jlblTempDay);
			dayList.add(jlblTempDay);
		}//for
		
		
		//달력배경
		jlblCalendar = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceCalendar.png"));
		jlblCalendar.setBounds(200,210,580,400);
		add(jlblCalendar);
		
		//남은 연가 폰트
		Font leftDayOffFont = new Font("맑은 고딕",Font.BOLD,20);
		//남은 연가
		StringBuilder leftDayOff = new StringBuilder();
//		leftDayOff.append()\
		//보더타이틀 설정
		jlblLeftDayOff = new JLabel("남은연가 대충 따닥");
		TitledBorder tb= new TitledBorder("남은 연가");
		tb.setTitleFont(leftDayOffFont);
		jlblLeftDayOff.setBorder(tb);
		//라벨설정
		jlblLeftDayOff.setFont(leftDayOffFont);
		jlblLeftDayOff.setBounds(800,250,300,100);
		add(jlblLeftDayOff);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		
		
	}//constructor
}//class
