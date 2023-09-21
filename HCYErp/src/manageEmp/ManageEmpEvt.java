package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class ManageEmpEvt extends MouseAdapter implements ActionListener {

	private ManageEmp me;
	private List<String> list;

	public ManageEmpEvt(ManageEmp me) {
		this.me = me;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == me.getJbtnLogOut()) {
			logOut();
		} // if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == me.getJlDepartment()) {
			selectDept();
		} // if
		if (e.getSource() == me.getJlTeam()) {
			selectTeam();
		} // if
		if (e.getSource() == me.getJlName()) {
			if (e.getClickCount() == 2) {
				selectEmp();
			}
		} // if
	}// mouseClicked

	public void selectDept() {
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
		int cnt = 0;
		me.getDlmteam().removeAllElements();
		me.getDlmEmp().removeAllElements();
		list = new ArrayList<String>();
		try {
			for (EmpVO eVO : meDAO.searchTeam(me.getJlDepartment().getSelectedValue())) {
				cnt++;
				list.add(eVO.getEmpNo() + "/" + eVO.getEname());
				boolean isDuplicate = false;

				for (int i = 0; i < me.getDlmteam().getSize(); i++) {
					if (me.getDlmteam().get(i).equals(eVO.getTeam())) {
						isDuplicate = true;
						break; // 중복이면 루프를 종료
					} // if
				} // for
				if (isDuplicate) {
					me.getDlmEmp().addElement(eVO.getEname());
				} else {
					me.getDlmteam().addElement(eVO.getTeam());
					me.getDlmEmp().addElement(eVO.getEname());
				} // if
			} // for

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // try
	}// selectDept

	public void selectTeam() {
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
		me.getDlmEmp().removeAllElements();
		list = new ArrayList<String>();
		try {
			for (EmpVO eVO : meDAO.searchEmp(me.getJlTeam().getSelectedValue())) {
				list.add(eVO.getEmpNo() + "/" + eVO.getEname());
				me.getDlmEmp().addElement(eVO.getEname());
			}
			// String Hong ="...찬영씨?:;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // try
	}// selectTeam

	public void selectEmp() {
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
		EmpVO eVO = null;
		int selectedEmpName = me.getJlName().getSelectedIndex();
		int cnt = 0;
		int cnt2 = 0;
		if (me.getJlDepartment().getSelectedValue() == null && me.getJlTeam().getSelectedValue() == null) {
			try {
				list = meDAO.selectDept();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // try
			for (String emp : list) {
				if (emp.substring(0, emp.indexOf("/")).equals("0")) {
					cnt++;
					continue;
				} // if
				if (cnt == 2) {
					cnt2++;
					if (cnt2 == selectedEmpName + 1) {
						// 가져온 직원 정보를 화면의 라벨에 설정합니다.
						try {
							eVO = meDAO.getEmpDetails(Integer.parseInt(emp.substring(0, emp.indexOf("/"))));
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						} // try
					} // if
				} // if
			} // for
		} else if (me.getJlTeam().getSelectedValue() != null) {
			for (String emp : list) {
				cnt++;
				if (cnt == selectedEmpName + 1) {
					try {
						eVO = meDAO.getEmpDetails(Integer.parseInt(emp.substring(0, emp.indexOf("/"))));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // try
				} // if
			} // for
		} else if (me.getJlDepartment().getSelectedValue() != null && me.getJlTeam().getSelectedValue() == null) {
			for (String emp : list) {
				cnt++;
				if (cnt == selectedEmpName + 1) {
					try {
						eVO = meDAO.getEmpDetails(Integer.parseInt(emp.substring(0, emp.indexOf("/"))));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // try
				} // if
			} // for
		} // if
		try {
			if (meDAO.selectTeamName(me.getHcyE().getUser()) == 13
					|| meDAO.selectTeamName(me.getHcyE().getUser()) == 91) {
				new ManageEmpDialog(me, eVO);
			} else {
				new ManageEmpDialog(me, eVO).getJbtnModify().setEnabled(false);
			} // if
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // try
	} // selectEmp

	public void logOut() {
		me.getHcyE().getTabbedPane().setVisible(false);
		me.getHcyE().addComponent();
		me.getHcyE().setUser(0);
	}// logOut
}// class
