package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageDailyReportEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReport mdr;

	private ManageDailyReportEvt(ManageDailyReport mdr) {
		this.mdr = mdr;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed

}//class
