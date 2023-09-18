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
		new ResetPassDialog().setBounds(hcyE.getX() + 200, hcyE.getY() + 150, 800, 400);
	}// mouseClicked

	@Override
	public void mouseEntered(MouseEvent e) {
		hcyE.getJlblresetPass().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}// mouseEntered

//	@Override
//	public void mouseExited(MouseEvent e) {
//		
//	}

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
		eVO=aDAO.selectEmp(hcyE.getUser());
		if (hcyEDAO.selectLogin(empNo) == true) {
			if (password.equals(hcyEDAO.geteVO().getPass())) {
				if (eVO.getTeam().equals("인사")||eVO.getTeam().equals("임원")) {
					hcyE.removeComponent();
					hcyE.setTabbedPane(new JTabbedPane());
					hcyE.add(hcyE.getTabbedPane());
					hcyE.getTabbedPane().add("출근", new Attendance(hcyE));
					hcyE.getTabbedPane().add("문서관리", md);
					hcyE.getTabbedPane().add("업무일지 작성", new DailyReport(hcyE));
					hcyE.getTabbedPane().add("업무일지 관리", mdr);
					hcyE.getTabbedPane().add("사원정보 관리", me);
					hcyE.getTabbedPane().add("입퇴사 관리", new ManageEmpRegister(hcyE));
					hcyE.getTabbedPane().add("월별 사원근태 관리", mma);
					hcyE.getTabbedPane().add("사원명별 사원근태 관리", mpa);
					hcyE.getTabbedPane().add("휴가 관리", new ManageLeave(hcyE));
					hcyE.getTabbedPane().addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							int selectedIndex = hcyE.getTabbedPane().getSelectedIndex();
							switch (selectedIndex) {
							case 1:
								JCheckBox jcbMap = null;
								for (Map.Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
									jcbMap = entry.getValue();
									jcbMap.setSelected(false);
								}
							case 3:
								Calendar cal = Calendar.getInstance();
								int year = cal.get(Calendar.YEAR);
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								mdr.getJcbYear().setSelectedItem(year);
								mdr.getJcbMonth().setSelectedItem(month + 1);
								mdr.getJcbDay().setSelectedItem(day);

							case 4:
								ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
								me.getDlmDept().removeAllElements();
								try {
									for (String dept : meDAO.selectDept()) {
										me.getDlmDept().addElement(dept);
									}
								} catch (SQLException se) {
									// TODO Auto-generated catch block
									se.printStackTrace();
								}
								me.getDlmteam().removeAllElements();
								try {
									for (String team : meDAO.selectTeam()) {
										me.getDlmteam().addElement(team);
									}
								} catch (SQLException se) {
									// TODO Auto-generated catch block
									se.printStackTrace();
								}
								me.getDlmEmp().removeAllElements();
								try {
									for (String emp : meDAO.selectEmp()) {
										me.getDlmEmp().addElement(emp.substring(0,emp.indexOf("/")));
									}
								} catch (SQLException se) {
									// TODO Auto-generated catch block
									se.printStackTrace();
								} // catch
							case 5:
								Calendar cal1 = Calendar.getInstance();
								int year1 = cal1.get(Calendar.YEAR);
								int month1 = cal1.get(Calendar.MONTH);
								mma.getJcbYear().setSelectedItem(year1);
								mma.getJcbMonth().setSelectedItem(month1 + 1);
							case 6:
								mpa.getJcbEmp().setSelectedIndex(0);
							}// switch
						}// stateChanged
					});
				} // if
				else {
					hcyE.removeComponent();
					hcyE.setTabbedPane(new JTabbedPane());
					hcyE.add(hcyE.getTabbedPane());
					hcyE.getTabbedPane().add("출근", new Attendance(hcyE));
					hcyE.getTabbedPane().add("문서관리", md);
					hcyE.getTabbedPane().add("업무일지 작성", new DailyReport(hcyE));
					hcyE.getTabbedPane().add("업무일지 관리", mdr);
					hcyE.getTabbedPane().add("사원정보 관리", me);
					hcyE.getTabbedPane().add("월별 사원근태 관리", mma);
					hcyE.getTabbedPane().add("사원명별 사원근태 관리", mpa);
					hcyE.getTabbedPane().addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							int selectedIndex = hcyE.getTabbedPane().getSelectedIndex();
							switch (selectedIndex) {
							case 1:
								JCheckBox jcbMap = null;
								for (Map.Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
									jcbMap = entry.getValue();
									jcbMap.setSelected(false);
								}
							case 3:
								Calendar cal = Calendar.getInstance();
								int year = cal.get(Calendar.YEAR);
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								mdr.getJcbYear().setSelectedItem(year);
								mdr.getJcbMonth().setSelectedItem(month + 1);
								mdr.getJcbDay().setSelectedItem(day);

							case 4:
								ManageEmpDAO meDAO = ManageEmpDAO.getInstance();
								me.getDlmDept().removeAllElements();
								try {
									for (String dept : meDAO.selectDept()) {
										me.getDlmDept().addElement(dept);
									}
								} catch (SQLException se) {
									// TODO Auto-generated catch block
									se.printStackTrace();
								}
								me.getDlmteam().removeAllElements();
								try {
									for (String team : meDAO.selectTeam()) {
										me.getDlmteam().addElement(team);
									}
								} catch (SQLException se) {
									// TODO Auto-generated catch block
									se.printStackTrace();
								}
								me.getDlmEmp().removeAllElements();
								try {
									for (String emp : meDAO.selectEmp()) {
										me.getDlmEmp().addElement(emp.substring(0,emp.indexOf("/")));
									}
								} catch (SQLException se) {
									// TODO Auto-generated catch block
									se.printStackTrace();
								} // catch
							case 6:
								Calendar cal1 = Calendar.getInstance();
								int year1 = cal1.get(Calendar.YEAR);
								int month1 = cal1.get(Calendar.MONTH);
								mma.getJcbYear().setSelectedItem(year1);
								mma.getJcbMonth().setSelectedItem(month1 + 1);
							case 7:
								if(mpa.getJcbEmp().getItemCount()!=0) {
								mpa.getJcbEmp().setSelectedIndex(0);
								}
							}// switch
						}// stateChanged
					});
				}//else
			} else {
				JOptionPane.showMessageDialog(hcyE, "아이디혹은 비밀번호가 잘못되었습니다.");
			} // else
		} // if

	}// login
}// class
