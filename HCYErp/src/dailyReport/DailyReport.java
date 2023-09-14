package dailyReport;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import login.HCYErp;

@SuppressWarnings("serial")
public class DailyReport extends JPanel {
	private JButton jbtnReport;
	private JTextArea jtaReport;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private HCYErp hcyE;
	
	public DailyReport(HCYErp hcyE) {
		this.hcyE=hcyE;
		DailyReportEvt event = new DailyReportEvt(this);
		
		setLayout(null);
		
		//업무일지 에리어
		jtaReport = new JTextArea("2023-00-00 사원명\n\n\n업무내용\n-\n-\n-");
		jtaReport.setBounds(190,100,800,350);
		jtaReport.setBorder(new TitledBorder(""));
		add(jtaReport);
		
		//작성 버튼
		jbtnReport = new JButton("작성완료");
		jbtnReport.setBounds(480,470,200,70);
		jbtnReport.addActionListener(event);
		add(jbtnReport);
		
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
		hcyE.getList().add(this);
		
	}//constructor

	public JButton getJbtnReport() {
		return jbtnReport;
	}

	public JTextArea getJtaReport() {
		return jtaReport;
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
