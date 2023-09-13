package manageEmpRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ManageEmpRegisterEvt extends MouseAdapter implements ActionListener {

	private ManageEmpRegister mer;

	public ManageEmpRegisterEvt(ManageEmpRegister mer) {
		this.mer = mer;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent ae) {
		//퇴사처리
		if (ae.getSource() == mer.getJbtnResign()) {
			new ApproveResignationDialog();
		}//if

		//입사자 추가
		if (ae.getSource() == mer.getJbtnEmpRegister()) {
			new AddEmpDialog(mer);
		}//if

		//휴직처리
		if (ae.getSource() == mer.getJbtnAbsence()) {
			new ApproveAbsenceDialog(mer);
		}//if
		
		// 로그아웃
		if (ae.getSource() == mer.getJbtnLogOut()) {
			mer.getHcyE().getTabbedPane().setVisible(false);
			mer.getHcyE().addComponent();
			mer.getHcyE().setUser(0);
		} // if
	}// actionPerformed

}// class
