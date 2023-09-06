package attendance;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import login.HCYErp;
import login.HCYErpEvt;


public class AttendanceEvt extends MouseAdapter implements ActionListener {

	private Attendance ad;
	private HCYErpEvt hcyEE;
	
	
	public AttendanceEvt(Attendance ad) {
		this.ad=ad;
	}//constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ad.getJbtnChangePass()) {
			new ChangePassDialog().setBounds(ad.getHcyE().getX()+200,ad.getHcyE().getY()+100,500,500);
		}
		if(e.getSource()==ad.getJbtnLogOut()) {
			logOut();
		}
	}//actionPerformed
	
	public void attend() {
		
	}

	public void getoff() {
		
	}
	
	public void dayOffApply() {
		
	}
	
	public void changePass() {
		
	}
	
	public void logOut() {
		ad.getHcyE().getTabbedPane().setVisible(false);
		ad.getHcyE().addComponent();
	}
	
}//Class
