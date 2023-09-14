package manageEmp;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import attendance.Attendance;
import dailyReport.DailyReport;
import login.HCYErp;
import manageAttendance.ManageMonthlyAttendance;
import manageAttendance.ManagePersonalAttendance;
import manageDailyReport.ManageDailyReport;
import manageDoc.ManageDoc;
import manageEmpRegister.ManageEmpRegister;
import manageLeave.ManageLeave;

@SuppressWarnings("serial")
public class ManageEmp extends JPanel {
	private JList<String> jlDepartment;
	private JList<String> jlTeam;
	private JList<String> jlName;
	private JScrollPane jspDepartment;
	private JScrollPane jspTeam;
	private JScrollPane jspName;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private HCYErp hcyE;
	private DefaultListModel<String> dlmDept;
	private DefaultListModel<String> dlmteam;
	private DefaultListModel<String> dlmEmp;
	

	public ManageEmp(HCYErp hcyE) {
		this.hcyE = hcyE;
		ManageEmpEvt event = new ManageEmpEvt(this);
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();

		setLayout(null);

		// 부서 리스트
		dlmDept = new DefaultListModel<String>();
		jlDepartment = new JList<String>(dlmDept);
//		for(int i =1;i<100;i++) {
//			dlmDept.addElement("부서"+i);
//		}//이거 다오 들어오면 삭제
		try {
			for (String dept : meDAO.selectDept()) {
				dlmDept.addElement(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
		jspDepartment = new JScrollPane(jlDepartment);
		jspDepartment.setBounds(150, 100, 230, 400);
		jspDepartment.setBorder(new TitledBorder("부서"));
		jlDepartment.addListSelectionListener(event);
		add(jspDepartment);

		// 팀 리스트
		dlmteam = new DefaultListModel<String>();
		jlTeam = new JList<String>(dlmteam);
//		for(int i =1;i<100;i++) {
//			dlmteam.addElement("팀"+i);
//		}//이거 다오 들어오면 삭제
		try {
			for (String team : meDAO.selectTeam()) {
				dlmteam.addElement(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch

		jspTeam = new JScrollPane(jlTeam);
		jspTeam.setBounds(430, 100, 230, 400);
		jspTeam.setBorder(new TitledBorder("팀"));
		jlTeam.addListSelectionListener(event);
		add(jspTeam);

		// 사원 리스트
		dlmEmp = new DefaultListModel<String>();
		jlName = new JList<String>(dlmEmp);
//		for(int i =1;i<100;i++) {
//			dlmEmp.addElement("사원"+i);
//		}//이거 다오 들어오면 삭제
		try {
			for (String emp : meDAO.selectEmp()) {
				dlmEmp.addElement(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		jspName = new JScrollPane(jlName);
		jspName.setBounds(710, 100, 230, 400);
		jspName.setBorder(new TitledBorder("사원"));
		jlName.addListSelectionListener(event);
		add(jspName);

		// 로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000, 510, 150, 40);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		
		// 텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930, 450, 300, 300);
		add(jlblLogoTxt);

		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
	}// constructor

	public JList<String> getJlDepartment() {
		return jlDepartment;
	}

	public JList<String> getJlTeam() {
		return jlTeam;
	}

	public JList<String> getJlName() {
		return jlName;
	}

	public JScrollPane getJspDepartment() {
		return jspDepartment;
	}

	public JScrollPane getJspTeam() {
		return jspTeam;
	}

	public JScrollPane getJspName() {
		return jspName;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public HCYErp getHcyE() {
		return hcyE;
	}

	public DefaultListModel<String> getDlmDept() {
		return dlmDept;
	}

	public DefaultListModel<String> getDlmteam() {
		return dlmteam;
	}

	public DefaultListModel<String> getDlmEmp() {
		return dlmEmp;
	}
}// class
