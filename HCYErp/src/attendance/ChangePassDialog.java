package attendance;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ChangePassDialog extends JDialog{

	private JPasswordField jpfCurrentPass;
	private JPasswordField jpfNewPass;
	private JPasswordField jpfCheckNewPass;
	private JTextField jtfCurrentPass;
	private JTextField jtfNewPass;
	private JTextField jtfCheckNewPass;
	private JLabel jlblCurrentView;
	private JLabel jlblNewView;
	private JLabel jlblCheckNewView;
	private JButton jbtnOK;
	private JButton jbtnCancel;
	
	public ChangePassDialog() {
		//패스워드필드 선언
		jpfCurrentPass=new JPasswordField();
		jpfNewPass=new JPasswordField();
		jpfCheckNewPass=new JPasswordField();
		//텍스트필드 선언
		jtfCurrentPass=new JPasswordField();
		jtfNewPass=new JPasswordField();
		jtfCheckNewPass=new JPasswordField();
		//버튼선언
		jbtnOK=new JButton("변경확인");
		jbtnCancel=new JButton("취소");
		//라벨선언
		jlblCurrentView=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리.png"));
		jlblNewView=new JLabel();
		jlblCheckNewView=new JLabel();
		
		jlblCurrentView.setBounds(100,100,100,100);
		
		add(jlblCurrentView);
		
		setSize(500,500);
		setVisible(true);
		
		
	}//constructor
	
	public static void main(String[] arg) {
		new ChangePassDialog();
	}//main
	
}//class
