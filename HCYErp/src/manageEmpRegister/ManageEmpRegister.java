package manageEmpRegister;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import VO.EmpVO;
import login.HCYErp;

@SuppressWarnings("serial")
public class ManageEmpRegister extends JPanel{
	private JButton jbtnEmpRegister;
	private JButton jbtnResign;
	private JButton jbtnAbsence;
	private JTable jtRegiAbInfo;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private JScrollPane jspResignAb;
	private HCYErp hcyE;
	
	public ManageEmpRegister(HCYErp hcyE) {
		this.hcyE=hcyE;
		setLayout(null);
		ManageEmpRegisterEvt event = new ManageEmpRegisterEvt(this);
		
		//입퇴사자 테이블
		DefaultTableModel dtmRefiAbInfo  = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // 모든 셀을 수정 불가능하게 설정
		        return false;
		    }//isCellEditable
		};
		
		jtRegiAbInfo = new JTable(dtmRefiAbInfo);
		jspResignAb = new JScrollPane(jtRegiAbInfo);
		
		//다오에서 추가
		List<EmpVO> empVOList = null;
		try {
			empVOList = ManageEmpRegisterDAO.getInstance().selectEmp();
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		for(int i = 0; i< empVOList.size();i++) {
			dtmRefiAbInfo.addRow(new Object[] {empVOList.get(i).getEmpNo(),empVOList.get(i).getEname(),empVOList.get(i).getJob(),empVOList.get(i).getLevel(),empVOList.get(i).getHiredate(),empVOList.get(i).getSal()});
		}//for
		dtmRefiAbInfo.addColumn("사원번호");
		dtmRefiAbInfo.addColumn("사원이름");
		dtmRefiAbInfo.addColumn("부서");
		dtmRefiAbInfo.addColumn("직급");
		dtmRefiAbInfo.addColumn("입사일");
		dtmRefiAbInfo.addColumn("연봉");
		
		//이동,크기 조절 불가
		jtRegiAbInfo.getTableHeader().setReorderingAllowed(false);
		jtRegiAbInfo.getTableHeader().setResizingAllowed(false);
		
		jspResignAb.setBounds(100,50,800,500);
		jspResignAb.setBorder(new TitledBorder("입사자/퇴사 대상자 목록"));
		add(jspResignAb);
		
		//셀 간격 고정
		jtRegiAbInfo.setRowHeight(25);
			
		//JTable header font설정
		Font headerFont=new Font("맑은 고딕",Font.BOLD,15);
		JTableHeader tableHeader=jtRegiAbInfo.getTableHeader();
		tableHeader.setFont(headerFont);
		tableHeader.setBackground(new Color(213,232,212));
		
		//JTable cell 간격
		TableColumnModel columnModel =jtRegiAbInfo.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(190);
		columnModel.getColumn(5).setPreferredWidth(150);
		
		//cell font설정
		Font jtFont=new Font("맑은 고딕",Font.PLAIN,15);
		jtRegiAbInfo.setFont(jtFont);
		
		//JTable cell 가운데 정렬
		DefaultTableCellRenderer cellCenter=new DefaultTableCellRenderer();
		cellCenter.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<jtRegiAbInfo.getColumnCount();i++) {
			jtRegiAbInfo.getColumnModel().getColumn(i).setCellRenderer(cellCenter);
		}//for
		
		//타이틀바 디자인
		TitledBorder titleBorder=BorderFactory.createTitledBorder("입사자/퇴사 대상자 목록");
		Font titleFont=new Font("맑은 고딕",Font.BOLD,18);
		titleBorder.setTitleFont(titleFont);
		titleBorder.setTitleJustification(titleBorder.LEFT);
		
		jspResignAb.setBorder(titleBorder);
		
		//버튼 폰트
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		
		//입사자 추가 버튼
		jbtnEmpRegister = new JButton("입사자 추가");
		jbtnEmpRegister.setBounds(930,130,130,55);
		jbtnEmpRegister.setBackground(new Color(0x461C90));
		jbtnEmpRegister.setFont(jbtnFont);
		jbtnEmpRegister.setForeground(Color.white);
		jbtnEmpRegister.addActionListener(event);
		add(jbtnEmpRegister);
		
		//퇴사 처리 버튼
		jbtnResign = new JButton("퇴사 처리");
		jbtnResign.setBounds(930,230,130,55);
		jbtnResign.setBackground(new Color(0x461C90));
		jbtnResign.setFont(jbtnFont);
		jbtnResign.setForeground(Color.white);
		jbtnResign.addActionListener(event);
		add(jbtnResign);
		
		//휴직 처리 버튼
		jbtnAbsence = new JButton("휴직 처리");
		jbtnAbsence.addActionListener(event);
		jbtnAbsence.setBounds(930,330,130,55);
		jbtnAbsence.setFont(jbtnFont);
		jbtnAbsence.setForeground(Color.white);
		jbtnAbsence.setBackground(new Color(0x461C90));
		add(jbtnAbsence);
		
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
		add(jlblBG);hcyE.getList().add(this);
		
		//jTable 배경색
		jspResignAb.getViewport().setBackground(new Color(0xECEBFF));
	}//constructor

	public JButton getJbtnEmpRegister() {
		return jbtnEmpRegister;
	}

	public JButton getJbtnResign() {
		return jbtnResign;
	}

	public JButton getJbtnAbsence() {
		return jbtnAbsence;
	}

	public JTable getJtRegiAbInfo() {
		return jtRegiAbInfo;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public JScrollPane getJspResignAb() {
		return jspResignAb;
	}

	public HCYErp getHcyE() {
		return hcyE;
	}
	
}//class
