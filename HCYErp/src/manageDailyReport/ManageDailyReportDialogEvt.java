package manageDailyReport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import javax.swing.JOptionPane;

import VO.DailyReportVO;

public class ManageDailyReportDialogEvt extends MouseAdapter implements ActionListener {
	private ManageDailyReportDialog mdrd;

	public ManageDailyReportDialogEvt(ManageDailyReportDialog mdrd) {
		this.mdrd = mdrd;
		mdrd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mdrd.dispose();
			}//windowwindowClosing
		});//addWindowListener
	}//constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//취소버튼
		if(e.getSource()==mdrd.getJbtnCancel()) {
			cancel();
		}//if
		
		//수정버튼
		if(e.getSource()==mdrd.getJbtnModify()) {
			try {
				modifyReport();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//catch
		}//end if
	}//actionPerformed
	
	
	public void modifyReport() throws SQLException {//수정 버튼 눌렀을 때
		DailyReportVO drVO=new DailyReportVO();
	
		//내용추가
		if(mdrd.getMdr().getDtmReport().getValueAt(mdrd.getMdr().getJtReport().getSelectedRow(), 0).toString()==String.valueOf(mdrd.getMdr().getHcyE().getUser())) {
		StringBuilder sbContent=new StringBuilder();
		drVO.setReportContent(sbContent.append(mdrd.getJtaDailyReport().getText()).toString());
		
		//사번추가
		int empNo=(int) mdrd.getMdr().getJtReport().getValueAt(mdrd.getMdr().getJtReport().getSelectedRow(), 0);
		drVO.setEmpNo(empNo);
		
		//일지 등록일 추가
		String date = mdrd.getMdr().getJtReport().getValueAt(mdrd.getMdr().getJtReport().getSelectedRow(), 3).toString();
		drVO.setReportDate(date);
		
		ManageDailyReportDAO.getInstance().updateDailyReport(drVO);
		
		JOptionPane.showMessageDialog(mdrd, "업무일지가 수정되었습니다.");
		
		refresh(mdrd.getMdr().getHcyE().getEvent().getSelectedIndex());
		
		cancel();
		}else {
			JOptionPane.showMessageDialog(mdrd, "다른사원의 업무일지는 변경할 수 없습니다!");
		}
	}//modifyReport
	
	public void cancel() {
		mdrd.dispose();
	}//cancel
	
	public void refresh(int index) throws SQLException {
		mdrd.getMdr().getHcyE().getTabbedPane().setVisible(false);
		mdrd.getMdr().getHcyE().addComponent();
		mdrd.getMdr().getHcyE().getEvent().login();
		mdrd.getMdr().getHcyE().getTabbedPane().setSelectedIndex(index);
	}//refresh

}//class
