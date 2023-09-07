package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

public class ManageDailyReportEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReport mdr;

	public ManageDailyReportEvt(ManageDailyReport mdr) {
		this.mdr = mdr;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mdr.getJbtnDateSearch()) {
			ManageDailyReportDAO mdrDAO=ManageDailyReportDAO.getInstance();
			try {
				mdrDAO.selectDailyReport("2023-09-04");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}//if
		if(e.getSource()==mdr.getJbtnEmpSearch()) {
			ManageDailyReportDAO mdrDAO=ManageDailyReportDAO.getInstance();
			try {
				mdrDAO.selectDailyReport(4705);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}//actionPerformed

}//class
