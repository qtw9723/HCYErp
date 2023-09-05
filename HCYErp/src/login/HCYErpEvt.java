package login;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import VO.EmpVO;
import attendance.Attendance;
import dailyReport.DailyReport;
import manageAttendance.ManageAttendance;
import manageDailyReport.ManageDailyReport;
import manageDoc.ManageDoc;
import manageEmp.ManageEmp;
import manageEmpRegister.ManageEmpRegister;
import manageLeave.ManageLeave;

public class HCYErpEvt extends MouseAdapter implements ActionListener {

	private HCYErp hcyE;
	private int empNo;
	private EmpVO eVO;

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
			try {
				login();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			hcyE.removeComponent();
			JTabbedPane jt=new JTabbedPane();
			hcyE.add(jt);		
			jt.add("출근",new Attendance(hcyE));
			jt.add("문서관리",new ManageDoc(hcyE));
			jt.add("업무일지 작성",new DailyReport(hcyE));
			jt.add("업무일지 관리",new ManageDailyReport(hcyE));
			jt.add("사원정보 관리",new ManageEmp(hcyE));
			jt.add("사원정보 관리",new ManageEmpRegister(hcyE));
			jt.add("사원정보 관리",new ManageAttendance(hcyE));
			jt.add("사원정보 관리",new ManageLeave(hcyE));
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
	
	public void login() throws SQLException {
		
		HCYErpDAO hcyEDAO=HCYErpDAO.getInstance();
		
		char[] passwordChars=hcyE.getJpfPass().getPassword();
		String password=new String(passwordChars);
		
		eVO=new EmpVO(Integer.parseInt(hcyE.getJtfEmpNo().getText()),password);
		System.out.println(eVO.getEmpNo()+" / "+password);
		
		hcyEDAO.selectLogin(eVO);
		
//		if(hcyEDAO.selectLogin(eVO)==false) {
//			JOptionPane.showMessageDialog(hcyE, "아이디 또는 비밀번호가 잘못되었습니다.");
//		}else {	
////			hcyE.getJtfEmpNo().getText()=eVO.getEm
//		}
		
		
		
	}


}
