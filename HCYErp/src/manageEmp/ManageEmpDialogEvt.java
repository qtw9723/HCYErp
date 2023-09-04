package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageEmpDialogEvt extends MouseAdapter implements ActionListener{

	private ManageEmpDialog med;
	
	public ManageEmpDialogEvt(ManageEmpDialog med) {
		this.med=med;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//class
