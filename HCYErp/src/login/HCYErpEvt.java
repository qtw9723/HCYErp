package login;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import VO.EmpVO;
import attendance.Attendance;
import attendance.AttendanceDAO;
import dailyReport.DailyReport;
import manageAttendance.ManageMonthlyAttendance;
import manageAttendance.ManagePersonalAttendance;
import manageDailyReport.ManageDailyReport;
import manageDoc.ManageDoc;
import manageEmp.ManageEmp;
import manageEmp.ManageEmpDAO;
import manageEmpRegister.ManageEmpRegister;
import manageLeave.ManageLeave;

public class HCYErpEvt extends MouseAdapter implements ActionListener {

	private HCYErp hcyE;
	private int empNo;
	private int selectedIndex;

	public HCYErpEvt(HCYErp hcyE) {
		this.hcyE = hcyE;
		hcyE.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				hcyE.dispose();
			};// windowClosing
		});// addWindowListender
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == hcyE.getJbtnLogIn()) {
			try {
				login();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} // catch
		} // if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		new ResetPassDialog().setBounds(hcyE.getX() + 200, hcyE.getY() + 150, 600, 400);
	}// mouseClicked

	@Override
	public void mouseEntered(MouseEvent e) {
		hcyE.getJlblresetPass().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}// mouseEntered

	public void login() throws SQLException {
	
		HCYErpDAO hcyEDAO = HCYErpDAO.getInstance();

		char[] passwordChar = hcyE.getJpfPass().getPassword();
		String password = new String(passwordChar);
		empNo = Integer.parseInt(hcyE.getJtfEmpNo().getText());
		hcyE.setUser(empNo);
		ManageEmp me = new ManageEmp(hcyE);
		ManageDoc md = new ManageDoc(hcyE);
		ManageDailyReport mdr = new ManageDailyReport(hcyE);
		ManageMonthlyAttendance mma = new ManageMonthlyAttendance(hcyE);
		ManagePersonalAttendance mpa = new ManagePersonalAttendance(hcyE);
		EmpVO eVO = new EmpVO();
		AttendanceDAO aDAO = AttendanceDAO.getInstance();
		eVO = aDAO.selectEmp(hcyE.getUser());
		if (hcyEDAO.selectLogin(empNo) == true) {
			if (password.equals(hcyEDAO.geteVO().getPass())) {
				hcyE.removeComponent();
				hcyE.setTabbedPane(new JTabbedPane());
				hcyE.getTabbedPane().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						selectedIndex = hcyE.getTabbedPane().getSelectedIndex();
						refresh(selectedIndex);
					}// stateChanged
				});
				hcyE.add(hcyE.getTabbedPane());
				hcyE.getTabbedPane().add("출근", new Attendance(hcyE));
				hcyE.getTabbedPane().add("문서관리", md);
				hcyE.getTabbedPane().add("업무일지 작성", new DailyReport(hcyE));
				hcyE.getTabbedPane().add("업무일지 관리", mdr);
				hcyE.getTabbedPane().add("사원정보 관리", me);
				hcyE.getTabbedPane().add("월별 사원근태 관리", mma);
				hcyE.getTabbedPane().add("사원명별 사원근태 관리", mpa);
				if (eVO.getTeam().equals("인사") || eVO.getTeam().equals("임원")) {
					hcyE.getTabbedPane().add("입퇴사 관리", new ManageEmpRegister(hcyE));
					hcyE.getTabbedPane().add("휴가 관리", new ManageLeave(hcyE));
				}

//				} // else
			} else {
				JOptionPane.showMessageDialog(hcyE, "아이디혹은 비밀번호가 잘못되었습니다.");
			} // else
		} // if

	}// login

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void refresh(int index) {
//		hcyE.getTabbedPane().setVisible(false);
//		hcyE.addComponent();
		try {
			hcyE.getEvent().login();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hcyE.getTabbedPane().setSelectedIndex(index);
	}

}// class
