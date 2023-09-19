package manageEmp;


import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
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
	private List<String> listName;
	
	

	public ManageEmp(HCYErp hcyE) {
		this.hcyE = hcyE;
		ManageEmpEvt event = new ManageEmpEvt(this);
		ManageEmpDAO meDAO = ManageEmpDAO.getInstance();

		setLayout(null);
		
		//타이틀바 디자인
		TitledBorder titleBorderDept=BorderFactory.createTitledBorder("부서");
		TitledBorder titleBorderTeam=BorderFactory.createTitledBorder("팀");
		TitledBorder titleBorderEmp=BorderFactory.createTitledBorder("사원");
		Font titleFont=new Font("맑은 고딕",Font.BOLD,15);
		titleBorderDept.setTitleFont(titleFont);
		titleBorderTeam.setTitleFont(titleFont);
		titleBorderEmp.setTitleFont(titleFont);
		
		// 부서 리스트
		dlmDept = new DefaultListModel<String>();
		jlDepartment = new JList<String>(dlmDept);
//		for(int i =1;i<100;i++) {
//			dlmDept.addElement("부서"+i);
//		}//이거 다오 들어오면 삭제
		
		
		jspDepartment = new JScrollPane(jlDepartment);
		jspDepartment.setBounds(150, 100, 230, 400);
		jspDepartment.setBorder(titleBorderDept);
		jlDepartment.addMouseListener(event);
		add(jspDepartment);

		// 팀 리스트
		dlmteam = new DefaultListModel<String>();
		jlTeam = new JList<String>(dlmteam);
//		for(int i =1;i<100;i++) {
//			dlmteam.addElement("팀"+i);
//		}//이거 다오 들어오면 삭제
//		try {
//			for (String team : meDAO.selectTeam()) {
//				dlmteam.addElement(team);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//catch

		jspTeam = new JScrollPane(jlTeam);
		jspTeam.setBounds(430, 100, 230, 400);
		jspTeam.setBorder(titleBorderTeam);
		jlTeam.addMouseListener(event);
		add(jspTeam);

		// 사원 리스트
		dlmEmp = new DefaultListModel<String>();
		jlName = new JList<String>(dlmEmp);
//		for(int i =1;i<100;i++) {
//			dlmEmp.addElement("사원"+i);
//		}//이거 다오 들어오면 삭제
//		try {
//			listName=meDAO.selectEmp();
//			for (String emp : listName) {
//				dlmEmp.addElement(emp.substring(0,emp.indexOf("/")));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//catch
		int cnt=0;
		try {
			listName=meDAO.selectDept();
			for (String dept : listName) {
				if(dept.substring(0,dept.indexOf("/")).equals("0")) {
					cnt++;
					continue;
				}
				if(cnt==0) {
				dlmDept.addElement(dept.substring(dept.indexOf("/")+1));
				}
				if(cnt==1) {
				dlmteam.addElement(dept.substring(dept.indexOf("/")+1));
				}
				if(cnt==2) {
				dlmEmp.addElement(dept.substring(dept.indexOf("/")+1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
		jspName = new JScrollPane(jlName);
		jspName.setBounds(710, 100, 230, 400);
		jspName.setBorder(titleBorderEmp);
		jlName.addMouseListener(event);
		add(jspName);

		// 로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000, 510, 150, 40);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		jbtnLogOut.setFont(LogOutFont);
		jbtnLogOut.setForeground(Color.BLACK);
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
		
		//글꼴 폰트 설정
		Font font=new Font("맑은 고딕",Font.PLAIN,14);
		jlDepartment.setFont(font);
		jlTeam.setFont(font);
		jlName.setFont(font);
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

	public void setDlmDept(DefaultListModel<String> dlmDept) {
		this.dlmDept = dlmDept;
	}

	public List<String> getListName() {
		return listName;
	}
	
}// class