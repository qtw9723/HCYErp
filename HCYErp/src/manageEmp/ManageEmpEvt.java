package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
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
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==me.getJlName()) {
			new ManageEmpDialog(me);
		}//if
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
		if (e.getSource() == me.getJlDepartment()) {
			me.getDlmteam().removeAllElements();
			me.getDlmEmp().removeAllElements();
			try {
				for (String team : meDAO.searchTeam(me.getJlDepartment().getSelectedValue())) {
				    String teamName = team.substring(0, team.indexOf("/"));
				    
				    // 중복을 걸러내기 위한 플래그
				    boolean isDuplicate = false;

				    // me.getDlmteam()에 이미 해당 팀 이름이 있는지 확인
				    for (int i = 0; i < me.getDlmteam().getSize(); i++) {
				        if (me.getDlmteam().get(i).equals(teamName)) {
				            isDuplicate = true;
				            break; // 중복이면 루프를 종료
				        }//if
				    }//for

				    // 중복이면 me.getDlmEmp()에만 값을 추가
				    if (isDuplicate) {
				        me.getDlmEmp().addElement(team.substring(team.indexOf("/") + 1));
				    } else {
				        // 중복이 아닌 경우에는 me.getDlmteam()과 me.getDlmEmp() 모두에 추가
				        me.getDlmteam().addElement(teamName);
				        me.getDlmEmp().addElement(team.substring(team.indexOf("/") + 1));
				    }//if
				}//for

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // try
			
		} 
		else if (e.getSource() == me.getJlTeam()&&e.getSource() == me.getJlDepartment()) {
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
