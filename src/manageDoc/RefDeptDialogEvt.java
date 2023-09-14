package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RefDeptDialogEvt extends MouseAdapter implements ActionListener {
	private RefDeptDialog rdd;

	public RefDeptDialogEvt(RefDeptDialog rdd) {
		this.rdd = rdd;
		
		rdd.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				rdd.dispose();
			}
			
		});//addWindowListener
	}//constructor

	public void approveRef() {//권한 부여
		
	}//approveRef
	
	public void cancelRef() {
		rdd.dispose();
	}//cancelRef
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rdd.getJbtnApproveRef()) {
			approveRef();
		}
		if(e.getSource()==rdd.getJbtnCancel()) {
			cancelRef();
		}
	}//actionPerformed

}//class
