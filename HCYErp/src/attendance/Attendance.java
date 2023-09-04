package attendance;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
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
		
		AttendanceEvt event= new AttendanceEvt(this); 
		
		setLayout(null);
		
		//출근버튼
		jbtnAttend = new JButton("출근");
		jbtnAttend.setBounds(230,50,150,50);
		jbtnAttend.addActionListener(event);
		add(jbtnAttend);
		//퇴근버튼
		jbtnOffWork = new JButton("퇴근");
		jbtnOffWork.setBounds(480,50,150,50);
		jbtnOffWork.addActionListener(event);
		add(jbtnOffWork);
		//휴가신청버튼
		jbtnApplyDayOff = new JButton("휴가신청");
		jbtnApplyDayOff.setBounds(730,50,150,50);
		jbtnApplyDayOff.addActionListener(event);
		add(jbtnApplyDayOff);
		//비밀번호 초기화버튼
		jbtnChangePass = new JButton("비밀번호초기화");
		jbtnChangePass.setBounds(1000,450,150,40);
		jbtnChangePass.addActionListener(event);
		add(jbtnChangePass);
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		//이번달 근태 현황 라벨
		jlblMonthlyAttendance = new JLabel("이번달 근태 현황");
		jlblMonthlyAttendance.setBounds(200,130,200,20);
		jlblMonthlyAttendance.setFont(new Font("맑은 고딕",Font.BOLD,20));
		add(jlblMonthlyAttendance);
		
		//달력날짜
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
		
		//출근도장
		//도장 선언
		ImageIcon attend = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceStamp.png");
		ImageIcon dayoff = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYDayoffStamp.png");
		ImageIcon getoff = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYGetoffStamp.png");
		ImageIcon tardy = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTardyStamp.png");
		JLabel jlblAttend = new JLabel(attend);
		JLabel jlblDayoff = new JLabel(dayoff);
		JLabel jlblGetoff = new JLabel(getoff);
		JLabel jlblTardy = new JLabel(tardy);
		jlblAttend.setBounds(100,100,160,160);
		jlblDayoff.setBounds(200,100,160,160);
		jlblGetoff.setBounds(300,100,160,160);
		jlblTardy.setBounds(400,100,160,160);
		add(jlblAttend);
		add(jlblDayoff);
		add(jlblGetoff);
		add(jlblTardy);
		
		//달력배경
		jlblCalendar = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceCalendar.png"));
		jlblCalendar.setBounds(200,180,580,400);
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
		//남은 연가 라벨
		jlblLeftDayOff.setFont(leftDayOffFont);
		jlblLeftDayOff.setBounds(800,220,300,100);
		add(jlblLeftDayOff);
		
		
		
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		
		
	}//constructor

	public JButton getJbtnAttend() {
		return jbtnAttend;
	}

	public JButton getJbtnOffWork() {
		return jbtnOffWork;
	}

	public JButton getJbtnApplyDayOff() {
		return jbtnApplyDayOff;
	}

	public JLabel getJlblMonthlyAttendance() {
		return jlblMonthlyAttendance;
	}

	public JLabel getJlblCalendar() {
		return jlblCalendar;
	}

	public JLabel getJlblLeftDayOff() {
		return jlblLeftDayOff;
	}

	public JButton getJbtnChangePass() {
		return jbtnChangePass;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}
	
}//class
