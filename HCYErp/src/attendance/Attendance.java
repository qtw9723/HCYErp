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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import login.HCYErp;
import login.HCYErpDAO;

@SuppressWarnings("serial")
public class Attendance extends JPanel {
	private JLabel jlblMonthlyAttendance;
	private JButton jbtnApplyDayOff;
	private JButton jbtnChangePass;
	private JLabel jlblLeftDayOff;
	private List<JLabel> jlblList;
	private List<JLabel> dayList;
	private JButton jbtnOffWork;
	private JLabel jlblCalendar;
	private JButton jbtnAttend;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JLabel jlblAttend;
	private ImageIcon attend;
	private ImageIcon dayOff;
	private ImageIcon leave;
	private ImageIcon tardy;
	private HCYErp hcyE;

	public Attendance(HCYErp hcyE) {
		this.hcyE = hcyE;

		// 이벤트 등록
		AttendanceEvt event = new AttendanceEvt(this);
		setLayout(null);

		Font AttendanceBtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		// 출근버튼
		jbtnAttend = new JButton("출근"); 
		jbtnAttend.setBackground(new Color(0x3322A8));
		jbtnAttend.setBounds(265, 50, 150, 50);
		jbtnAttend.setFont(AttendanceBtnFont);
		jbtnAttend.setForeground(Color.WHITE);
		jbtnAttend.addActionListener(event);
		add(jbtnAttend);
		// 퇴근버튼
		jbtnOffWork = new JButton("퇴근");
		jbtnOffWork.setBackground(new Color(0x3322A8));
		jbtnOffWork.setBounds(515, 50, 150, 50);
		jbtnOffWork.setFont(AttendanceBtnFont);
		jbtnOffWork.setForeground(Color.white);
		jbtnOffWork.addActionListener(event);
		add(jbtnOffWork);
		// 휴가신청버튼
		jbtnApplyDayOff = new JButton("휴가신청");
		jbtnApplyDayOff.setBackground(new Color(0x3322A8));
		jbtnApplyDayOff.setBounds(765, 50, 150, 50);
		jbtnApplyDayOff.setFont(AttendanceBtnFont);
		jbtnApplyDayOff.setForeground(Color.white);
		jbtnApplyDayOff.addActionListener(event);
		add(jbtnApplyDayOff);

		Font ChangePassFont = new Font("맑은 고딕", Font.BOLD, 13);
		// 비밀번호 초기화버튼
		jbtnChangePass = new JButton("비밀번호 변경");
		jbtnChangePass.setBackground(new Color(0x5E5E5E));
		jbtnChangePass.setBounds(1000, 450, 150, 40);
		jbtnChangePass.setForeground(Color.WHITE);
		jbtnChangePass.addActionListener(event);
		jbtnChangePass.setFont(ChangePassFont);
		add(jbtnChangePass);

		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		// 로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		jbtnLogOut.setBounds(1000, 510, 150, 40);
		jbtnLogOut.setForeground(Color.BLACK);
		jbtnLogOut.addActionListener(event);
		jbtnLogOut.setFont(LogOutFont);
		add(jbtnLogOut);

		// 텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/HCYErpFile/images/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(913, 450, 300, 300);
		add(jlblLogoTxt);
		// 이번달 근태 현황 라벨
		jlblMonthlyAttendance = new JLabel("이번달 근태 현황");
		jlblMonthlyAttendance.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		jlblMonthlyAttendance.setBounds(225, 133, 200, 20);
		add(jlblMonthlyAttendance);

		// 달력날짜
		Calendar cal = Calendar.getInstance();
		JLabel jlblTempDay = new JLabel();
		dayList = new ArrayList<JLabel>();
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
		jlblyear.setBounds(270, 137, 100, 100);
		jlblyear.setFont(ymFont);
		add(jlblyear);

		// 월 라벨
		JLabel jlblmonth = new JLabel(Integer.toString(cal.get(Calendar.MONTH) + 1));
		jlblmonth.setBounds(390, 137, 100, 100);
		jlblmonth.setFont(ymFont);
		add(jlblmonth);

		// 출근도장
		// 도장 선언
		attend = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYAttendanceStamp.png");
		dayOff = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYDayoffStamp.png");
		leave = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYLeaveStamp.png");
		tardy = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYTardyStamp.png");
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
		jlblCalendar = new JLabel(new ImageIcon("C:/Users/user/HCYErpFile/images/HCYAttendanceCalendar.png"));
		jlblCalendar.setBounds(200, 180, 580, 400);
		add(jlblCalendar);

		// 남은 연가 폰트
		Font leftDayOffFont = new Font("맑은 고딕", Font.BOLD, 18);
		Font leftDayOffContFont = new Font("맑은 고딕", Font.PLAIN, 17);
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
		jlblLeftDayOff.setBounds(800, 220, 150, 100);
		jlblLeftDayOff.setFont(leftDayOffContFont);
		jlblLeftDayOff.setHorizontalAlignment(SwingConstants.CENTER);
		add(jlblLeftDayOff);

		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/HCYErpFile/images/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);

		
		List<String> msgList = new ArrayList<String>();
		
		try {
			msgList = HCYErpDAO.getInstance().selectMSG(hcyE.getUser());
			HCYErpDAO.getInstance().updateMSG(hcyE.getUser());
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		if(msgList.size()!=0) {
			JOptionPane.showMessageDialog(jlblBG, msgList.size()+"개의 메세지가 도착했습니다.");
		for(String msg : msgList) {
			JOptionPane.showMessageDialog(jlblBG, msg);
		}//for
		}//if
	}// constructor

	public JLabel getJlblMonthlyAttendance() {
		return jlblMonthlyAttendance;
	}
	public JButton getJbtnApplyDayOff() {
		return jbtnApplyDayOff;
	}
	public JButton getJbtnChangePass() {
		return jbtnChangePass;
	}
	public JLabel getJlblLeftDayOff() {
		return jlblLeftDayOff;
	}
	public List<JLabel> getJlblList() {
		return jlblList;
	}
	public List<JLabel> getDayList() {
		return dayList;
	}
	public JLabel getJlblCalendar() {
		return jlblCalendar;
	}
	public JButton getJbtnOffWork() {
		return jbtnOffWork;
	}
	public JButton getJbtnAttend() {
		return jbtnAttend;
	}
	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}
	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}
	public JLabel getJlblAttend() {
		return jlblAttend;
	}
	public ImageIcon getDayOff() {
		return dayOff;
	}
	public ImageIcon getAttend() {
		return attend;
	}
	public ImageIcon getTardy() {
		return tardy;
	}
	public ImageIcon getLeave() {
		return leave;
	}
	public HCYErp getHcyE() {
		return hcyE;
	}

}// class
