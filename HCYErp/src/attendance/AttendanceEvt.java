package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class AttendanceEvt extends MouseAdapter implements ActionListener {

	private Attendance ad;
	
	public AttendanceEvt(Attendance ad) {
		this.ad=ad;
	}//constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ad.getJbtnChangePass()) {
			new ChangePassDialog();
		}
	}//actionPerformed

	
}//Class
