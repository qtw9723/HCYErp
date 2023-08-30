package kr.co.HCY;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FindPassDialog extends JDialog {
	private JTextField jtfEmpNo; 
	private JTextField jtfEname; 
	private JTextField jtfSsn; 
	private JButton jbtnFindPass; 
	private JButton jbtnCancel; 
	private JLabel jlblLogoGrey;
	public FindPassDialog() {
		
		jtfEmpNo = new JTextField();
		jtfEname = new JTextField();
		jtfSsn = new JTextField();
		
		
	}
}
