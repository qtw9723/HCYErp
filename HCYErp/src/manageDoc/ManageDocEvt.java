package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageDocEvt extends MouseAdapter implements ActionListener {
	private ManageDoc md;
	
	public ManageDocEvt(ManageDoc md) {
		this.md = md;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed

}//class
