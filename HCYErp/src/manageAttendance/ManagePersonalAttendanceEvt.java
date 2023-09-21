package manageAttendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import attendance.AttendanceStatus;

public class ManagePersonalAttendanceEvt extends MouseAdapter implements ActionListener {

	private ManagePersonalAttendance ma;

	public ManagePersonalAttendanceEvt(ManagePersonalAttendance ma) {
		this.ma = ma;
	}// Constructor

	@Override
	public void actionPerformed(ActionEvent e) {

		// 조회버튼
		if (e.getSource() == ma.getJbtnAttendName()) {
			searchAttendanceName();
		} // if

		// 로그아웃
		if (e.getSource() == ma.getJbtnLogOut()) {
			logOut();
		} // if
	}// actionPerformed

	private void logOut() {
		ma.getHcyE().getTabbedPane().setVisible(false);
		ma.getHcyE().addComponent();
		ma.getHcyE().setUser(0);
	}//logOut

	private void searchAttendanceName() {
		// 도장 선언
		ImageIcon attend = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYAttendanceStamp.png");
		ImageIcon leave = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYLeaveStamp.png");
		ImageIcon dayOff = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYDayoffStamp.png");
		ImageIcon tardy = new ImageIcon("C:/Users/user/HCYErpFile/images/HCYTardyStamp.png");
		JLabel jlblAttend = null;

		if (ma.getJlblAttendList() == null) {
			ma.setJlblAttendList(new ArrayList<JLabel>());
		} // if

		// 이전에 추가된 도장 제거
		for (JLabel label : ma.getJlblAttendList()) {
			ma.remove(label);
		} // for
		ma.revalidate();
		ma.repaint();
		ma.getJlblAttendList().clear();

		// 도장추가
		Map<Integer, AttendanceStatus> attendMap = new HashMap<Integer, AttendanceStatus>();
		try {
			String emp = ma.getJcbEmp().getSelectedItem().toString();
			attendMap = ManageAttendanceDAO.getInstance()
					.selectNoAttend(Integer.parseInt(emp.substring(0, emp.indexOf("/"))));
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(jlblAttend, "데이터베이스에 문제가 발생했습니다. 기술팀에 문의해주세요!");
		} // catch

		Calendar cal = Calendar.getInstance();
		// 상황에 맞는 도장 설정
		for (int i = 0; i < cal.get(Calendar.DATE); i++) {
			if (attendMap.get(i + 1) != null) {
				switch (attendMap.get(i + 1)) {
				// 출근도장 쾅
				case ATTENDANCE:
					jlblAttend = new JLabel(attend);
					jlblAttend.setBounds(ma.getDayList().get(i).getX() - 50, ma.getDayList().get(i).getY() + 15, 50, 50);
					ma.add(jlblAttend);
					ma.setComponentZOrder(jlblAttend, 0);
					ma.repaint();
					ma.getJlblAttendList().add(jlblAttend);
					break;
				// 지각도장 쾅
				case ABSENCE:
					jlblAttend = new JLabel(tardy);
					jlblAttend.setBounds(ma.getDayList().get(i).getX() - 50, ma.getDayList().get(i).getY() + 15, 50,
							50);
					ma.add(jlblAttend);
					ma.setComponentZOrder(jlblAttend, 0);
					ma.repaint();
					ma.getJlblAttendList().add(jlblAttend);
					break;
				// 휴가도장 쾅
				case DAY_OFF:
					jlblAttend = new JLabel(dayOff);
					jlblAttend.setBounds(ma.getDayList().get(i).getX() - 50, ma.getDayList().get(i).getY() + 15, 50,
							50);
					ma.add(jlblAttend);
					ma.setComponentZOrder(jlblAttend, 0);
					ma.repaint();
					ma.getJlblAttendList().add(jlblAttend);
					break;
				// 휴직도장 쾅
				case LEAVE:
					jlblAttend = new JLabel(leave);
					jlblAttend.setBounds(ma.getDayList().get(i).getX() - 50, ma.getDayList().get(i).getY() + 15, 50,
							50);
					ma.add(jlblAttend);
					ma.setComponentZOrder(jlblAttend, 0);
					ma.repaint();
					ma.getJlblAttendList().add(jlblAttend);
					break;
				default:
				}// switch
			} // if
		} // for
	}//searchAttendanceName

}// class
