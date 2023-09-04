package dailyReport;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders;

public class DailyReport extends JPanel {
	private JButton jbtnReport;
	private JTextArea jtaReport;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	
	public DailyReport() {
		setLayout(null);
		
		//업무일지 에리어
		jtaReport = new JTextArea();
		jtaReport.setBounds(190,100,800,350);
		jtaReport.setBorder(new TitledBorder(""));
		add(jtaReport);
		
		//작성 버튼
		jbtnReport = new JButton("작성완료");
		jbtnReport.setBounds(480,470,200,70);
		add(jbtnReport);
		
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
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
	
	
}//class
