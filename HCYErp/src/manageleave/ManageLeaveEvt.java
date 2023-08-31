package manageleave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageLeaveEvt extends MouseAdapter implements ActionListener {
	private ManageLeave ml;
	
	public ManageLeaveEvt(ManageLeave ml) {
		this.ml=ml;
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
