package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class ApproveResignationDialogEvt extends MouseAdapter implements ActionListener{

	private ApproveResignationDialog ard;
	
	public ApproveResignationDialogEvt(ApproveResignationDialog ard) {
		this.ard=ard;
		
		ard.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ard.dispose();
			}
			
		});//addWindowListener
	}//constructor

	public void approveResignation() throws SQLException {
		if(ard.getJtaReason().getText().equals("")) {
			JOptionPane.showMessageDialog(ard, "퇴사이유를 작성하세요");
		}else {
			
			EmpVO eVO=new EmpVO(); 
			
			//사원번호
			
			eVO.setEmpNo(Integer.parseInt(ard.getJcbEmpNoName().getSelectedItem().toString().substring(0,4)));
			
			ManageEmpRegisterDAO.getInstance().deleteRsignationEmp(eVO);
			
			JOptionPane.showMessageDialog(ard, "퇴사되었습니다.");
		}
		
	}//approveResignation
	
	public void cancel() {
		ard.dispose();
	}//cancel
	
	public void startUpdateDays() {

		int selectedYear = (int) ard.getJcbYear().getSelectedItem();
		int selectedMonth = (int) ard.getJcbMonth().getSelectedItem();

		Calendar cal = Calendar.getInstance();

		cal.set(selectedYear, selectedMonth - 1, 1); 
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		ard.getJcbDay().removeAllItems();
		for (int i = 1; i <= maxDay; i++) {
			ard.getJcbDay().addItem(i);
		}//end for
	}// startUpdateDays
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ard.getJbtnApprove()) {
			try {
				approveResignation();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}if(e.getSource()==ard.getJbtnCancel()) {
			cancel();
		}if(e.getSource()==ard.getJcbMonth()) {
			startUpdateDays();
		}//end if
	}

}//class
