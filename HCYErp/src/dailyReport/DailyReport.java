package dailyReport;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

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
		Calendar cal = Calendar.getInstance();
		String month = Integer.toString(cal.get(Calendar.MONTH)+1);
		String date = Integer.toString(cal.get(Calendar.DATE));
		if(month.length()==1) {month="0"+month;}
		if(date.length()==1) {date="0"+date;}
		jtaReport = new JTextArea(cal.get(Calendar.YEAR)+"-"+month+"-"+cal.get(Calendar.DATE)+" 사원명\n\n\n업무내용\n-\n-\n-");
		jtaReport.setBounds(190,100,800,350);
		jtaReport.setBorder(new TitledBorder(""));
		Font jtaFont = new Font("맑은 고딕", Font.PLAIN, 15);
		jtaReport.setFont(jtaFont);
		add(jtaReport);
		
		//작성 버튼
		jbtnReport = new JButton("작성완료");
		jbtnReport.setBounds(490,500,180,50);
		jbtnReport.setBackground(new Color(0x3322A8));
		Font ReportBtnFont = new Font("맑은 고딕", Font.BOLD, 17);
		jbtnReport.setFont(ReportBtnFont);
		jbtnReport.setForeground(Color.WHITE);
		jbtnReport.addActionListener(event);
		add(jbtnReport);
		
		//로그아웃 버튼 
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		jbtnLogOut.setFont(LogOutFont);
		jbtnLogOut.setForeground(Color.BLACK);
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);

		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/HCYErpFile/images/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(913,450,300,300);
		add(jlblLogoTxt);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/HCYErpFile/images/HCYErp배경.png"));
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
