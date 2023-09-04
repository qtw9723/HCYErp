package attendance;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChangePassDialog extends JDialog{

	private JPasswordField jpfCurrentPass;
	private JPasswordField jpfNewPass;
	private JPasswordField jpfCheckNewPass;
	private JTextField jtfCurrentPass;
	private JTextField jtfNewPass;
	private JTextField jtfCheckNewPass;
	private JLabel jlblNewHide;
	private JLabel jlblNewView;
	private JLabel jlblCheckNewHide;
	private JLabel jlblCheckNewView;
	private JLabel jlblCurrentPass;
	private JLabel jlblNewPass;
	private JLabel jlblCheckNewPass;
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
		jlblNewHide=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리.png"));
		jlblNewView=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리1.png"));
		jlblCheckNewHide=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리.png"));
		jlblCheckNewView=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리1.png"));
		jlblCurrentPass=new JLabel("현재비밀번호 : ");
		jlblNewPass=new JLabel("새 비밀번호 : ");
		jlblCheckNewPass=new JLabel("비밀번호 확인 : ");
		//숨기기 버튼 숨겨놓기
		jlblNewHide.setVisible(false);
		jlblCheckNewHide.setVisible(false);
		
		//텍스트필드 숨겨놓기
		jtfNewPass.setVisible(false);
		jtfCheckNewPass.setVisible(false);
		
		setLayout(null);
		ChangePassDialogEvt cpde=new ChangePassDialogEvt(this);
		jbtnOK.addActionListener(cpde);
		jbtnCancel.addActionListener(cpde);
		
		jlblNewHide.addMouseListener(cpde);
		jlblNewView.addMouseListener(cpde);
		jlblCheckNewHide.addMouseListener(cpde);
		jlblCheckNewView.addMouseListener(cpde);
		//컴포넌트 크기설정
		jpfCurrentPass.setBounds(100,100,150,30);
		jpfNewPass.setBounds(100,200,150,30);
		jpfCheckNewPass.setBounds(100,300,150,30);
		
		jtfNewPass.setBounds(100,200,150,30);
		jtfCheckNewPass.setBounds(100,200,150,30);
		
		jlblNewHide.setBounds(250,200,30,30);
		jlblNewView.setBounds(250,200,30,30);
		jlblCheckNewHide.setBounds(250,300,30,30);
		jlblCheckNewView.setBounds(250,300,30,30);
		jlblCurrentPass.setBounds(0,100,100,30);
		jlblNewPass.setBounds(0,200,100,30);
		jlblCheckNewPass.setBounds(0,300,100,30);
		
		jbtnOK.setBounds(100,400,100,30);
		jbtnCancel.setBounds(300,400,100,30);
		//컴포넌트 추가
		add(jpfCurrentPass);
		add(jpfNewPass);
		add(jpfCheckNewPass);
		
		add(jtfNewPass);
		add(jtfCheckNewPass);
		
		add(jlblNewHide);
		add(jlblNewView);
		add(jlblCheckNewHide);
		add(jlblCheckNewView);
		add(jlblCurrentPass);
		add(jlblNewPass);
		add(jlblCheckNewPass);
		
		add(jbtnOK);
		add(jbtnCancel);
		
		setSize(500,500);
		setVisible(true);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});//windowListener
		
	}//constructor
	
	public static void main(String[] arg) {
		new ChangePassDialog();
	}//main
	
}//class
