package manageEmpRegister;

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
		setLayout(null);
		ManageEmpRegisterEvt event = new ManageEmpRegisterEvt(this);
		
		//입퇴사자 테이블
		DefaultTableModel dtmRefiAbInfo = new DefaultTableModel();
		dtmRefiAbInfo.addColumn("사원번호");
		dtmRefiAbInfo.addColumn("사원이름");
		dtmRefiAbInfo.addColumn("퇴사일");
		dtmRefiAbInfo.addColumn("승인일");
		dtmRefiAbInfo.addColumn("퇴사사유");
		//다오에서 추가
		List<Object[]> ojList = new ArrayList<Object[]>();
		for(int i =0;i<100;i++) {
			ojList.add(new Object[]{i,"이름","2023.09.05","2023.09.04","회장이 갈궈서"});
		}//사사사사사사삭제
		
		for(Object[] oj:ojList) {
		dtmRefiAbInfo.addRow(oj);
		}//for
		jtRegiAbInfo = new JTable(dtmRefiAbInfo);
		jspResignAb = new JScrollPane(jtRegiAbInfo);
		jspResignAb.setBounds(100,50,800,500);
		jspResignAb.setBorder(new TitledBorder("입사자/퇴사자 목록"));
		add(jspResignAb);
		
		//입사자 추가 버튼
		jbtnEmpRegister = new JButton("입사자 추가");
		jbtnEmpRegister.setBounds(930,100,130,55);
		add(jbtnEmpRegister);
		//퇴사 처리 버튼
		jbtnResign = new JButton("퇴사 처리");
		jbtnResign.setBounds(930,200,130,55);
		add(jbtnResign);
		//휴직 버튼
		jbtnAbsence = new JButton("휴직");
		jbtnAbsence.setBounds(930,300,130,55);
		add(jbtnAbsence);
		
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
