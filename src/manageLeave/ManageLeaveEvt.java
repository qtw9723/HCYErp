package manageLeave;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageLeaveEvt extends MouseAdapter implements ActionListener {
	private ManageLeave ml;
	private int select;
	
	public ManageLeaveEvt(ManageLeave ml) {
		this.ml=ml; 
	}//Constructor
	
	@Override
	public void mouseClicked(MouseEvent me) {
		//리스트 클릭
		if(me.getSource()==ml.getJtLeaveProposal()) {
			clickLeaveList();
		}//if
	}//mouseClicked


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}//actionPerformed
	
	private void clickLeaveList() {
		try {
			if(select==(ml.getJtLeaveProposal().getSelectedRow())) {
				try {
					new ManageLeaveDialog(ml);
				} catch (SQLException e) {
					e.printStackTrace();
				}//catch
			} else {
				select = ml.getJtLeaveProposal().getSelectedRow();
			}//else
		} catch (NullPointerException npe) {
			select = ml.getJtLeaveProposal().getSelectedRow();
		}//catch
	}//clickList
}//class
