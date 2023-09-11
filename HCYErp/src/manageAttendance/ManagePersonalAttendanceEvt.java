package manageAttendance;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManagePersonalAttendanceEvt extends MouseAdapter implements ActionListener{

	private ManagePersonalAttendance ma;
	public ManagePersonalAttendanceEvt(ManagePersonalAttendance ma) {
		this.ma=ma;  
	}//Constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
