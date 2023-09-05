package manageDailyReport;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import login.HCYErp;

@SuppressWarnings("serial")
public class ManageDailyReport extends JPanel {
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JComboBox<Integer> jcbDay;
	private JComboBox<String> jcbEmp;
	private JButton jbtnDateSearch;
	private JButton jbtnEmpSearch;
	private JList<String> jlReport;
	private JScrollPane jspReport;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private HCYErp hcyE;
	
	public ManageDailyReport(HCYErp hcyE) {
		this.hcyE=hcyE;
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
		jcbYear.setBounds(100,100,130,40);
		jcbYear.setBackground(new Color(0xffffff));
		add(jcbYear);
		//월 콤박
		jcbMonth = new JComboBox<Integer>();
		for(int i = 1;i<13;i++) {
			jcbMonth.addItem(i);
		}//for
		jcbMonth.setBounds(260,100,130,40);
		jcbMonth.setBackground(new Color(0xffffff));
		jcbMonth.addActionListener(event);
		add(jcbMonth);
		
		//일 콤박 찬영아 이거 가져다가 쓰렴 하하
		jcbDay = new JComboBox<Integer>();
		for(int i = 1;i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH);i++) {
			jcbDay.addItem(i);
		}//for
		jcbDay.setBounds(420,100,130,40);
		jcbDay.setBackground(new Color(0xffffff));
		add(jcbDay);
		//하하하하하하하하하하하하하ㅏ하하하하하하하하하하하하하하하하ㅏ하하하하하하하하하하ㅏ하하하하하하하
		
		//일자 조회 버튼
		jbtnDateSearch = new JButton("조회");
		jbtnDateSearch.setBounds(580,100,70,40);
		jbtnDateSearch.addActionListener(event);
		add(jbtnDateSearch);
		
		
		//이름 콤박
		jcbEmp = new JComboBox<String>();
		//잇츠 다오 필요 예스
		jcbEmp.setBounds(700,100,230,40);
		jcbEmp.setBackground(new Color(0xffffff));
		add(jcbEmp);
		
		//이름 조회 버튼
		jbtnEmpSearch = new JButton("조회");
		jbtnEmpSearch.setBounds(960,100,70,40);
		jbtnEmpSearch.addActionListener(event);
		add(jbtnEmpSearch);
		
		
		//업무일지 리스트
		DefaultListModel<String> dlmReport = new DefaultListModel<String>();
		jlReport = new JList<String>(dlmReport);
		for(int i =1;i<100;i++) {
			dlmReport.addElement("업무일지"+i);
		}// 다오 나오면 수저저저저저저저저저저저저저저저ㅓㅇ
		jspReport = new JScrollPane(jlReport);
		jspReport.setBounds(150,160,800,350);
		add(jspReport);
		
		
		
		
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

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<Integer> getJcbDay() {
		return jcbDay;
	}

	public JComboBox<String> getJcbEmp() {
		return jcbEmp;
	}

	public JButton getJbtnDateSearch() {
		return jbtnDateSearch;
	}

	public JButton getJbtnEmpSearch() {
		return jbtnEmpSearch;
	}

	public JList<String> getJlReport() {
		return jlReport;
	}

	public JScrollPane getJspReport() {
		return jspReport;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public HCYErp getHcyE() {
		return hcyE;
	}
	
	

}//class
