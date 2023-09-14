package manageEmp;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
	
	public ManageEmpDialog(ManageEmp me) {
		this.me=me;
		jlblEname=new JLabel("이름");
		jlblLevel=new JLabel("직급");
		jlblTel=new JLabel("전화");
		jlblEmail=new JLabel("이메일");
		jlblDept=new JLabel("부서");
		jlblTeam=new JLabel("팀");
		jlblJob=new JLabel("직무");
		jlblLoc=new JLabel("근무지");
		jlblSal=new JLabel("연봉");
		
		jtfEname=new JTextField();
		jtfLevel=new JTextField();
		jtfTel=new JTextField();
		jtfEmail=new JTextField();
		jtfDept=new JTextField();
		jtfTeam=new JTextField();
		jtfJob=new JTextField();
		jtfLoc=new JTextField();
		jtfSal=new JTextField();
		
		jbtnModify=new JButton("사원정보 수정");
		jbtnOK=new JButton("수정확인");
		jbtnCancel=new JButton("취소");
		
		jbtnModify.setBackground(new Color(0x8244AD));
		jbtnOK.setBackground(new Color(0x8244AD));
		jbtnCancel.setBackground(new Color(0xE0E0E0));
		
		//텍스트 필드 디자인
		Border focusField=BorderFactory.createLineBorder(new Color(0xEE82EE),2);// Line border
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
		
		jlblEname.setBounds(45,60,100,30);
		jlblLevel.setBounds(45,110,100,30);
		jlblTel.setBounds(45,160,100,30);
		jlblEmail.setBounds(45,210,100,30);
		jlblDept.setBounds(300,10,100,30);
		jlblTeam.setBounds(300,60,100,30);
		jlblJob.setBounds(300,110,100,30);
		jlblLoc.setBounds(300,160,100,30);
		jlblSal.setBounds(300,210,100,30);
		
		add(jtfEname);
		add(jtfLevel);
		add(jtfTel);
		add(jtfEmail);
		add(jtfDept);
		add(jtfTeam);
		add(jtfJob);
		add(jtfLoc);
		add(jtfSal);
		
		jtfEname.setBounds(120,60,110,30);
		jtfLevel.setBounds(120,110,110,30);
		jtfTel.setBounds(120,160,110,30);
		jtfEmail.setBounds(120,210,110,30);
		jtfDept.setBounds(370,10,110,30);
		jtfTeam.setBounds(370,60,110,30);
		jtfJob.setBounds(370,110,110,30);
		jtfLoc.setBounds(370,160,110,30);
		jtfSal.setBounds(370,210,110,30); 
		
		add(jbtnModify);
		add(jbtnOK);
		add(jbtnCancel);
		
		jbtnModify.setBounds(180, 330, 150, 50);
			
		jbtnOK.setVisible(false);
		jbtnCancel.setVisible(false);
		
		ManageEmpDialogEvt mede=new ManageEmpDialogEvt(this);
		jbtnModify.addActionListener(mede);
		jbtnOK.addActionListener(mede);
		jbtnCancel.addActionListener(mede);
		
		setBounds(me.getX()+300,me.getY()+100,680,450);
		setVisible(true);
		
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

	
	
}//class
