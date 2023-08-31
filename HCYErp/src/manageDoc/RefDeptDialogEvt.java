package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class RefDeptDialogEvt extends MouseAdapter implements ActionListener {
	private RefDeptDialog rdd;

	private RefDeptDialogEvt(RefDeptDialog rdd) {
		this.rdd = rdd;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed

}//class
