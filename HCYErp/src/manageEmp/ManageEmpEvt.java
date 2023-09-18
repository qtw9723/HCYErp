package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class ManageEmpEvt extends MouseAdapter implements ActionListener {

	private ManageEmp me;

	public ManageEmpEvt(ManageEmp me) {
		this.me = me;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
		if (e.getSource() == me.getJlName()) {
			//사원정보 가져오려고 추가
			  String selectedEmpName = me.getJlName().getSelectedValue().toString();
			try {
				EmpVO empDetail = meDAO.getEmpDetails(selectedEmpName);

		        if (empDetail != null) {
		            // 가져온 직원 정보를 화면의 라벨에 설정합니다.
		        	ManageEmpDialog med=new ManageEmpDialog();
		        	med.getJtfEname().setText(empDetail.getEname());
		        	med.getJtfLevel().setText(empDetail.getLevel());
		        	med.getJtfTel().setText(empDetail.getTel());
		        	med.getJtfEmail().setText(empDetail.getEmail());
		        	med.getJtfDept().setText(empDetail.getDept());
		        	med.getJtfTeam().setText(empDetail.getTeam());
		        	med.getJtfJob().setText(empDetail.getJob());
		        	med.getJtfLoc().setText( empDetail.getDeptLoc());
		        	med.getJtfSal().setText(String.valueOf(empDetail.getSal()));
		        } else {
		            // 선택된 직원의 정보가 없을 경우에는 메시지를 표시합니다.
		            JOptionPane.showMessageDialog(me, "선택된 직원의 정보가 없습니다.");
		        }
				if (meDAO.selectTeamName(me.getHcyE().getUser()) == 13
						|| meDAO.selectTeamName(me.getHcyE().getUser()) == 91) {

					for (String empno : me.getListName()) {
						System.out.println(empno);
					}
					new ManageEmpDialog(me);
				} else {
					new ManageEmpDialog(me).getJbtnModify().setEnabled(false);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // if
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
						} // if
					} // for
						// 중복이면 me.getDlmEmp()에만 값을 추가
					if (isDuplicate) {
						me.getDlmEmp().addElement(team.substring(team.indexOf("/") + 1));
					} else {
						// 중복이 아닌 경우에는 me.getDlmteam()과 me.getDlmEmp() 모두에 추가
						me.getDlmteam().addElement(teamName);
						me.getDlmEmp().addElement(team.substring(team.indexOf("/") + 1));
					} // if
				} // for

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // try

		} else if (e.getSource() == me.getJlTeam()) {

			me.getDlmEmp().removeAllElements();

			try {
				for (String emp : meDAO.searchEmp(me.getJlTeam().getSelectedValue())) {
					me.getDlmEmp().addElement(emp);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // try

		} // valueChanged
			// 사원을 눌렀을 때 기본 정보가 띄워져야하잖아 ?
		
	}// mouseClicked
}// class
