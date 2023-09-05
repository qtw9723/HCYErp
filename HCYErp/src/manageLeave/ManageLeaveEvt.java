package manageLeave;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageLeaveEvt extends MouseAdapter implements ActionListener,ListSelectionListener {
	private ManageLeave ml;
	
	public ManageLeaveEvt(ManageLeave ml) {
		this.ml=ml; 
	}//Constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}//class
