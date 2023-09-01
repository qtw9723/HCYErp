package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class FindPassDialogEvt extends MouseAdapter implements ActionListener {
	private FindPassDialog fpd;

	public FindPassDialogEvt(FindPassDialog fpd) {
		this.fpd = fpd;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}// actionPerformed

}// class
