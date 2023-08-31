package manageAttendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageAttendanceEvt extends MouseAdapter implements ActionListener{

	private ManageAttendance ma;
	public ManageAttendanceEvt(ManageAttendance ma) {
		this.ma=ma;  
	}//Constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
