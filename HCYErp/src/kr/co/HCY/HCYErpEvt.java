package kr.co.HCY;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JInternalFrame;

public class HCYErpEvt extends MouseAdapter implements ActionListener {

	private HCYErp hcyE;
	private JInternalFrame jif;
	private int empNo;
	
	public HCYErpEvt(HCYErp hcyE) {
		this.hcyE=hcyE;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==hcyE.getJbtnAttend()) {
			
		}
		if(e.getSource()==hcyE.getJbtnOffWork()) {
			
		}
		if(e.getSource()==hcyE.getJbtnApplyLeave()) {
			
		}
		if(e.getSource()==hcyE.getJbtnChangePass()) {
			
		}
		if(e.getSource()==hcyE.getJbtnLogOut()) {
			
		}
		if(e.getSource()==hcyE.getJbtnFileUpload()) {
			
		}
		if(e.getSource()==hcyE.getJbtnFileDownload()) {
			
		}
		if(e.getSource()==hcyE.getJbtnFileDelete()) {
			
		}
		if(e.getSource()==hcyE.getJbtnRef()) {
			
		}
		if(e.getSource()==hcyE.getJbtnReport()) {
			
		}
		if(e.getSource()==hcyE.getJbtnDateSearch()) {
			
		}
		if(e.getSource()==hcyE.getJbtnNameSearch()) {
			
		}
		if(e.getSource()==hcyE.getJbtnEmpRegister()) {
			
		}
		if(e.getSource()==hcyE.getJbtnResign()) {
			
		}
		if(e.getSource()==hcyE.getJbtnReport()) {
			
		}
		if(e.getSource()==hcyE.getJbtnAbsence()) {
			
		}
		if(e.getSource()==hcyE.getJbtnAttendDate()) {
			
		}
		if(e.getSource()==hcyE.getJbtnAttendName()) {
			
		}
		
		
	}
	
	public void findPass() {
		
	}

	
}
