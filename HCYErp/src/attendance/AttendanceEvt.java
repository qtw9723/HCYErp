package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AttendanceEvt extends MouseAdapter implements ActionListener {

	private Attendance ad;

	public AttendanceEvt(Attendance ad) {
		this.ad = ad;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// 비밀번호 변경
		if (e.getSource() == ad.getJbtnChangePass()) {
			new ChangePassDialog(ad).setBounds(ad.getHcyE().getX() + 200, ad.getHcyE().getY() + 100, 360, 320);
		} // if

		// 로그아웃
		if (e.getSource() == ad.getJbtnLogOut()) {
			logOut();
		} // if

		// 출근
		if (e.getSource() == ad.getJbtnAttend()) {
			try {
				attend();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if

		// 퇴근
		if (e.getSource() == ad.getJbtnOffWork()) {
			try {
				getOff();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if

		// 휴가신청
		if (e.getSource() == ad.getJbtnApplyDayOff()) {
			new ApplyDayOffDialog(ad);
		} // if
	}// actionPerformed

	private void attend() throws SQLException {
		// 출근 버튼 눌렀을 때 발생할 수 있는 예외처리
		boolean workdFlag = AttendanceDAO.getInstance().selectWorkedFlag(ad.getHcyE().getUser());
		boolean workingFlag = AttendanceDAO.getInstance().selectWorkingFlag(ad.getHcyE().getUser());
		boolean toDayFlag = AttendanceDAO.getInstance().selectTodayWork(ad.getHcyE().getUser());
		// 전에 퇴근 눌렀는지 확인
		if (workdFlag) {
			JOptionPane.showMessageDialog(ad, "퇴근처리하지 않은 날이 있습니다. 자동으로 퇴근처리 합니다. 다시 출근을 눌러주세요!","출근",JOptionPane.INFORMATION_MESSAGE);
			int updateFlag = AttendanceDAO.getInstance().updateGetOff(ad.getHcyE().getUser());
			if (updateFlag == 1) {
				JOptionPane.showMessageDialog(ad, "정상적으로 퇴근처리 되었습니다! ","퇴근",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(ad, "오류가 발생했습니다. 출근버튼을 눌렀는지 확인해주세요!","출근",JOptionPane.INFORMATION_MESSAGE);
			} // else
			return;
		} // if
			// 이미 출근 눌렀는지 확인
		if (workingFlag) {
			JOptionPane.showMessageDialog(ad, "이미 출근 했습니다!","출근",JOptionPane.INFORMATION_MESSAGE);
			return;
		} // if
			// 이미 퇴근 눌렀는지 확인
		if (toDayFlag) {
			JOptionPane.showMessageDialog(ad, "이미 퇴근했습니다!","퇴근",JOptionPane.INFORMATION_MESSAGE);
			return;
		} // if

		// 출근 처리
		AttendanceDAO.getInstance().insertAttendance(ad.getHcyE().getUser());

		JLabel jlblAttend = null;
		if (Calendar.getInstance().get(Calendar.HOUR) < AttendanceDAO.WORK_START_TIME) {
			JOptionPane.showMessageDialog(ad, "정상적으로 출근처리 되었습니다.","출근",JOptionPane.INFORMATION_MESSAGE);
			jlblAttend = new JLabel(ad.getAttend());
		} else {
			// 지각처리
			JOptionPane.showMessageDialog(ad, "지각!!","지각",JOptionPane.INFORMATION_MESSAGE);
			jlblAttend = new JLabel(ad.getTardy());
		} // else

		int date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		jlblAttend.setBounds(ad.getDayList().get(date - 1).getX() - 50, ad.getDayList().get(date - 1).getY() + 15, 50,
				50);
		ad.add(jlblAttend);
		ad.setComponentZOrder(jlblAttend, 0);
		ad.repaint();
		ad.getHcyE().setAttendFlag(true);
	}// attend

	private void getOff() throws SQLException {
		// 업무 일지 작성했을 때만 퇴근 가능
		if (!ad.getHcyE().isGetOffFlag()) {
			JOptionPane.showMessageDialog(ad, "업무일지를 작성해주세요.","업무일지",JOptionPane.INFORMATION_MESSAGE);
			return;
		} // if
			// 출근 버튼 눌렀을 때 발생할 수 있는 예외처리
		boolean AttendFlag = AttendanceDAO.getInstance().selectWorkingFlag(ad.getHcyE().getUser());
		int updateFlag = AttendanceDAO.getInstance().updateGetOff(ad.getHcyE().getUser());
		// 이미 퇴근 했는지 확인
		if (AttendFlag) {
			// 출근 버튼을 눌렀나 확인
			if (updateFlag == 1) {
				JOptionPane.showMessageDialog(ad, "정상적으로 퇴근처리 되었습니다! ");
			} else {
				JOptionPane.showMessageDialog(ad, "오류가 발생했습니다. 출근버튼을 눌렀는지 확인해주세요!");
			} // else
		} else {
			JOptionPane.showMessageDialog(ad, "이미 퇴근 했거나, 데이터베이스에 오류가 발생했습니다.");
		} // else
	}// getOff

	public void logOut() {
		ad.getHcyE().getTabbedPane().setVisible(false);
		ad.getHcyE().addComponent();
		ad.getHcyE().setUser(0);
	}// logOut

}// Class
