package dailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import VO.DailyReportVO;
import attendance.Attendance;
import attendance.AttendanceEvt;
import login.HCYErp;
import oracle.sql.DATE;

public class DailyReportEvt extends MouseAdapter implements ActionListener {
	
	private DailyReport dr;
	
	public DailyReportEvt(DailyReport dr) {
		this.dr = dr;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == dr.getJbtnReport() ) {
			try {
				submitReport();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
				JOptionPane.showMessageDialog(dr, "오류로 일지 등록이 완료되지 않았습니다.");
			}//catch
		}//if
		
		if( e.getSource() == dr.getJbtnLogOut() ) {
			logOut();
		}//if
		
	}//actionPerformed

	public void submitReport() throws SQLException {
		if( !dr.getHcyE().isAttendFlag() ) {
			JOptionPane.showMessageDialog(dr, "출근 후 업무일지를 작성할 수 있습니다.");
			return;
		}//if
		
		DailyReportVO drVO=new DailyReportVO();
		drVO.setEmpNo(dr.getHcyE().getUser());
		drVO.setReportContent(dr.getJtaReport().getText());
			
		DailyReportDAO.getInstance().insertDailyReport(drVO);
					
		JOptionPane.showMessageDialog(dr, "오늘의 업무일지 등록이 완료되었습니다.");
		
		dr.getHcyE().setGetOffFlag(true);
		
	}//submitReport
	
	public void logOut() {
		dr.getHcyE().getTabbedPane().setVisible(false);
		dr.getHcyE().addComponent();
		dr.getHcyE().setUser(0);
	}//logOut
	
}//class
