package manageEmp;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import VO.EmpVO;

public class ManageEmpDialog extends JDialog{

	private JLabel jlblEname;
	private JLabel jlblLevel;
	private JLabel jlblTel;
	private JLabel jlblEmail;
	private JLabel jlblDept;
	private JLabel jlblTeam;
	private JLabel jlblJob;
	private JLabel jlblLoc;
	private JLabel jlblSal;
	
	private JTextField jtfEname;
	private JTextField jtfLevel;
	private JTextField jtfTel;
	private JTextField jtfEmail;
	private JTextField jtfDept;
	private JTextField jtfTeam;
	private JTextField jtfJob;
	private JTextField jtfLoc;
	private JTextField jtfSal;
	
	private JButton jbtnModify;
	private JButton jbtnOK;
	private JButton jbtnCancel; 
	private ManageEmp me;
	private EmpVO eVO;
	public ManageEmpDialog() {
		
	}
	
	public ManageEmpDialog(ManageEmp me,EmpVO eVO) {
		this.me=me;
		this.eVO=eVO;
		setTitle("사원 정보");

		getContentPane().setBackground(new Color(255,245,245));

		//JLabel
		jlblEname=new JLabel("이름");
		jlblLevel=new JLabel("직급");
		jlblTel=new JLabel("전화");
		jlblEmail=new JLabel("이메일");
		jlblDept=new JLabel("부서");
		jlblTeam=new JLabel("팀");
		jlblJob=new JLabel("직무");
		jlblLoc=new JLabel("근무지");
		jlblSal=new JLabel("연봉");
		
		//JLabel font설정
		Font jlblFont = new Font("맑은 고딕", Font.BOLD, 14);
		jlblEname.setFont(jlblFont);
		jlblLevel.setFont(jlblFont);
		jlblTel.setFont(jlblFont);
		jlblEmail.setFont(jlblFont);
		jlblDept.setFont(jlblFont);
		jlblTeam.setFont(jlblFont);
		jlblJob.setFont(jlblFont);
		jlblLoc.setFont(jlblFont);
		jlblSal.setFont(jlblFont);
		
		//JTextField
		jtfEname=new JTextField(eVO.getEname());
		jtfLevel=new JTextField(eVO.getLevel());
		jtfTel=new JTextField(eVO.getTel());
		jtfEmail=new JTextField(eVO.getEmail());
		jtfDept=new JTextField(eVO.getDept());
		jtfTeam=new JTextField(eVO.getTeam());
		jtfJob=new JTextField(eVO.getJob());
		jtfLoc=new JTextField(eVO.getDeptLoc());
		jtfSal=new JTextField(String.valueOf(eVO.getSal()));
		
		//JTextField font설정
		Font jtfFont = new Font("맑은 고딕", Font.PLAIN, 14);

		jtfEname.setFont(jtfFont);
		jtfLevel.setFont(jtfFont);
		jtfTel.setFont(jtfFont);
		jtfEmail.setFont(jtfFont);
		jtfDept.setFont(jtfFont);
		jtfTeam.setFont(jtfFont);
		jtfJob.setFont(jtfFont);
		jtfLoc.setFont(jtfFont);
		jtfSal.setFont(jtfFont);
		
		jbtnModify=new JButton("사원정보 수정");
		jbtnOK=new JButton("수정확인");
		jbtnCancel=new JButton("취소");
		
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 13);
		jbtnModify.setBackground(new Color(0x6D47B0));
		jbtnModify.setFont(jbtnFont);
		jbtnModify.setForeground(Color.white);
		jbtnOK.setBackground(new Color(0x6D47B0));
		jbtnOK.setFont(jbtnFont);
		jbtnOK.setForeground(Color.white);
		jbtnCancel.setBackground(new Color(0x919191));
		jbtnCancel.setForeground(Color.white);
		jbtnCancel.setFont(jbtnFont);
		
		//텍스트 필드 디자인
		Border focusField=BorderFactory.createLineBorder(new Color(255,72,72),2);// Line border
		Border unfocusField=BorderFactory.createLineBorder(Color.LIGHT_GRAY);// Line border
		
		
		jtfEname.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfEname.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfEname.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfLevel.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfLevel.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfLevel.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfTel.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfTel.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfTel.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfEmail.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfEmail.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfEmail.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfDept.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfDept.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfDept.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfTeam.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfTeam.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfTeam.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfJob.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfJob.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfJob.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfLoc.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfLoc.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfLoc.setBorder(focusField);
			}
		});//addFocusListener
		
		jtfSal.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfSal.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfSal.setBorder(focusField);
			}
		});//addFocusListener
		
		setLayout(null);
		
		add(jlblEname);
		add(jlblLevel);
		add(jlblTel);
		add(jlblEmail);
		add(jlblDept);
		add(jlblTeam);
		add(jlblJob);
		add(jlblLoc);
		add(jlblSal);
		
		jlblEname.setBounds(35,50,100,30);
		jlblLevel.setBounds(35,100,100,30);
		jlblTel.setBounds(35,150,100,30);
		jlblEmail.setBounds(35,200,100,30);
		jlblDept.setBounds(295,50,100,30);
		jlblTeam.setBounds(295,100,100,30);
		jlblJob.setBounds(295,150,100,30);
		jlblLoc.setBounds(295,200,100,30);
		jlblSal.setBounds(295,250,100,30);
		
		add(jtfEname);
		add(jtfLevel);
		add(jtfTel);
		add(jtfEmail);
		add(jtfDept);
		add(jtfTeam);
		add(jtfJob);
		add(jtfLoc);
		add(jtfSal);
		
		jtfEname.setBounds(90,45,170,40);
		jtfLevel.setBounds(90,95,170,40);
		jtfTel.setBounds(90,145,170,40);
		jtfEmail.setBounds(90,195,170,40);
		jtfDept.setBounds(355,45,130,40);
		jtfTeam.setBounds(355,95,130,40);
		jtfJob.setBounds(355,145,130,40);
		jtfLoc.setBounds(355,195,130,40);
		jtfSal.setBounds(355,245,130,40); 
		
		add(jbtnModify);
		add(jbtnOK);
		add(jbtnCancel);
		
		jbtnModify.setBounds(180, 320, 150, 50);
			
		jbtnOK.setVisible(false);
		jbtnCancel.setVisible(false);
		
		ManageEmpDialogEvt event=new ManageEmpDialogEvt(this);
		jbtnModify.addActionListener(event);
		jbtnOK.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		setBounds(me.getX()+700,me.getY()+250,540,450);
		setVisible(true);
		setResizable(false);
	}//constructor
	

	public JLabel getJlblEname() {
		return jlblEname;
	}

	public JLabel getJlblLevel() {
		return jlblLevel;
	}

	public JLabel getJlblTel() {
		return jlblTel;
	}

	public JLabel getJlblEmail() {
		return jlblEmail;
	}

	public JLabel getJlblDept() {
		return jlblDept;
	}

	public JLabel getJlblTeam() {
		return jlblTeam;
	}

	public JLabel getJlblJob() {
		return jlblJob;
	}

	public JLabel getJlblLoc() {
		return jlblLoc;
	}

	public JLabel getJlblSal() {
		return jlblSal;
	}


	public JTextField getJtfEname() {
		return jtfEname;
	}

	public JTextField getJtfLevel() {
		return jtfLevel;
	}

	public JTextField getJtfTel() {
		return jtfTel;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfDept() {
		return jtfDept;
	}

	public JTextField getJtfTeam() {
		return jtfTeam;
	}

	public JTextField getJtfJob() {
		return jtfJob;
	}

	public JTextField getJtfLoc() {
		return jtfLoc;
	}

	public JTextField getJtfSal() {
		return jtfSal;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public JButton getJbtnOK() {
		return jbtnOK;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}


	public ManageEmp getMe() {
		return me;
	}

	public EmpVO geteVO() {
		return eVO;
	}

	
	
}//class