package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ChangePassDialogEvt extends MouseAdapter implements ActionListener {

	private ChangePassDialog cpd;

	public ChangePassDialogEvt(ChangePassDialog cpd) {
		this.cpd = cpd;

		cpd.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				cpd.dispose();
			}

		});// addWindowListener
	}// constructor

	public void confirmPassChange() {
	    char[] currentPasswordChars = cpd.getJpfCurrentPass().getPassword();
	    char[] newPasswordChars = cpd.getJpfNewPass().getPassword();
	    char[] checkPasswordChars = cpd.getJpfCheckNewPass().getPassword();

	    String currentPassword = new String(currentPasswordChars); // char 배열을 문자열로 변환
	    String newPassword = new String(newPasswordChars); //
	    String checkPassword = new String(checkPasswordChars); 
	    
	    if(currentPassword.isEmpty()||newPassword.isEmpty()||checkPassword.isEmpty()) {
	    	JOptionPane.showMessageDialog(cpd, "해당 비밀번호를 입력해주세요");
	    }else {
	    	
	    	if (currentPassword.equals(newPassword)||currentPassword.equals(checkPassword)) {
	    		JOptionPane.showMessageDialog(cpd, "현재 비밀번호와 다른 비밀번호를 입력해주세요");
	    	} else {
	    		if (!newPassword.equals(checkPassword)) { // 문자열 비교에는 equals 메서드를 사용
	    			JOptionPane.showMessageDialog(cpd, "새로운 비밀번호를 올바르게 입력하세요");
	    		} else {
	    			System.out.println("둘다 맞음");
	    			// 이러면 이제 바뀐 비밀번호를 보내야겠지?
	    		}
	    	}
	    }
	}//confirmPassChange


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cpd.getJbtnOK()) {
			confirmPassChange();
		}else if(e.getSource()==cpd.getJbtnCancel()) {
			cancelChangePass();
		}else if(e.getSource()==cpd.getJbtnOK()) {
			changePass();
		}
	}// actionPerformed

	public void textfieldState() {
		if (cpd.getJpfCurrentPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "현재 비밀번호를 입력하세요");
		} else {
			if (cpd.getJpfNewPass().getPassword().length == 0) {
				JOptionPane.showMessageDialog(cpd, "새 비밀번호를 입력해주세요");
			} else {
				cpd.getJpfNewPass().setVisible(false);
				cpd.getJtfNewPass().setVisible(true);

				cpd.getJlblNewHide().setVisible(true);
				cpd.getJlblNewView().setVisible(false);

				
				char[] passwordChars = cpd.getJpfNewPass().getPassword();
				String password = new String(passwordChars);
				
				cpd.getJtfNewPass().setText(password);
			}
		}
	}// textfieldState

	public void passwordState() {
		cpd.getJpfNewPass().setVisible(true);
		cpd.getJtfNewPass().setVisible(false);

		cpd.getJlblNewHide().setVisible(false);
		cpd.getJlblNewView().setVisible(true);
	}// passwordState

	
	public void textfieldStateCheck() {
		if(cpd.getJpfCurrentPass().getPassword().length==0) {
			JOptionPane.showMessageDialog(cpd, "현재 비밀번호를 입력하세요");
		}else {
			if(cpd.getJpfNewPass().getPassword().length==0) {
				JOptionPane.showMessageDialog(cpd, "새 비밀번호를 입력하세요");
			}else {
				if(cpd.getJpfCheckNewPass().getPassword().length==0) {
					JOptionPane.showMessageDialog(cpd, "비밀번호 확인란을 입력하세요");
				}else {
					cpd.getJpfCheckNewPass().setVisible(false);
					cpd.getJtfCheckNewPass().setVisible(true);
					
					cpd.getJlblCheckNewView().setVisible(false);
					cpd.getJlblCheckNewHide().setVisible(true);
					
					char[] passwordChars = cpd.getJpfCheckNewPass().getPassword();
					String password = new String(passwordChars);
					
					cpd.getJtfCheckNewPass().setText(password);
				}
			}
		}
	}// textfieldStateCheck

	public void passwordStateCheck() {
		cpd.getJpfCheckNewPass().setVisible(true);
		cpd.getJtfCheckNewPass().setVisible(false);
		cpd.getJlblCheckNewHide().setVisible(false);
		cpd.getJlblCheckNewView().setVisible(true);
	}// passwordStateCheck
	

	public void cancelChangePass() {
		cpd.dispose();
	}//cancelChangePass
	
	public void changePass() {
		
		//비밀번호 변경하자
	}//changePass
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cpd.getJlblNewView()) {
			textfieldState();
		}else if (e.getSource() == cpd.getJlblNewHide()) {
			passwordState();
		}else if (e.getSource() == cpd.getJlblCheckNewView()) {
			textfieldStateCheck();
		} else if (e.getSource() == cpd.getJlblCheckNewHide()) {
			passwordStateCheck();
		}

		
	}// mouseClicked

}// class
