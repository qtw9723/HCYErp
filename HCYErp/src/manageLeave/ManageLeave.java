package manageLeave;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import VO.DayOffApplyVO;
import login.HCYErp;

@SuppressWarnings("serial")
public class ManageLeave extends JPanel{

	private HCYErp hcyE;
	private JTable jtLeaveProposal;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JDesktopPane desktopPane;
	private JScrollPane jspLeave;
	private List<DayOffApplyVO> doaVOList;
	private DefaultTableModel dtmLeave;
	
	public ManageLeave(HCYErp hcyE) throws SQLException {
		this.hcyE = hcyE;
		setLayout(null);
		ManageLeaveEvt event = new ManageLeaveEvt(this);
		
		dtmLeave = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // 모든 셀을 수정 불가능하게 설정
		        return false;
		    }//isCellEditable
		};
		
		jtLeaveProposal = new JTable(dtmLeave);
		
		//셀 간격
		jtLeaveProposal.setRowHeight(25);
		
		//이동,크기 조절 불가
		jtLeaveProposal.getTableHeader().setReorderingAllowed(false);
		jtLeaveProposal.getTableHeader().setResizingAllowed(false);
		dtmLeave.addColumn("사원번호");
		dtmLeave.addColumn("사원이름");
		dtmLeave.addColumn("휴가시작일");
		dtmLeave.addColumn("휴가종료일");
		dtmLeave.addColumn("휴가신청일");
		
		
		//JTable header font설정
		Font headerFont=new Font("맑은 고딕",Font.BOLD,15);
		JTableHeader tableHeader=jtLeaveProposal.getTableHeader();
		tableHeader.setFont(headerFont);
		tableHeader.setBackground(new Color(213,232,212));
		
		//JTable cell 간격
		TableColumnModel columnModel =jtLeaveProposal.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		
		//cell font설정
		Font jtFont=new Font("맑은 고딕",Font.PLAIN,15);
		jtLeaveProposal.setFont(jtFont);
		
		//JTable cell 가운데 정렬
		DefaultTableCellRenderer cellCenter=new DefaultTableCellRenderer();
		cellCenter.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<jtLeaveProposal.getColumnCount();i++) {
			jtLeaveProposal.getColumnModel().getColumn(i).setCellRenderer(cellCenter);
		}//for

		doaVOList = ManageLeaveDAO.getInstance().selectDayOffApply();
		addApplyList();
		jspLeave = new JScrollPane(jtLeaveProposal);
		jspLeave.setBounds(100,50,800,500);
		
		//타이틀바 디자인
		TitledBorder titleBorder=BorderFactory.createTitledBorder("휴가 신청 목록");
		Font titleFont=new Font("맑은 고딕",Font.BOLD,18);
		titleBorder.setTitleFont(titleFont);
		titleBorder.setTitleJustification(titleBorder.LEFT);
		
		jspLeave.setBorder(titleBorder);
		jtLeaveProposal.addMouseListener(event);
		add(jspLeave);
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		Font LogOutFont = new Font("맑은 고딕", Font.BOLD, 13);
		jbtnLogOut.setFont(LogOutFont);
		jbtnLogOut.setForeground(Color.BLACK);
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
		hcyE.getList().add(this);
		
		jtLeaveProposal.setBackground(Color.white);
		
		//JTable 밑에 빈 공간들 색
		jspLeave.getViewport().setBackground(new Color(0xECEBFF));
		
	}//Constructor

	public void addApplyList() {
		//리스트 초기화
		for(int i = dtmLeave.getRowCount()-1;i>=0;i--) {
			dtmLeave.removeRow(i);
		}//for
		
		//리스트 추가
		for(DayOffApplyVO doaVO:doaVOList) {
			dtmLeave.addRow(new Object[] {doaVO.getEmpNo(),doaVO.getEname(),doaVO.getStartDate(),doaVO.getEndDate(),doaVO.getSubmitDate()});
		}//for
	}//addApplyList

	public HCYErp getHcyE() {
		return hcyE;
	}

	public JTable getJtLeaveProposal() {
		return jtLeaveProposal;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public List<DayOffApplyVO> getDoaVOList() {
		return doaVOList;
	}
	
	 
}//class
