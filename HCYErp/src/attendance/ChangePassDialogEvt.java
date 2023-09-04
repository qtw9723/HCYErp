package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ChangePassDialogEvt extends MouseAdapter implements ActionListener{

	private ChangePassDialog cpd;
	
	public ChangePassDialogEvt(ChangePassDialog cpd) {
		this.cpd=cpd;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			
	}
	
}//class
