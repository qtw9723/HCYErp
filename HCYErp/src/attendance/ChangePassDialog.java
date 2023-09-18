
package attendance;


import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ChangePassDialog extends JDialog{

	private JPasswordField jpfCurrentPass;
	private JPasswordField jpfCheckNewPass;
	private JTextField jtfCheckNewPass;
	private JPasswordField jpfNewPass;
	private JTextField jtfCurrentPass;
	private JLabel jlblCheckNewHide;
	private JLabel jlblCheckNewView;
	private JLabel jlblCheckNewPass;
	private JLabel jlblCurrentPass;
	private JTextField jtfNewPass;
	private JButton jbtnCancel;
	private JLabel jlblNewHide;
	private JLabel jlblNewView;
	private JLabel jlblNewPass;
	private JButton jbtnOK;
	private Attendance ad;
	
	public ChangePassDialog(Attendance ad) {
		this.ad=ad;
		//패스워드필드 선언
		jpfCheckNewPass=new JPasswordField();
		jpfCurrentPass=new JPasswordField();
		jpfNewPass=new JPasswordField();
		//텍스트필드 선언
		jtfCheckNewPass=new JTextField();
		jtfCurrentPass=new JTextField();
		jtfNewPass=new JTextField();
		//버튼선언
		jbtnCancel=new JButton("취소");
		jbtnOK=new JButton("변경확인");
		//라벨선언
		jlblCheckNewView=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리1.png"));
		jlblCheckNewHide=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리.png"));
		jlblNewView=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리1.png"));
		jlblNewHide=new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/눈까리.png"));
		jlblCheckNewPass=new JLabel("비밀번호 확인 : ");
		jlblCurrentPass=new JLabel("현재비밀번호 : ");
		jlblNewPass=new JLabel("새 비밀번호 : ");
		//숨기기 버튼 숨겨놓기
		jlblCheckNewHide.setVisible(false);
		jlblNewHide.setVisible(false);
		//텍스트필드 숨겨놓기
		jtfCheckNewPass.setVisible(false);
		jtfNewPass.setVisible(false);
		//레이아웃 설정
		setLayout(null);
		//이벤트 등록
		ChangePassDialogEvt event=new ChangePassDialogEvt(this);
		jlblCheckNewHide.addMouseListener(event);
		jlblCheckNewView.addMouseListener(event);
		jbtnCancel.addActionListener(event);
		jlblNewHide.addMouseListener(event);
		jlblNewView.addMouseListener(event);
		jbtnOK.addActionListener(event);
		
		//텍스트 필드 디자인
		Border focusField=BorderFactory.createLineBorder(new Color(0xBC5BC2),2);// Line border
		Border unfocusField=BorderFactory.createLineBorder(Color.LIGHT_GRAY);// Line border
		
		jpfCurrentPass.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				jpfCurrentPass.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jpfCurrentPass.setBorder(focusField);
			}
		});
		
		jpfNewPass.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				jpfNewPass.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jpfNewPass.setBorder(focusField);
			}
		});
		
		jpfCheckNewPass.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				jpfCheckNewPass.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jpfCheckNewPass.setBorder(focusField);
			}
		});
		
		//컴포넌트 크기설정
		jpfCurrentPass.setBounds(100,100,150,30);
		jpfNewPass.setBounds(100,200,150,30);
		jpfCheckNewPass.setBounds(100,300,150,30);
		
		jtfNewPass.setBounds(100,200,150,30);
		jtfCheckNewPass.setBounds(100,300,150,30);
		
		jlblNewHide.setBounds(250,200,30,30);
		jlblNewView.setBounds(250,200,30,30);
		jlblCheckNewHide.setBounds(250,300,30,30);
		jlblCheckNewView.setBounds(250,300,30,30);
		jlblCurrentPass.setBounds(0,100,100,30);
		jlblNewPass.setBounds(0,200,100,30);
		jlblCheckNewPass.setBounds(0,300,100,30);
		
		jbtnOK.setBounds(100,400,100,30);
		jbtnOK.setBackground(new Color(0x5E5E5E));
		jbtnCancel.setBounds(300,400,100,30);
		jbtnCancel.setBackground(new Color(0xE0E0E0));
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
		
	}//constructor

	public JPasswordField getJpfCurrentPass() {
		return jpfCurrentPass;
	}

	public JPasswordField getJpfNewPass() {
		return jpfNewPass;
	}

	public JPasswordField getJpfCheckNewPass() {
		return jpfCheckNewPass;
	}

	public JTextField getJtfCurrentPass() {
		return jtfCurrentPass;
	}

	public JTextField getJtfNewPass() {
		return jtfNewPass;
	}

	public JTextField getJtfCheckNewPass() {
		return jtfCheckNewPass;
	}

	public JLabel getJlblNewHide() {
		return jlblNewHide;
	}

	public JLabel getJlblNewView() {
		return jlblNewView;
	}

	public JLabel getJlblCheckNewHide() {
		return jlblCheckNewHide;
	}

	public JLabel getJlblCheckNewView() {
		return jlblCheckNewView;
	}

	public JLabel getJlblCurrentPass() {
		return jlblCurrentPass;
	}

	public JLabel getJlblNewPass() {
		return jlblNewPass;
	}

	public JLabel getJlblCheckNewPass() {
		return jlblCheckNewPass;
	}

	public JButton getJbtnOK() {
		return jbtnOK;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public Attendance getAd() {
		return ad;
	}

}//class







