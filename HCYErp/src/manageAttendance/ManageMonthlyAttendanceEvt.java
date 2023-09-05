package manageAttendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageMonthlyAttendanceEvt extends MouseAdapter implements ActionListener{

	private ManageMonthlyAttendance ma;
	public ManageMonthlyAttendanceEvt(ManageMonthlyAttendance ma) {
		this.ma=ma;  
	}//Constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
