package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageEmpRegisterEvt extends MouseAdapter implements ActionListener {

	private ManageEmpRegister mer;
	
	public ManageEmpRegisterEvt(ManageEmpRegister mer) {
		this.mer=mer;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}//class
