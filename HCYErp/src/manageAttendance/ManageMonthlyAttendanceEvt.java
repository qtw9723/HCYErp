package manageAttendance;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import VO.AttendanceVO;

public class ManageMonthlyAttendanceEvt extends MouseAdapter implements ActionListener {

	private ManageMonthlyAttendance ma;

	public ManageMonthlyAttendanceEvt(ManageMonthlyAttendance ma) {
		this.ma = ma;
	}// Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// 월별 검색
		if (e.getSource() == ma.getJbtnAttendDate()) {
			try {
				searchAttendanceDate();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(ma, "데이터 베이스에 문제가 발생했습니다. 기술팀에 문의해주세요!");
			} // catch
		} // if
		
		//로그아웃
		if(e.getSource()==ma.getJbtnLogOut()) {
			logOut();
		}//if
	}// actionPerformed

	private void logOut() {
		ma.getHcyE().getTabbedPane().setVisible(false);
		ma.getHcyE().addComponent();
		ma.getHcyE().setUser(0);
	}//logOut

	private void searchAttendanceDate() throws SQLException {
		for(int i = 0;i<ma.getDtmMonthlyAttend().getRowCount();i++) {
		ma.getDtmMonthlyAttend().removeRow(i);
		}//for
		StringBuilder sbDay = new StringBuilder();
		StringBuilder tempMonth=new StringBuilder();
		if( ma.getJcbMonth().getSelectedItem().toString().length() == 1) {
			tempMonth.append("0");
		}//if
		tempMonth.append(ma.getJcbMonth().getSelectedItem().toString());
		sbDay.append(ma.getJcbYear().getSelectedItem()).append("-").append(tempMonth.toString());
		
		List<AttendanceVO> aVOList = ManageAttendanceDAO.getInstance().selectNoAttend(sbDay.toString());
		String[] attendArr = new String[ma.getDtmMonthlyAttend().getColumnCount()]; 
		if(aVOList.size()==0) {
			JOptionPane.showMessageDialog(ma, "해당 달에 출근한 사원이 없습니다.");
			return;
		}//if
		for(AttendanceVO aVO : aVOList) {
				attendArr[0] = Integer.toString(aVO.getEmpNo());
				attendArr[1] = aVO.getEname();
				attendArr[2] = aVO.getStartTime();
				attendArr[3] = aVO.getEndTime();
				attendArr[4] = aVO.getWorkDate();
			ma.getDtmMonthlyAttend().addRow((Object[])attendArr);
		}//for
	}//searchAttendanceDate
}// class
