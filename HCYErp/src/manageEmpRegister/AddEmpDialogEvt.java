package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==aed.getJbtnAddEmp()) {
			okAdd();
		}if(e.getSource()==aed.getJbtnCancel()) {
			cancel();
		}//end if
		
	}
	
	

}//class
