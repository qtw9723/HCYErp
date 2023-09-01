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
public class FindPassDialog extends JDialog {
	private JTextField jtfEmpNo;
	private JTextField jtfEname;
	private JTextField jtfSsn;
	private JButton jbtnFindPass;
	private JButton jbtnCancel;
	private JLabel jlblLogoGrey;
	private JLabel jlblEmpNo;
	private JLabel jlblEname;
	private JLabel jlblSsn;
	private JLabel jlblBg;

	public FindPassDialog() {

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
		jbtnFindPass = new JButton("비밀번호 찾기");
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
		jbtnFindPass.setFont(font);
		jbtnCancel.setFont(font);

		// 로고이미지 삽입
		jlblLogoGrey = new JLabel(
				new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/grey_logo_3_3cm-removebg.png"));
		jlblBg = new JLabel(
				new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));

		setLayout(null);
		
		FindPassDialogEvt fpde=new FindPassDialogEvt(this);
		jbtnFindPass.addActionListener(fpde);
		jbtnCancel.addActionListener(fpde);
		
		jtfEmpNo.setBounds(160, 50, 150, 30);
		jtfEname.setBounds(160, 120, 150, 30);
		jtfSsn.setBounds(160, 190, 150, 30);

		jbtnFindPass.setBounds(80, 250, 150, 35);
		jbtnCancel.setBounds(300, 250, 120, 35);

		jlblEmpNo.setBounds(40, 50, 80, 30);
		jlblEname.setBounds(40, 120, 80, 30);
		jlblSsn.setBounds(40, 190, 120, 30);
		jlblLogoGrey.setBounds(450, 10, 300, 300);
		jlblBg.setBounds(0,0,800,800);

		add(jtfEmpNo);
		add(jtfEname);
		add(jtfSsn);

		add(jbtnFindPass);
		add(jbtnCancel);

		add(jlblEmpNo);
		add(jlblEname);
		add(jlblSsn);
		add(jlblLogoGrey);
		add(jlblBg);
		setBounds(100, 100, 800, 400);
		setVisible(true);
	}// constructor

	public static void main(String[] arg) {
		new FindPassDialog();
	}
}// class
