package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

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

	public void approveResignation() {
		
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
			approveResignation();
		}if(e.getSource()==ard.getJbtnCancel()) {
			cancel();
		}if(e.getSource()==ard.getJcbMonth()) {
			startUpdateDays();
		}//end if
	}

}//class
