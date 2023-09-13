package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class AddEmpDialogEvt extends MouseAdapter implements ActionListener{

	private AddEmpDialog aed;
	
	public AddEmpDialogEvt(AddEmpDialog aed) {
		this.aed=aed;
		
		aed.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				aed.dispose();
			}
			
		});//addWindowListener
	}//constructor

	public void okAdd() {
		
	}//okAdd
	
	public void cancel() {
		aed.dispose();
	}//cancel
	
	public void startUpdateDays() {

		int selectedYear = (int) aed.getJcbYearHiredate().getSelectedItem();
		int selectedMonth = (int) aed.getJcbMonthHiredate().getSelectedItem();

		Calendar cal = Calendar.getInstance();

		cal.set(selectedYear, selectedMonth - 1, 1); // 월은 0부터 시작하므로 -1 자꾸 잊어먹냐 왜
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		aed.getJcbDayHiredate().removeAllItems();
		for (int i = 1; i <= maxDay; i++) {
			aed.getJcbDayHiredate().addItem(i);
		}//end for
	}// startUpdateDays

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==aed.getJbtnAddEmp()) {
			okAdd();
		}if(e.getSource()==aed.getJbtnCancel()) {
			cancel();
		}if(e.getSource()==aed.getJcbMonthHiredate()) {
			startUpdateDays();
		}
		
	}
	
	

}//class
