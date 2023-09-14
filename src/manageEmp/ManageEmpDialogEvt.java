package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ManageEmpDialogEvt extends MouseAdapter implements ActionListener{

	private ManageEmpDialog med;
	
	public ManageEmpDialogEvt(ManageEmpDialog med) {
		this.med=med;
		
		med.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				med.dispose();
			}
			
		});//addWindowListener
		
	}//constructor

	public void okModify() {//바뀐정보들 업데이트
		
	}//okModify
	
	public void cancel() {
		med.dispose();
	}//cancel
	
	public void modifyEmpInfo() {
		med.getJbtnModify().setVisible(false);
		med.getJbtnOK().setVisible(true);
		med.getJbtnCancel().setVisible(true);
		med.getJbtnOK().setBounds(180,320,150,50);
		med.getJbtnCancel().setBounds(350, 320, 150, 50);
		
	}//modifyEmpInfo
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==med.getJbtnModify()) {
			modifyEmpInfo();
		}else if(e.getSource()==med.getJbtnOK()) {
			okModify();
		}else if(e.getSource()==med.getJbtnCancel()) {
			cancel();
		}
		
	}//actionPerformed
	
}//class
