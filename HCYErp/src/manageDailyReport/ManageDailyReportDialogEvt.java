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
	public void actionPerformed(ActionEvent e) {//이거 다시 봐야함내가 적은 업무일지 들고와서 수정완료,취소만 짜면 됨
		if(e.getSource()==mdrd.getJbtnCancel()) {
			mdrd.dispose();
		}
	}//actionPerformed

}//class
