package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ResetPassDialog extends JDialog {
	private JTextField jtfEmpNo;
	private JTextField jtfEname;
	private JTextField jtfSsn;
	private JButton jbtnResetPass;
	private JButton jbtnCancel;
	private JLabel jlblLogoGrey;
	private JLabel jlblEmpNo;
	private JLabel jlblEname;
	private JLabel jlblSsn;
	private JLabel jlblBg;
	
	//값 입력
	private int inputEmpno;
	private String inputEname;
	private String inputSsn;

	public ResetPassDialog() {

		Border focusField = BorderFactory.createLineBorder(new Color(0xEE82EE)); // Line border
		Border unfocusField = BorderFactory.createLineBorder(Color.LIGHT_GRAY); // Line border

		Font font = new Font("맑은 고딕", Font.BOLD, 15);

		// 텍스트필드 선언
		jtfEmpNo = new JTextField();
		jtfEname = new JTextField();
		jtfSsn = new JTextField();
		
		jtfEmpNo.setBorder(unfocusField);
		jtfEname.setBorder(unfocusField);
		jtfSsn.setBorder(unfocusField);
		
		// 텍스트 필드 디자인
		jtfEmpNo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				jtfEmpNo.setBorder(unfocusField);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				jtfEmpNo.setBorder(focusField);
			}
		});

		jtfEname.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				jtfEname.setBorder(unfocusField);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				jtfEname.setBorder(focusField);
			}
		});

		jtfSsn.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				jtfSsn.setBorder(unfocusField);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				jtfSsn.setBorder(focusField);
			}
		});
		// 버튼선언
		jbtnResetPass = new JButton("비밀번호 초기화");
		jbtnCancel = new JButton("취소");

		// 라벨선언
		jlblEmpNo = new JLabel("사원번호 : ");
		jlblEname = new JLabel("이름 : ");
		jlblSsn = new JLabel("주민등록번호 : ");
		jlblBg =new JLabel();

		// 라벨 폰트 설정
		jlblEmpNo.setFont(font);
		jlblEname.setFont(font);
		jlblSsn.setFont(font);

		// 버튼 폰트 설정
		jbtnResetPass.setFont(font);
		jbtnCancel.setFont(font);

		// 로고이미지 삽입
		jlblLogoGrey = new JLabel(
				new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/grey_logo_3_3cm-removebg.png"));
		jlblBg = new JLabel(
				new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));

		setLayout(null);
		
		ResetPassDialogEvt fpde=new ResetPassDialogEvt(this);
		jbtnResetPass.addActionListener(fpde);
		jbtnCancel.addActionListener(fpde);
		
		jtfEmpNo.setBounds(160, 50, 150, 30);
		jtfEname.setBounds(160, 120, 150, 30);
		jtfSsn.setBounds(160, 190, 150, 30);

		jbtnResetPass.setBounds(80, 250, 150, 35);
		jbtnCancel.setBounds(300, 250, 120, 35);

		jlblEmpNo.setBounds(40, 50, 80, 30);
		jlblEname.setBounds(40, 120, 80, 30);
		jlblSsn.setBounds(40, 190, 120, 30);
		jlblLogoGrey.setBounds(450, 10, 300, 300);
		jlblBg.setBounds(0,0,800,800);

		add(jtfEmpNo);
		add(jtfEname);
		add(jtfSsn);

		add(jbtnResetPass);
		add(jbtnCancel);

		add(jlblEmpNo);
		add(jlblEname);
		add(jlblSsn);
		add(jlblLogoGrey);
		add(jlblBg);
		
		//일단 여기서 내가 값 넣어주고 내가 입력한 값이랑 같은지 비교하려고 해놓는거
		setInputEmpno(1);
		setInputEname("홍찬영");
		setInputSsn("980101-1234567");
		
		setBounds(100, 100, 800, 400);
		setVisible(true);
	}// constructor

	
	
	public int getInputEmpno() {
		return inputEmpno;
	}



	public String getInputEname() {
		return inputEname;
	}



	public String getInputSsn() {
		return inputSsn;
	}



	public void setInputEmpno(int inputEmpno) {
		this.inputEmpno = inputEmpno;
	}



	public void setInputEname(String inputEname) {
		this.inputEname = inputEname;
	}



	public void setInputSsn(String inputSsn) {
		this.inputSsn = inputSsn;
	}



	public void setJtfEmpNo(JTextField jtfEmpNo) {
		this.jtfEmpNo = jtfEmpNo;
	}



	public void setJtfEname(JTextField jtfEname) {
		this.jtfEname = jtfEname;
	}



	public void setJtfSsn(JTextField jtfSsn) {
		this.jtfSsn = jtfSsn;
	}



	



	public void setJbtnResetPass(JButton jbtnResetPass) {
		this.jbtnResetPass = jbtnResetPass;
	}



	public void setJbtnCancel(JButton jbtnCancel) {
		this.jbtnCancel = jbtnCancel;
	}



	public void setJlblLogoGrey(JLabel jlblLogoGrey) {
		this.jlblLogoGrey = jlblLogoGrey;
	}



	public void setJlblEmpNo(JLabel jlblEmpNo) {
		this.jlblEmpNo = jlblEmpNo;
	}



	public void setJlblEname(JLabel jlblEname) {
		this.jlblEname = jlblEname;
	}



	public void setJlblSsn(JLabel jlblSsn) {
		this.jlblSsn = jlblSsn;
	}



	public void setJlblBg(JLabel jlblBg) {
		this.jlblBg = jlblBg;
	}



	public JTextField getJtfEmpNo() {
		return jtfEmpNo;
	}



	public JTextField getJtfEname() {
		return jtfEname;
	}



	public JTextField getJtfSsn() {
		return jtfSsn;
	}



	public JButton getJbtnResetPass() {
		return jbtnResetPass;
	}



	public JButton getJbtnCancel() {
		return jbtnCancel;
	}



	public JLabel getJlblLogoGrey() {
		return jlblLogoGrey;
	}



	public JLabel getJlblEmpNo() {
		return jlblEmpNo;
	}



	public JLabel getJlblEname() {
		return jlblEname;
	}



	public JLabel getJlblSsn() {
		return jlblSsn;
	}



	public JLabel getJlblBg() {
		return jlblBg;
	}



	public static void main(String[] arg) {
		new ResetPassDialog();
	}



	
}// class
