package manageDailyReport;

import java.awt.Color;

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
		
		jtaDailyReport.setBounds(0,0,500,200);
		jbtnModify.setBounds(100,215,100,30);
		jbtnModify.setBackground(new Color(0x8244AD));
		jbtnCancel.setBounds(270,215,100,30);
		jbtnCancel.setBackground(new Color(0xE0E0E0));
		
		add(jtaDailyReport);
		add(jbtnModify);
		add(jbtnCancel);
		
		setBounds(mdr.getX()+600,mdr.getY()+200,500,300);
		setVisible(true);
		
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
