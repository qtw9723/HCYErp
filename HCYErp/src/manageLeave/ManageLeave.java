package manageLeave;

import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
		dtmLeave.addColumn("사원번호");
		dtmLeave.addColumn("사원이름");
		dtmLeave.addColumn("휴가시작일");
		dtmLeave.addColumn("휴가끝일");
		dtmLeave.addColumn("휴가신청일");
		doaVOList = ManageLeaveDAO.getInstance().selectDayOffApply();
		addApplyList();
		jtLeaveProposal = new JTable(dtmLeave);
		jspLeave = new JScrollPane(jtLeaveProposal);
		jspLeave.setBounds(100,50,800,500);
		jspLeave.setBorder(new TitledBorder("휴가 신청 목록"));
		jtLeaveProposal.addMouseListener(event);
		add(jspLeave);
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
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
