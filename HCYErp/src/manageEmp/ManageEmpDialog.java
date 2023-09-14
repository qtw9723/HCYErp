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

	private JLabel jlblEmpNo;
	private JLabel jlblEname;
	private JLabel jlblLevel;
	private JLabel jlblJobTel;
	private JLabel jlblEmail;
	private JLabel jlblDept;
	private JLabel jlblTeam;
	private JLabel jlblJob;
	private JLabel jlblBirthday;
	private JLabel jlblLoc;
	private JLabel jlblSal;
	
	private JTextField jtfEmpNo;
	private JTextField jtfEname;
	private JTextField jtfLevel;
	private JTextField jtfJobTel;
	private JTextField jtfEmail;
	private JTextField jtfDept;
	private JTextField jtfTeam;
	private JTextField jtfJob;
	private JTextField jtfBirthday;
	private JTextField jtfLoc;
	private JTextField jtfSal;
	
	private JButton jbtnModify;
	private JButton jbtnOK;
	private JButton jbtnCancel;
	
	public ManageEmpDialog() {
		jlblEmpNo=new JLabel("번호");
		jlblEname=new JLabel("이름");
		jlblLevel=new JLabel("직급");
		jlblJobTel=new JLabel("전화");
		jlblEmail=new JLabel("이메일");
		jlblDept=new JLabel("부서");
		jlblTeam=new JLabel("팀");
		jlblJob=new JLabel("직무");
		jlblBirthday=new JLabel("생년월일");
		jlblLoc=new JLabel("근무지");
		jlblSal=new JLabel("연봉");
		
		jtfEmpNo=new JTextField();
		jtfEname=new JTextField();
		jtfLevel=new JTextField();
		jtfJobTel=new JTextField();
		jtfEmail=new JTextField();
		jtfDept=new JTextField();
		jtfTeam=new JTextField();
		jtfJob=new JTextField();
		jtfBirthday=new JTextField();
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
		
		//어떤 게 변경되고있는지 알 수 있게 focusListener
		jtfEmpNo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfEmpNo.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfEmpNo.setBorder(focusField);
			}
		});//addFocusListener
		
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
		
		jtfJobTel.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfJobTel.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfJobTel.setBorder(focusField);
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
		
		jtfBirthday.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				jtfBirthday.setBorder(unfocusField);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				jtfBirthday.setBorder(focusField);
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
		
		add(jlblEmpNo);
		add(jlblEname);
		add(jlblLevel);
		add(jlblJobTel);
		add(jlblEmail);
		add(jlblDept);
		add(jlblTeam);
		add(jlblJob);
		add(jlblBirthday);
		add(jlblLoc);
		add(jlblSal);
		
		jlblEmpNo.setBounds(45,10,100,30);
		jlblEname.setBounds(45,60,100,30);
		jlblLevel.setBounds(45,110,100,30);
		jlblJobTel.setBounds(45,160,100,30);
		jlblEmail.setBounds(45,210,100,30);
		jlblDept.setBounds(300,10,100,30);
		jlblTeam.setBounds(300,60,100,30);
		jlblJob.setBounds(300,110,100,30);
		jlblBirthday.setBounds(300,160,100,30);
		jlblLoc.setBounds(300,210,100,30);
		jlblSal.setBounds(300,260,100,30);
		
		add(jtfEmpNo);
		add(jtfEname);
		add(jtfLevel);
		add(jtfJobTel);
		add(jtfEmail);
		add(jtfDept);
		add(jtfTeam);
		add(jtfJob);
		add(jtfBirthday);
		add(jtfLoc);
		add(jtfSal);
		
		jtfEmpNo.setBounds(120,10,110,30);
		jtfEname.setBounds(120,60,110,30);
		jtfLevel.setBounds(120,110,110,30);
		jtfJobTel.setBounds(120,160,110,30);
		jtfEmail.setBounds(120,210,110,30);
		jtfDept.setBounds(370,10,110,30);
		jtfTeam.setBounds(370,60,110,30);
		jtfJob.setBounds(370,110,110,30);
		jtfBirthday.setBounds(370,160,110,30);
		jtfLoc.setBounds(370,210,110,30);
		jtfSal.setBounds(370,260,110,30); 
		
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
		
		setSize(680,450);
		setVisible(true);
		
	}//constructor
	
	public JLabel getJlblEmpno() {
		return jlblEmpNo;
	}

	public JLabel getJlblEname() {
		return jlblEname;
	}

	public JLabel getJlblLevel() {
		return jlblLevel;
	}

	public JLabel getJlblJobTel() {
		return jlblJobTel;
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

	public JLabel getJlblBirthday() {
		return jlblBirthday;
	}

	public JLabel getJlblLoc() {
		return jlblLoc;
	}

	public JLabel getJlblSal() {
		return jlblSal;
	}

	public JTextField getJtfEmpno() {
		return jtfEmpNo;
	}

	public JTextField getJtfEname() {
		return jtfEname;
	}

	public JTextField getJtfLevel() {
		return jtfLevel;
	}

	public JTextField getJtfJobTel() {
		return jtfJobTel;
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

	public JTextField getJtfBirthday() {
		return jtfBirthday;
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

	public static void main(String[]args) {
		new ManageEmpDialog();
	}
	
}//class
