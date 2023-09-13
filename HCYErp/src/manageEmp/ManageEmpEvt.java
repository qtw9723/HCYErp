package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageEmpEvt extends MouseAdapter implements ActionListener, ListSelectionListener {

	private ManageEmp me;

	public ManageEmpEvt(ManageEmp me) {
		this.me = me;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {

	}// actionPerformed

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
		if (e.getSource() == me.getJlDepartment()) {
			me.getDlmteam().removeAllElements();
			me.getDlmEmp().removeAllElements();
			int i=0;
			try {
				for (String team : meDAO.searchTeam(me.getJlDepartment().getSelectedValue())) {
						me.getDlmteam().addElement(team.substring(0, team.indexOf("/")));
						if (team.substring(team.indexOf("/") + 1) != "null") {
							me.getDlmEmp().addElement(team.substring(team.indexOf("/") + 1));
						} else if (team.substring(team.indexOf("/") + 1).equals("null")) {
							me.getDlmEmp().addElement("");
						} // if
						if(me.getDlmteam().get(i++).contain(team.substring(0, team.indexOf("/")))){
							
						}
				} // for
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // try
		} // if
		if (e.getSource() == me.getJlTeam()) {
			me.getDlmEmp().removeAllElements();
			try {
				for (String emp : meDAO.searchEmp(me.getJlTeam().getSelectedValue())) {
					me.getDlmEmp().addElement(emp);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // try
		} // if
	}// valueChanged

}// class
