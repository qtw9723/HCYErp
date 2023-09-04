package manageDailyReport;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ManageDailyReportDialog extends JDialog {

	private JTextArea jtaDailyReport;
	private JButton jbtnModify;
	private JButton jbtnCancel;
	
	public ManageDailyReportDialog() {
		
		//textarea선언
		jtaDailyReport=new JTextArea();
		
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
		jbtnCancel.setBounds(270,215,100,30);
		
		add(jtaDailyReport);
		add(jbtnModify);
		add(jbtnCancel);
		
		setBounds(100,100,500,300);
		setVisible(true);
		
	}//constructor
	public static void main(String[] arg) {
		new ManageDailyReportDialog();
	}//main
	public JTextArea getJtaDailyReport() {
		return jtaDailyReport;
	}
	public JButton getJbtnModify() {
		return jbtnModify;
	}
	public JButton getJbtnCancel() {
		return jbtnCancel;
	}
	
}//class
