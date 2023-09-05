package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageEmpEvt extends MouseAdapter implements ActionListener, ListSelectionListener{

	private ManageEmp me;
	
	public ManageEmpEvt(ManageEmp me) {
		this.me=me;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}//actionPerformed

	@Override
	public void valueChanged(ListSelectionEvent e) {
	}//valueChanged
	
}//class
