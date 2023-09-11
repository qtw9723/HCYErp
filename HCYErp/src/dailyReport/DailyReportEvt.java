package dailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import VO.DailyReportVO;
import login.HCYErp;

public class DailyReportEvt extends MouseAdapter implements ActionListener {
	
	private DailyReport dr;

	public DailyReportEvt(DailyReport dr) {
		this.dr = dr;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == dr.getJbtnReport() ) {
				submitReport();
		}//if
		
		if( e.getSource() == dr.getJbtnLogOut() ) {
			logOut();
		}//if
		
	}//actionPerformed

	public void submitReport() {
		DailyReportVO drVO=new DailyReportVO();
		drVO.setEmpNo(dr.getHcyE().getUser());
		drVO.setReportContent(dr.getJtaReport().getText());
	}//submitReport
	
	public void logOut() {
		dr.getHcyE().getTabbedPane().setVisible(false);
		dr.getHcyE().addComponent();
		dr.getHcyE().setUser(0);
	}//logOut
	
}//class
