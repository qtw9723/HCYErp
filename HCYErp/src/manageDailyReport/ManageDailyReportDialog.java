package manageDailyReport;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import VO.DailyReportVO;

@SuppressWarnings("serial")
public class ManageDailyReportDialog extends JDialog {
	private ManageDailyReport mdr;
	private JTextArea jtaDailyReport;
	private JButton jbtnModify;
	private JButton jbtnCancel;
	
	public ManageDailyReportDialog(ManageDailyReport mdr, String content) {
		this.mdr=mdr;
		
		//다이얼로그 배경색
		getContentPane().setBackground(new Color(255,245,245));
		
		//textarea선언
		jtaDailyReport=new JTextArea(content);
		
		//button선언
		jbtnModify=new JButton("수정완료");
		jbtnCancel=new JButton("취소");
		
		//이벤트 클래스에 연결
		ManageDailyReportDialogEvt mdrde=new ManageDailyReportDialogEvt(this);
		jbtnModify.addActionListener(mdrde);
		jbtnCancel.addActionListener(mdrde);
		
		setLayout(null);
		
		//폰트
		Font jtaFont = new Font("맑은 고딕", Font.PLAIN, 14);
		jtaDailyReport.setFont(jtaFont);
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		jbtnModify.setFont(jbtnFont);
		jbtnCancel.setFont(jbtnFont);
		jbtnModify.setForeground(Color.white);
		jbtnCancel.setForeground(Color.white);
		
		jtaDailyReport.setBounds(30,30,620,450);
		jbtnModify.setBounds(230,500,100,40);
		jbtnModify.setBackground(new Color(0x6D47B0));
		jbtnCancel.setBounds(370,500,100,40);
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		
		add(jtaDailyReport);
		add(jbtnModify);
		add(jbtnCancel);
		
		setBackground(new Color(255,245,245));
		setTitle("업무일지 조회");
		setBounds(mdr.getX()+600,mdr.getY()+200,698,600);
		setVisible(true);
		setResizable(false);
		
	}//constructor
	
	
	public JTextArea getJtaDailyReport() {
		return jtaDailyReport;
	}
	public JButton getJbtnModify() {
		return jbtnModify;
	}
	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public ManageDailyReport getMdr() {
		return mdr;
	}
	
}//class
