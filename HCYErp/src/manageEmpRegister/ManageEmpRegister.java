package manageEmpRegister;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
		};;
		dtmRefiAbInfo.addColumn("사원번호");
		dtmRefiAbInfo.addColumn("사원이름");
		dtmRefiAbInfo.addColumn("부서");
		dtmRefiAbInfo.addColumn("직급");
		dtmRefiAbInfo.addColumn("입사일");
		dtmRefiAbInfo.addColumn("연봉");
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
		jtRegiAbInfo = new JTable(dtmRefiAbInfo);
		jspResignAb = new JScrollPane(jtRegiAbInfo);
		jspResignAb.setBounds(100,50,800,500);
		jspResignAb.setBorder(new TitledBorder("입사자/퇴사 대상자 목록"));
		add(jspResignAb);
		
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		//입사자 추가 버튼
		jbtnEmpRegister = new JButton("입사자 추가");
		jbtnEmpRegister.setBounds(930,100,130,55);
		jbtnEmpRegister.setBackground(new Color(0x461C90));
		jbtnEmpRegister.setFont(jbtnFont);
		jbtnEmpRegister.setForeground(Color.white);
		jbtnEmpRegister.addActionListener(event);
		add(jbtnEmpRegister);
		
		//퇴사 처리 버튼
		jbtnResign = new JButton("퇴사 처리");
		jbtnResign.setBounds(930,200,130,55);
		jbtnResign.setBackground(new Color(0x461C90));
		jbtnResign.setFont(jbtnFont);
		jbtnResign.setForeground(Color.white);
		jbtnResign.addActionListener(event);
		add(jbtnResign);
		
		//휴직 버튼
		jbtnAbsence = new JButton("휴직 처리");
		jbtnAbsence.addActionListener(event);
		jbtnAbsence.setBounds(930,300,130,55);
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
