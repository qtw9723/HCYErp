package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageDailyReportDialogEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReportDialog mdrd;

	ManageDailyReportDialogEvt(ManageDailyReportDialog mdrd) {
		this.mdrd = mdrd;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mdrd.getJbtnCancel()) {
			mdrd.dispose();
		}
	}//actionPerformed

}//class
