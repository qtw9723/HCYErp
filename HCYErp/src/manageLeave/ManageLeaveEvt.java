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

	public ManageLeaveEvt(ManageLeave ml) {
		this.ml = ml;
	}// Constructor

	@Override
	public void mouseClicked(MouseEvent me) {
		// 리스트 클릭
		if (me.getSource() == ml.getJtLeaveProposal() && me.getClickCount() == 2) {
			clickLeaveList();
		} // if
	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ml.getJbtnLogOut()) {
			logOut();
		} // if

	}// actionPerformed

	private void clickLeaveList() {
		try {
			new ManageLeaveDialog(ml);
		} catch (SQLException e) {
			e.printStackTrace();
		}// catch
	}// clickList

	public void logOut() {
		ml.getHcyE().getTabbedPane().setVisible(false);
		ml.getHcyE().addComponent();
		ml.getHcyE().setUser(0);
	}// logOut
}// class
