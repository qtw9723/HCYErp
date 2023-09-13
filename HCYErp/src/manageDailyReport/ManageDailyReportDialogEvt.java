package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageDailyReportDialogEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReportDialog mdrd;

	ManageDailyReportDialogEvt(ManageDailyReportDialog mdrd) {
		this.mdrd = mdrd;
	}//constructor
	

	public void modifyReport() {//수정 버튼 눌렀을 때
		
	}//modifyReport
	
	public void cancel() {
		mdrd.dispose();
	}//cancel
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//이거 다시 봐야함내가 적은 업무일지 들고와서 수정완료,취소만 짜면 됨
		if(e.getSource()==mdrd.getJbtnCancel()) {
			cancel();
		}//end if
		if(e.getSource()==mdrd.getJbtnModify()) {
			modifyReport();
		}//end if
		if(e.getSource()==mdrd.getJcbMonth){
			
		}
	}//actionPerformed

}//class
