package kr.co.HCY;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

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
		if (e.getSource() == hcyE.getJbtnLogIn()) {
			findPass();
		}
		if (e.getSource() == hcyE.getJbtnAttend()) {

		}
		if (e.getSource() == hcyE.getJbtnOffWork()) {

		}
		if (e.getSource() == hcyE.getJbtnApplyLeave()) {

		}
		if (e.getSource() == hcyE.getJbtnChangePass()) {

		}
		if (e.getSource() == hcyE.getJbtnLogOut()) {

		}
		if (e.getSource() == hcyE.getJbtnFileUpload()) {

		}
		if (e.getSource() == hcyE.getJbtnFileDownload()) {

		}
		if (e.getSource() == hcyE.getJbtnFileDelete()) {

		}
		if (e.getSource() == hcyE.getJbtnRef()) {

		}
		if (e.getSource() == hcyE.getJbtnReport()) {

		}
		if (e.getSource() == hcyE.getJbtnDateSearch()) {

		}
		if (e.getSource() == hcyE.getJbtnNameSearch()) {

		}
		if (e.getSource() == hcyE.getJbtnEmpRegister()) {

		}
		if (e.getSource() == hcyE.getJbtnResign()) {

		}
		if (e.getSource() == hcyE.getJbtnReport()) {

		}
		if (e.getSource() == hcyE.getJbtnAbsence()) {

		}
		if (e.getSource() == hcyE.getJbtnAttendDate()) {

		}
		if (e.getSource() == hcyE.getJbtnAttendName()) {

		}

	}

	public void findPass() {

		JDesktopPane desktopPane = new JDesktopPane();
		hcyE.add(desktopPane);
		// 내부 프레임
		jif = new JInternalFrame("Inner Frame", true, true, true, true);
		jif.setSize(300, 200);
		jif.setLocation(50, 50);

		jif.setVisible(true);
		desktopPane.add(jif);

		desktopPane.setVisible(false);
		jif.setVisible(true);

	}

}
