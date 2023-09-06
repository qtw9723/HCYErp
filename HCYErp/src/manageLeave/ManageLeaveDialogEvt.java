package manageLeave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageLeaveDialogEvt implements ActionListener {

	private ManageLeaveDialog mld; 
	
	public ManageLeaveDialogEvt(ManageLeaveDialog mld) {
		this.mld=mld; 
		mld.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			mld.dispose();
		}//windowClosing
		});
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed
	
}//class
