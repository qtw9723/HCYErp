package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageEmpEvt extends MouseAdapter implements ActionListener{

	private ManageEmp me;
	
	public ManageEmpEvt(ManageEmp me) {
		this.me=me;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
