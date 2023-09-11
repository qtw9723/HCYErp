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
import manageAttendance.ManageMonthlyAttendance;
import manageAttendance.ManagePersonalAttendance;
import manageDailyReport.ManageDailyReport;
import manageDoc.ManageDoc;
import manageEmp.ManageEmp;
import manageEmpRegister.ManageEmpRegister;
import manageLeave.ManageLeave;

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
			try {
				login();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
		
		char[] passwordChar=hcyE.getJpfPass().getPassword();
		String password=new String(passwordChar);
//		eVO=new EmpVO(Integer.parseInt(hcyE.getJtfEmpNo().getText()),password);
//		System.out.println(eVO.getEmpNo()+" / "+password);
		empNo=Integer.parseInt(hcyE.getJtfEmpNo().getText());
		
		if(hcyEDAO.selectLogin(empNo)==true) {
			if(password.equals(hcyEDAO.geteVO().getPass())) {
			hcyE.setUser(empNo);
			hcyE.removeComponent();
			hcyE.setTabbedPane(new JTabbedPane());
			hcyE.add(hcyE.getTabbedPane());		
			hcyE.getTabbedPane().add("출근",new Attendance(hcyE));
			hcyE.getTabbedPane().add("문서관리",new ManageDoc(hcyE));
			hcyE.getTabbedPane().add("업무일지 작성",new DailyReport(hcyE));
			hcyE.getTabbedPane().add("업무일지 관리",new ManageDailyReport(hcyE));
			hcyE.getTabbedPane().add("사원정보 관리",new ManageEmp(hcyE));
			hcyE.getTabbedPane().add("입퇴사 관리",new ManageEmpRegister(hcyE));
			hcyE.getTabbedPane().add("월별 사원근태 관리",new ManageMonthlyAttendance(hcyE));
			hcyE.getTabbedPane().add("사원명별 사원근태 관리",new ManagePersonalAttendance(hcyE));
			hcyE.getTabbedPane().add("휴가 관리",new ManageLeave(hcyE));
			}else {
				JOptionPane.showMessageDialog(hcyE, "아이디혹은 비밀번호가 잘못되었습니다.");
			}
		}
	}


}
