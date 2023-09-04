package manageEmp;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

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
	
	public ManageEmp() {
		ManageEmpEvt event = new ManageEmpEvt(this);
		
		setLayout(null);
		
		//부서 리스트
		DefaultListModel<String> dlmDept = new DefaultListModel<String>();
		jlDepartment = new JList<String>(dlmDept);
		for(int i =1;i<100;i++) {
			dlmDept.addElement("부서"+i);
		}//이거 다오 들어오면 삭제
		jspDepartment = new JScrollPane(jlDepartment);
		jspDepartment.setBounds(150,100,230,400);
		jspDepartment.setBorder(new TitledBorder("부서"));
		add(jspDepartment);
		
		//팀 리스트
		
		
		
		
		
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

}//class
