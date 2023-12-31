package login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import VO.EmpVO;
import attendance.Attendance;
import attendance.AttendanceDAO;
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
	private int selectedIndex;
	private static final int ManageDoc = 1;
	private static final int ManageDailyReport = 3;
	private static final int ManageEmp = 4;
	private static final int ManageMonthlyAttendance = 5;
	private static final int ManagePersonalAttendance = 6;
	private static final int ManageEmpRegister = 7;
	private static final int ManageLeave = 8;

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
		if(e.getSource()==hcyE.getJpfPass()) {
			try {
				login();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		new ResetPassDialog().setBounds(hcyE.getX() + 300, hcyE.getY() + 150, 600, 400);
	}// mouseClicked

	@Override
	public void mouseEntered(MouseEvent e) {
		hcyE.getJlblresetPass().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}// mouseEntered

	public void login() throws SQLException {
		if (hcyE.getJtfEmpNo().getText().isEmpty() && hcyE.getJpfPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(hcyE, "사원번호와 비밀번호를 입력해주세요!!!");
			return;
		}
		if (hcyE.getJtfEmpNo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(hcyE, "사원번호를 먼저 입력해 주세요!");
			return;
		}
		if (hcyE.getJpfPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(hcyE, "비밀번호를 입력해 주세요!!");
			return;
		}
		char[] passwordChar = hcyE.getJpfPass().getPassword();
		String password = new String(passwordChar);
		empNo = Integer.parseInt(hcyE.getJtfEmpNo().getText());
		hcyE.setUser(empNo);
		HCYErpDAO hcyEDAO = HCYErpDAO.getInstance();
		if (hcyEDAO.selectLogin(empNo) == true&& password.equals(hcyEDAO.geteVO().getPass())) {
		Calendar cal = Calendar.getInstance();
		ManageEmp me = new ManageEmp(hcyE);
		ManageDoc md = new ManageDoc(hcyE);
		ManageDailyReport mdr = new ManageDailyReport(hcyE);
		ManageMonthlyAttendance mma = new ManageMonthlyAttendance(hcyE);
		ManagePersonalAttendance mpa = new ManagePersonalAttendance(hcyE);
		ManageEmpRegister mer = new ManageEmpRegister(hcyE);
		ManageLeave ml = new ManageLeave(hcyE);
		EmpVO eVO = new EmpVO();
		AttendanceDAO aDAO = AttendanceDAO.getInstance();
		eVO = aDAO.selectEmp(hcyE.getUser());
				hcyE.removeComponent();
				hcyE.setTabbedPane(new JTabbedPane());
				// JTabbedPane의 너비와 높이를 설정

				hcyE.getTabbedPane().addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						selectedIndex = hcyE.getTabbedPane().getSelectedIndex();
						switch (selectedIndex) {
						case ManageDoc:
							for (Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
								entry.getValue().setSelected(false);
							}
						case ManageDailyReport:
							mdr.getJcbYear().setSelectedItem(cal.get(Calendar.YEAR));
							mdr.getJcbMonth().setSelectedItem(cal.get(Calendar.MONTH) + 1);
							mdr.getJcbDay().setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
							mdr.getJcbEmp().setSelectedIndex(0);
						case ManageEmp:
							int cnt = 0;
							me.getDlmDept().removeAllElements();
							me.getDlmteam().removeAllElements();
							me.getDlmEmp().removeAllElements();
							for (String dept : me.getListName()) {
								if (dept.substring(0, dept.indexOf("/")).equals("0")) {
									cnt++;
									continue;
								}
								if (cnt == 0) {
									me.getDlmDept().addElement(dept.substring(dept.indexOf("/") + 1));
								}
								if (cnt == 1) {
									me.getDlmteam().addElement(dept.substring(dept.indexOf("/") + 1));
								}
								if (cnt == 2) {
									me.getDlmEmp().addElement(dept.substring(dept.indexOf("/") + 1));
								}
							}
						case ManageMonthlyAttendance:
							mma.getJcbYear().setSelectedItem(cal.get(Calendar.YEAR));
							mma.getJcbMonth().setSelectedItem(cal.get(Calendar.MONTH) + 1);
						case ManagePersonalAttendance:
							mpa.getJcbEmp().setSelectedIndex(0);
						case ManageEmpRegister:
							mer.getJtRegiAbInfo().clearSelection();
						case ManageLeave:
							ml.getJtLeaveProposal().clearSelection();
							break;

						}
					}// stateChanged
				});
				// JTabbedPane 가져오기
				JTabbedPane tabbedPane = hcyE.getTabbedPane();

				// 사용자 정의 UI 클래스를 설정합니다.
				tabbedPane.setUI(new CustomTabbedPaneUI());

				Font tabFont = new Font("맑은 고딕", Font.BOLD, 16);
				hcyE.getTabbedPane().setFont(tabFont);
				hcyE.getTabbedPane().setForeground(Color.white);
				hcyE.getTabbedPane().setBackground(new Color(0x9D78DB));
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
			JOptionPane.showMessageDialog(hcyE, "아이디 혹은 비밀번호가 잘못되었습니다.");
		} // else

	}// login

	public class CustomTabbedPaneUI extends BasicTabbedPaneUI {
		private int tabHeight = 40; // 원하는 탭의 위아래 넓이

		@Override
		public int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
			return tabHeight;
		}// calculateTabHeight

		@Override
		public int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
			// 기본 넓이 계산
			int width = super.calculateTabWidth(tabPlacement, tabIndex, metrics);

			// 탭 넓이 조절

			return width + 10;
		}// calculateTabWidth

		@Override
		protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
				boolean isSelected) {
			if (isSelected) {
				// 선택된 탭 색 적용
				g.setColor(new Color(0x401A83));
				g.fillRect(x, y, w, h);
			} else {
				// 선택되지 않은 탭은 기본색을 사용합니다.
				super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
			} // end else
		}// paintTabBackground

	}// CustomTabbedPaneUI

	public int getSelectedIndex() {
		return selectedIndex;
	}

}// class
