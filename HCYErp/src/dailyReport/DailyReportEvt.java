package dailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class DailyReportEvt extends MouseAdapter implements ActionListener {
	
	private DailyReport dr;

	public DailyReportEvt(DailyReport dr) {
		this.dr = dr;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed

}//class
