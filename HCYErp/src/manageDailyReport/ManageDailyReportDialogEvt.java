package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import VO.DailyReportVO;

public class ManageDailyReportDialogEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReportDialog mdrd;

	ManageDailyReportDialogEvt(ManageDailyReportDialog mdrd) {
		this.mdrd = mdrd;
		mdrd.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				mdrd.dispose();
			}
			
		});//addWindowListener
	}//constructor
	

	public void modifyReport() throws SQLException {//수정 버튼 눌렀을 때
		DailyReportVO drVO=new DailyReportVO();
	
		
		//내용추가
		StringBuilder sbContent=new StringBuilder();
		drVO.setReportContent(sbContent.append(mdrd.getJtaDailyReport().getText()).toString());
		
		//사번추가
		drVO.setEmpNo(mdrd.getMdr().getHcyE().getUser());
		
		ManageDailyReportDAO.getInstance().updateDailyReport(drVO);
		
		JOptionPane.showMessageDialog(mdrd, "업무일지가 변경되었습니다.");
		
	}//modifyReport
	
	public void cancel() {
		mdrd.dispose();
	}//cancel
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==mdrd.getJbtnCancel()) {
			cancel();
		}//end if
		if(e.getSource()==mdrd.getJbtnModify()) {
			try {
				modifyReport();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}//end if
		
	}//actionPerformed

}//class
