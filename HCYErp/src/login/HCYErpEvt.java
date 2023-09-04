package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import attendance.Attendance;
import manageDoc.ManageDoc;

public class HCYErpEvt extends MouseAdapter implements ActionListener {

	private HCYErp hcyE;
	private JInternalFrame jif;
	private int empNo;

	public HCYErpEvt(HCYErp hcyE) {
		this.hcyE = hcyE;
		hcyE.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				hcyE.dispose();
			};
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==hcyE.getJbtnLogIn()) {
			hcyE.removeComponent();
			JTabbedPane jt=new JTabbedPane();
			hcyE.add(jt);		
			jt.add("출근",new Attendance());
			jt.add("문서관리",new ManageDoc());
		}
	}
	public void findPass() {
		
	}


}
