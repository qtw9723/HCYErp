package login;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.DefaultTableCellRenderer;

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
	private int selectedIndex=0;

	private static final int ManageDoc=1;
	private static final int ManageDailyReport=3;
	private static final int ManageEmp=4;
	private static final int ManageMonthlyAttendance=5;
	private static final int ManagePersonalAttendance=6;
	private static final int ManageEmpRegister=7;
	private static final int ManageLeave=8;
	
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
		Calendar cal=Calendar.getInstance();
		
		char[] passwordChar = hcyE.getJpfPass().getPassword();
		String password = new String(passwordChar);
		empNo = Integer.parseInt(hcyE.getJtfEmpNo().getText());
		hcyE.setUser(empNo);
		ManageEmp me = new ManageEmp(hcyE);
		ManageDoc md = new ManageDoc(hcyE);
		ManageDailyReport mdr = new ManageDailyReport(hcyE);
		ManageMonthlyAttendance mma = new ManageMonthlyAttendance(hcyE);
		ManagePersonalAttendance mpa = new ManagePersonalAttendance(hcyE);
		ManageEmpRegister mer=new ManageEmpRegister(hcyE);
		ManageLeave ml=new ManageLeave(hcyE);
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
						switch (selectedIndex) {
						case ManageDoc:
							for(Map.Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
								entry.getValue().setSelected(false);
							}
							break;
						case ManageDailyReport:
							
							mdr.getJcbYear().setSelectedItem(cal.get(Calendar.YEAR));
							mdr.getJcbMonth().setSelectedItem(cal.get(Calendar.MONTH)+1);
							mdr.getJcbDay().setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
							mdr.getJcbEmp().setSelectedIndex(0);
							break;

						case ManageEmp:
							ManageEmpDAO meDAO=ManageEmpDAO.getInstance();
							me.getDlmDept().removeAllElements();
							me.getDlmteam().removeAllElements();
							me.getDlmEmp().removeAllElements();
							int cnt=0;
							try {
								List<String> listName=meDAO.selectDept();
								for (String dept : listName) {
									if(dept.substring(0,dept.indexOf("/")).equals("0")) {
										cnt++;
										continue;
									}
									if(cnt==0) {
									me.getDlmDept().addElement(dept.substring(dept.indexOf("/")+1));
									}
									if(cnt==1) {
									me.getDlmteam().addElement(dept.substring(dept.indexOf("/")+1));
									}
									if(cnt==2) {
									me.getDlmEmp().addElement(dept.substring(dept.indexOf("/")+1));
									}
								}
							} catch (SQLException se) {
								se.printStackTrace();
							}//catch
						case ManageMonthlyAttendance:
							mma.getJcbYear().setSelectedItem(cal.get(Calendar.YEAR));
							mma.getJcbMonth().setSelectedItem(cal.get(Calendar.MONTH)+1);
						case ManagePersonalAttendance:
							mpa.getJcbEmp().setSelectedIndex(0);
						case ManageEmpRegister:
							mer.getJtRegiAbInfo().clearSelection();
						case ManageLeave:
							ml.getJtLeaveProposal().clearSelection();
						default:
							break;
						}
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
					hcyE.getTabbedPane().add("입퇴사 관리", mer);
					hcyE.getTabbedPane().add("휴가 관리", ml);
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
}// class
