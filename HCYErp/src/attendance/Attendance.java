package attendance;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import login.HCYErp;

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
	private HCYErp hcyE;
	private ImageIcon attend;
	private ImageIcon leave;
	private ImageIcon dayOff;
	private ImageIcon tardy;
	private JLabel jlblAttend;
	private List<JLabel> jlblList;
	private List<JLabel> dayList;

	public Attendance(HCYErp hcyE) {
		this.hcyE = hcyE;
		dayList = new ArrayList<JLabel>();

		AttendanceEvt event = new AttendanceEvt(this);

		setLayout(null);

		Font AttendanceBtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		// 출근버튼
		jbtnAttend = new JButton("출근");
		jbtnAttend.setBounds(265, 50, 150, 50);
		jbtnAttend.setBackground(new Color(0x461C90));
		jbtnAttend.setFont(AttendanceBtnFont);
		jbtnAttend.setForeground(Color.WHITE);
		jbtnAttend.addActionListener(event);
		add(jbtnAttend);
		// 퇴근버튼
		jbtnOffWork = new JButton("퇴근");
		jbtnOffWork.setBounds(515, 50, 150, 50);
		jbtnOffWork.setBackground(new Color(0x461C90));
		jbtnOffWork.setFont(AttendanceBtnFont);
		jbtnOffWork.setForeground(Color.white);
		jbtnOffWork.addActionListener(event);
		add(jbtnOffWork);
		// 휴가신청버튼
		jbtnApplyDayOff = new JButton("휴가신청");
		jbtnApplyDayOff.setBounds(765, 50, 150, 50);
		jbtnApplyDayOff.setBackground(new Color(0x461C90));
		jbtnApplyDayOff.setFont(AttendanceBtnFont);
		jbtnApplyDayOff.setForeground(Color.white);
		jbtnApplyDayOff.addActionListener(event);
		add(jbtnApplyDayOff);
		
		Font ChangePassFont = new Font("맑은 고딕", Font.BOLD, 13);
		// 비밀번호 초기화버튼
		jbtnChangePass = new JButton("비밀번호 변경");
		jbtnChangePass.setBounds(1000, 450, 150, 40);
		jbtnChangePass.setBackground(new Color(0x5E5E5E));
		jbtnChangePass.setFont(ChangePassFont);
		jbtnChangePass.setForeground(Color.WHITE);
		jbtnChangePass.addActionListener(event);
		add(jbtnChangePass);
		
		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		// 로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000, 510, 150, 40);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		jbtnLogOut.setFont(LogOutFont);
		jbtnLogOut.setForeground(Color.BLACK);
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		// 텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930, 450, 300, 300);
		add(jlblLogoTxt);
		// 이번달 근태 현황 라벨
		jlblMonthlyAttendance = new JLabel("이번달 근태 현황");
		jlblMonthlyAttendance.setBounds(200, 130, 200, 20);
		jlblMonthlyAttendance.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(jlblMonthlyAttendance);

		// 달력날짜
		Calendar cal = Calendar.getInstance();
		JLabel jlblTempDay = new JLabel();
		for (int i = 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
			cal.set(Calendar.DATE, i);
			jlblTempDay = new JLabel(Integer.toString(i));
			jlblTempDay.setBounds(205 + cal.get(Calendar.DAY_OF_WEEK) * 77, 161 + cal.get(Calendar.WEEK_OF_MONTH) * 59,
					100, 20);
			add(jlblTempDay);
			dayList.add(jlblTempDay);
		} // for

		// 연도 라벨
		Font ymFont = new Font("맑은 고딕", Font.BOLD, 17);
		JLabel jlblyear = new JLabel(Integer.toString(cal.get(Calendar.YEAR)));
		jlblyear.setBounds(260, 137, 100, 100);
		jlblyear.setFont(ymFont);
		add(jlblyear);

		// 월 라벨
		JLabel jlblmonth = new JLabel(Integer.toString(cal.get(Calendar.MONTH) + 1));
		jlblmonth.setBounds(380, 137, 100, 100);
		jlblmonth.setFont(ymFont);
		add(jlblmonth);

		// 출근도장
		// 도장 선언
		attend = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceStamp.png");
		leave = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYLeaveStamp.png");
		dayOff = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYDayoffStamp.png");
		tardy = new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTardyStamp.png");
		jlblAttend = null;
		List<JLabel> jlblList = new ArrayList<JLabel>();

		Map<Integer, AttendanceStatus> attendMap = new HashMap<Integer, AttendanceStatus>();
		try {
			attendMap = AttendanceDAO.getInstance().selectPersonalAttendance(hcyE.getUser());
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch

		for (int i = 0; i < cal.get(Calendar.DATE); i++) {
			if (attendMap.get(i + 1) != null) {
				switch (attendMap.get(i + 1)) {
				case ATTENDANCE:
					jlblAttend = new JLabel(attend);
					jlblAttend.setBounds(dayList.get(i).getX() - 50, dayList.get(i).getY() + 15, 50, 50);
					add(jlblAttend);
					jlblList.add(jlblAttend);
					break;
				case ABSENCE:
					jlblAttend = new JLabel(tardy);
					jlblAttend.setBounds(dayList.get(i).getX() - 50, dayList.get(i).getY() + 15, 50, 50);
					add(jlblAttend);
					jlblList.add(jlblAttend);
					break;
				case DAY_OFF:
					jlblAttend = new JLabel(dayOff);
					jlblAttend.setBounds(dayList.get(i).getX() - 50, dayList.get(i).getY() + 15, 50, 50);
					add(jlblAttend);
					jlblList.add(jlblAttend);
					break;
				case LEAVE:
					jlblAttend = new JLabel(leave);
					jlblAttend.setBounds(dayList.get(i).getX() - 50, dayList.get(i).getY() + 15, 50, 50);
					add(jlblAttend);
					jlblList.add(jlblAttend);
					break;
				default:
				}// switch
			} // if
		} // for

		// 달력배경
		jlblCalendar = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYAttendanceCalendar.png"));
		jlblCalendar.setBounds(200, 180, 580, 400);
		add(jlblCalendar);

		// 남은 연가 폰트
		Font leftDayOffFont = new Font("맑은 고딕", Font.BOLD, 20);
		// 남은 연가
		StringBuilder leftDayOff = new StringBuilder();
		try {
			int[] dayOffArr = AttendanceDAO.getInstance().selectLeftDayOff(hcyE.getUser());
			leftDayOff.append(dayOffArr[1] - dayOffArr[0]).append("일 / ").append(dayOffArr[1]).append("일");
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
			// 보더타이틀 설정
		jlblLeftDayOff = new JLabel(leftDayOff.toString());
		TitledBorder tb = new TitledBorder("남은 연가");
		tb.setTitleFont(leftDayOffFont);
		jlblLeftDayOff.setBorder(tb);
		// 남은 연가 라벨
		jlblLeftDayOff.setFont(leftDayOffFont);
		jlblLeftDayOff.setBounds(800, 220, 300, 100);
		add(jlblLeftDayOff);

		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);

	}// constructor

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

	public HCYErp getHcyE() {
		return hcyE;
	}

	public ImageIcon getAttend() {
		return attend;
	}

	public ImageIcon getLeave() {
		return leave;
	}

	public ImageIcon getDayOff() {
		return dayOff;
	}

	public ImageIcon getTardy() {
		return tardy;
	}

	public JLabel getJlblAttend() {
		return jlblAttend;
	}

	public List<JLabel> getJlblList() {
		return jlblList;
	}

	public List<JLabel> getDayList() {
		return dayList;
	}
	

}// class
