package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class AttendanceEvt extends MouseAdapter implements ActionListener {

	private Attendance ad;
	
	
	public AttendanceEvt(Attendance ad) {
		this.ad=ad;
	}//constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//비밀번호 초기화
		if(e.getSource()==ad.getJbtnChangePass()) {
			new ChangePassDialog().setBounds(ad.getHcyE().getX()+200,ad.getHcyE().getY()+100,500,500);
		}//if
		
		//로그아웃
		if(e.getSource()==ad.getJbtnLogOut()) {
			logOut();
		}//if
		
		//출근
		if(e.getSource()==ad.getJbtnAttend()) {
			try {
				attend();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//catch
		}//if
		
		//퇴근
		if(e.getSource()==ad.getJbtnOffWork()){
			try {
			getOff();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//catch
		}//if
		
		//휴가신청
		if(e.getSource()==ad.getJbtnApplyDayOff()) {
			new ApplyDayOffDialog(ad);
		}//if
	}//actionPerformed

	private void attend() throws SQLException {
		boolean flag = AttendanceDAO.getInstance().selectWorkingFlag(ad.getHcyE().getUser());
		if(!flag) {
		AttendanceDAO.getInstance().insertAttendance(ad.getHcyE().getUser());
		}else {
			JOptionPane.showMessageDialog(ad, "이미 출근 했거나, 데이터베이스에 오류가 발생했습니다.");
		}//else
	}//attend

	private void getOff() throws SQLException {
		boolean AttendFlag = AttendanceDAO.getInstance().selectWorkingFlag(ad.getHcyE().getUser());
		int updateFlag = AttendanceDAO.getInstance().updateGetOff(ad.getHcyE().getUser());
		if(AttendFlag) {
		if(updateFlag == 1) {
			JOptionPane.showMessageDialog(ad, "정상적으로 퇴근처리 되었습니다! ");
		}else {
			JOptionPane.showMessageDialog(ad, "오류가 발생했습니다. 출근버튼을 눌렀는지 확인해주세요!");
		}//else
		}else {
			JOptionPane.showMessageDialog(ad, "이미 퇴근 했거나, 데이터베이스에 오류가 발생했습니다.");
		}//else
	}//getOff
		
	
	public void logOut() {
		ad.getHcyE().getTabbedPane().setVisible(false);
		ad.getHcyE().addComponent();
		ad.getHcyE().setUser(0);
	}//logOut
	
	
}//Class
