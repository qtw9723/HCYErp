package manageDailyReport;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import login.HCYErp;

@SuppressWarnings("serial")
public class ManageDailyReport extends JPanel {
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JComboBox<Integer> jcbDay;
	private JComboBox<String> jcbEmp;
	private JButton jbtnDateSearch;
	private JButton jbtnEmpSearch;
	private JTable jtReport;
	private JScrollPane jspReport;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private HCYErp hcyE;
	private DefaultTableModel dtmReport;
	private JLabel jlblYear;
	private JLabel jlblMonth;
	private JLabel jlblDay;

	public ManageDailyReport(HCYErp hcyE) throws SQLException {
		this.hcyE = hcyE;
		ManageDailyReportEvt event = new ManageDailyReportEvt(this);

		setLayout(null);

		// 연월일 콤박
		Font jcbFont = new Font("맑은 고딕", Font.BOLD, 15);
		// 연 콤박
		jcbYear = new JComboBox<Integer>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		jcbYear.addItem(year - 1);
		jcbYear.addItem(year);
		jcbYear.addItem(year + 1);
		jcbYear.setSelectedItem(year);
		jcbYear.setBounds(150, 80, 100, 40);
		jcbYear.setBackground(new Color(0xffffff));
		jcbYear.setFont(jcbFont);
		add(jcbYear);
		// 월 콤박
		jcbMonth = new JComboBox<Integer>();
		for (int i = 1; i < 13; i++) {
			jcbMonth.addItem(i);
		} // for
		jcbMonth.setSelectedItem(cal.get(Calendar.MONTH) + 1);
		jcbMonth.setBounds(300, 80, 70, 40);
		jcbMonth.setBackground(new Color(0xffffff));
		jcbMonth.addActionListener(event);
		jcbMonth.setFont(jcbFont);
		add(jcbMonth);
		// 일 콤박
		jcbDay = new JComboBox<Integer>();
		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			jcbDay.addItem(i);
		} // for
		jcbDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		jcbDay.setBounds(420, 80, 70, 40);
		jcbDay.setBackground(new Color(0xffffff));
		jcbDay.setFont(jcbFont);
		add(jcbDay);
		
		//연월일 라벨
		jlblYear = new JLabel("년");
		jlblMonth = new JLabel("월");
		jlblDay = new JLabel("일");
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 15);
		jlblYear.setFont(jlblFont);
		jlblMonth.setFont(jlblFont);
		jlblDay.setFont(jlblFont);
		jlblYear.setForeground(Color.black);
		jlblMonth.setForeground(Color.black);
		jlblDay.setForeground(Color.black);
		jlblYear.setBounds(255, 80, 40, 40);
		jlblMonth.setBounds(375, 80, 40, 40);
		jlblDay.setBounds(495, 80, 40, 40);
		add(jlblYear);
		add(jlblMonth);
		add(jlblDay);

		// 일자 조회 버튼
		jbtnDateSearch = new JButton("조회");
		jbtnDateSearch.setBounds(530, 80, 70, 40);
		jbtnDateSearch.setBackground(new Color(0x6252CD));
		Font searchBtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		jbtnDateSearch.setFont(searchBtnFont);
		jbtnDateSearch.setForeground(Color.WHITE);
		jbtnDateSearch.addActionListener(event);
		add(jbtnDateSearch);

		// 이름 콤박
		jcbEmp = new JComboBox<String>();
		int teamno = ManageDailyReportDAO.getInstance().selectTeamno(hcyE.getUser());
		if (!(teamno == 13 || teamno == 91)) {
			try {
				List<String> empList = ManageDailyReportDAO.getInstance().selectEmp(hcyE.getUser());
				for (String emp : empList) {
					jcbEmp.addItem(emp.substring(0, emp.indexOf("/", 5)));
				} // for
			} catch (SQLException e) {
				e.printStackTrace();
			} // catch
		}else {
			List<String> empList=ManageDailyReportDAO.getInstance().selectEmpBoss();
			for (String emp : empList) {
				jcbEmp.addItem(emp.substring(0, emp.indexOf("/", 5)));
			} // for
		}
		jcbEmp.setBounds(675, 80, 180, 40);
		jcbEmp.setBackground(new Color(0xffffff));
		jcbEmp.setFont(jcbFont);
		add(jcbEmp);
		

		// 이름 조회 버튼
		jbtnEmpSearch = new JButton("조회");
		jbtnEmpSearch.setBounds(880, 80, 70, 40);
		jbtnEmpSearch.setBackground(new Color(0x6252CD));
		jbtnEmpSearch.setFont(searchBtnFont);
		jbtnEmpSearch.setForeground(Color.WHITE);
		jbtnEmpSearch.addActionListener(event);
		add(jbtnEmpSearch);

		// 업무일지 리스트
		dtmReport = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 모든 셀을 수정 불가능하게 설정
				return false;
			}// isCellEditable
		};
		jtReport = new JTable(dtmReport);
		
		//셀 간격
		jtReport.setRowHeight(25);
		
		//이동,크기 조절 불가
		jtReport.getTableHeader().setReorderingAllowed(false);
		jtReport.getTableHeader().setResizingAllowed(false);
				
		dtmReport.addColumn("사원번호");
		dtmReport.addColumn("사원이름");
		dtmReport.addColumn("내용 요약");
		dtmReport.addColumn("일지등록일");
		
		//JTable header font설정
		Font headerFont=new Font("맑은 고딕",Font.BOLD,15);
		JTableHeader tableHeader=jtReport.getTableHeader();
		tableHeader.setFont(headerFont);
		tableHeader.setBackground(new Color(213,232,212));
		
		//JTable cell 간격
		TableColumnModel columnModel =jtReport.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(100);
						
		//cell font설정
		Font jtFont=new Font("맑은 고딕",Font.PLAIN,15);
		jtReport.setFont(jtFont);
		
		//JTable cell 가운데 정렬
		DefaultTableCellRenderer cellCenter=new DefaultTableCellRenderer();
		cellCenter.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<jtReport.getColumnCount();i++) {
			jtReport.getColumnModel().getColumn(i).setCellRenderer(cellCenter);
		}//for
				
		jspReport = new JScrollPane(jtReport);
		jspReport.setBounds(150, 160, 800, 390);
		//JTable 밑에 빈 공간들 색
		jspReport.getViewport().setBackground(new Color(0xECEBFF));
		
		add(jspReport);

		jtReport.addMouseListener(event);

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
		jlblLogoTxt.setBounds(913, 450, 300, 300);
		add(jlblLogoTxt);

		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		hcyE.getList().add(this);
	}// constructor

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JComboBox<Integer> getJcbDay() {
		return jcbDay;
	}

	public JComboBox<String> getJcbEmp() {
		return jcbEmp;
	}

	public JButton getJbtnDateSearch() {
		return jbtnDateSearch;
	}

	public JButton getJbtnEmpSearch() {
		return jbtnEmpSearch;
	}

	public JTable getJtReport() {
		return jtReport;
	}

	public JScrollPane getJspReport() {
		return jspReport;
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

	public DefaultTableModel getDtmReport() {
		return dtmReport;
	}

	public void setDtmReport(DefaultTableModel dtmReport) {
		this.dtmReport = dtmReport;
	}

}// class
