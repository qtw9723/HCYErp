package login;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTabbedPane;

import attendance.Attendance;
import dailyReport.DailyReport;
import manageDailyReport.ManageDailyReport;
import manageDoc.ManageDoc;
import manageEmp.ManageEmp;

public class HCYErpEvt extends MouseAdapter implements ActionListener {

	private HCYErp hcyE;
	private int empNo;

	public HCYErpEvt(HCYErp hcyE) {
		this.hcyE = hcyE;
		hcyE.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				hcyE.dispose();
			};
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==hcyE.getJbtnLogIn()) {
			hcyE.removeComponent();
			JTabbedPane jt=new JTabbedPane();
			hcyE.add(jt);		
			jt.add("출근",new Attendance(hcyE));
			jt.add("문서관리",new ManageDoc(hcyE));
			jt.add("업무일지 작성",new DailyReport(hcyE));
			jt.add("업무일지 관리",new ManageDailyReport(hcyE));
			jt.add("사원정보 관리",new ManageEmp(hcyE));
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new FindPassDialog().setBounds(hcyE.getX()+200,hcyE.getY()+150,800,400);
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		hcyE.getJlblFindPass().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		
//	}
	
	public void findPass() {
		
	}


}
