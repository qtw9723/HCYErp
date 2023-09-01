package attendance;

import java.awt.Font;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		jbtnChangePass.setBounds(1000,430,150,40);
		add(jbtnChangePass);
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,490,150,40);
		add(jbtnLogOut);
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/질문이 싫은 홍찬영.jpg"));
		jlblLogoTxt.setBounds(880,520,300,300);
		add(jlblLogoTxt);
		//이번달 근태 현황 라벨
		jlblMonthlyAttendance = new JLabel("이번달 근태 현황");
		jlblMonthlyAttendance.setBounds(200,180,200,20);
		jlblMonthlyAttendance.setFont(new Font("맑은 고딕",Font.BOLD,20));
		add(jlblMonthlyAttendance);
		//달력배경
		jlblCalendar = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceCalendar.png"));
		jlblCalendar.setBounds(200,210,580,400);
		add(jlblCalendar);
		
		//달력설정
		for(int i=0;i<Calendar.;) {
			
		}//for
		//남은 연가
		StringBuilder leftDayOff = new StringBuilder();
//		leftDayOff.append()
		jlblLeftDayOff = new JLabel();
		jlblLeftDayOff.setBounds(200,210,580,400);
		add(jlblLeftDayOff);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		
		
	}//constructor
}//class
