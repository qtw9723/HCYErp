package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageDailyReportDialogEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReportDilog mdrd;

	private ManageDailyReportDialogEvt(ManageDailyReportDilog mdrd) {
		this.mdrd = mdrd;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed

}//class
