package manageDailyReport;

import java.util.Calendar;

import javax.swing.ImageIcon;
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
		ManageDailyReportEvt event = new ManageDailyReportEvt(this);
		
		setLayout(null);
		
		//연월일 콤박
		//연 콤박
		jcbYear = new JComboBox<Integer>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		jcbYear.addItem(year-1);
		jcbYear.addItem(year);
		jcbYear.addItem(year+1);
		jcbYear.setBounds(100,100,100,50);
//		jcbYear.setBackground(0xffffff);
		add(jcbYear);
		
		for(int i=1;i<13;i++) {
		}
			
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
	}//constructor

}//class
