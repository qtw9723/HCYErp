package manageDailyReport;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ManageDailyReport extends JPanel {
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JComboBox<Integer> jcbDay;
	private JComboBox<String> jcbEmp;
	private JButton jbtnDateSearch;
	private JButton jbtnEmpSearch;
	private JList<String> jlReport;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	
	public ManageDailyReport() {
		
	}//constructor

}//class
