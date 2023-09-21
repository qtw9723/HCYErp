package manageAttendance;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class ManageMonthlyAttendance extends JPanel{
	private JButton jbtnAttendDate;
	private JComboBox<Integer> jcbYear;
	private JComboBox<Integer> jcbMonth;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JTable jtMonthlyAttend;
	private DefaultTableModel dtmMonthlyAttend;
	private JLabel jlblYear;
	private JLabel jlblMonth;

	private HCYErp hcyE;
	
	public ManageMonthlyAttendance(HCYErp hcyE) { 
		this.hcyE = hcyE;
		setLayout(null);
		ManageMonthlyAttendanceEvt event = new ManageMonthlyAttendanceEvt(this);
		
		//연월 라벨
		jlblYear = new JLabel("년");
		jlblMonth = new JLabel("월");
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 15);
		jlblYear.setFont(jlblFont);
		jlblMonth.setFont(jlblFont);
		add(jlblYear);
		add(jlblMonth);
		jlblYear.setBounds(425, 70, 40, 35);
		jlblMonth.setBounds(545, 70, 40, 35);
		
		//연월 콤박
		Font jcbFont = new Font("맑은 고딕", Font.BOLD, 15);
		//연 콤박
		jcbYear = new JComboBox<Integer>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		jcbYear.addItem(year-1);
		jcbYear.addItem(year);
		jcbYear.addItem(year+1);
		jcbYear.setSelectedItem(year);
		jcbYear.setBounds(320,70,100,40);
		jcbYear.setBackground(new Color(0xffffff));
		jcbYear.setFont(jcbFont);
		add(jcbYear);
		//월 콤박
		jcbMonth = new JComboBox<Integer>();
		for(int i = 1;i<13;i++) {
			jcbMonth.addItem(i);
		}//for
		jcbMonth.setSelectedItem(cal.get(Calendar.MONTH)+1);
		jcbMonth.setBounds(470,70,70,40);
		jcbMonth.setBackground(new Color(0xffffff));
		jcbMonth.setFont(jcbFont);
		add(jcbMonth);
		
		//날짜 검색 버튼
		Font searchFont = new Font("맑은 고딕", Font.BOLD, 15);
		jbtnAttendDate = new JButton("조회");
		jbtnAttendDate.setBounds(610,70,100,40);
		jbtnAttendDate.setBackground(new Color(0x6D47B0));
		jbtnAttendDate.setFont(searchFont);
		jbtnAttendDate.setForeground(Color.white);
		jbtnAttendDate.addActionListener(event);
		add(jbtnAttendDate);
		
		//출근 목록
		dtmMonthlyAttend = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // 모든 셀을 수정 불가능하게 설정
		        return false;
		    }//isCellEditable
		};
		dtmMonthlyAttend.addColumn("사원번호");
		dtmMonthlyAttend.addColumn("사원이름");
		dtmMonthlyAttend.addColumn("출근시간");
		dtmMonthlyAttend.addColumn("퇴근시간");
		dtmMonthlyAttend.addColumn("출근일");

		//내용 생성
		List<Object[]> ojList = new ArrayList<Object[]>();
		
		for(Object[] oj:ojList) {
		dtmMonthlyAttend.addRow(oj);
		}//for
		jtMonthlyAttend = new JTable(dtmMonthlyAttend);
		//스크롤패인 부착
		JScrollPane jspMonthlyAttend = new JScrollPane(jtMonthlyAttend);
		jspMonthlyAttend.setBounds(120,150,800,400);
		add(jspMonthlyAttend);
		
		//크기,이동 조절 불가
		jtMonthlyAttend.getTableHeader().setReorderingAllowed(false);
		jtMonthlyAttend.getTableHeader().setResizingAllowed(false);
		
		//셀 간격
		jtMonthlyAttend.setRowHeight(25);
		
		//JTable header font설정
		Font headerFont=new Font("맑은 고딕",Font.BOLD,15);
		JTableHeader tableHeader=jtMonthlyAttend.getTableHeader();
		tableHeader.setFont(headerFont);
		tableHeader.setBackground(new Color(213,232,212));
		
		//JTable cell 간격
		TableColumnModel columnModel =jtMonthlyAttend.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		
		//cell font설정
		Font jtFont=new Font("맑은 고딕",Font.PLAIN,15);
		jtMonthlyAttend.setFont(jtFont);
		
		//JTable cell 가운데 정렬
		DefaultTableCellRenderer cellCenter=new DefaultTableCellRenderer();
		cellCenter.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<jtMonthlyAttend.getColumnCount();i++) {
			jtMonthlyAttend.getColumnModel().getColumn(i).setCellRenderer(cellCenter);
		}//for
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		jbtnLogOut.setFont(LogOutFont);
		jbtnLogOut.setForeground(Color.BLACK);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
	}//Constructor

	public HCYErp getHcyE() {
		return hcyE;
	}

	public JButton getJbtnAttendDate() {
		return jbtnAttendDate;
	}

	public JComboBox<Integer> getJcbYear() {
		return jcbYear;
	}

	public JComboBox<Integer> getJcbMonth() {
		return jcbMonth;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public JTable getJtMonthlyAttend() {
		return jtMonthlyAttend;
	}

	public DefaultTableModel getDtmMonthlyAttend() {
		return dtmMonthlyAttend;
	}
	
}//class
