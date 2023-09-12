package attendance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import VO.EmpVO;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cpd.getJbtnOK()) {
			changePass();
		}
		if (e.getSource() == cpd.getJbtnCancel()) {
			cancelChangePass();
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

	public void textfieldStateCheck() {
		if (cpd.getJpfCurrentPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "현재 비밀번호를 입력하세요");
		} else {
			if (cpd.getJpfNewPass().getPassword().length == 0) {
				JOptionPane.showMessageDialog(cpd, "새 비밀번호를 입력하세요");
			} else {
				if (cpd.getJpfCheckNewPass().getPassword().length == 0) {
					JOptionPane.showMessageDialog(cpd, "비밀번호 확인란을 입력하세요");
				} else {
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

	public void passwordState() {
		cpd.getJpfNewPass().setVisible(true);
		cpd.getJtfNewPass().setVisible(false);

		cpd.getJlblNewHide().setVisible(false);
		cpd.getJlblNewView().setVisible(true);
	}// passwordState

	public void passwordStateCheck() {
		cpd.getJpfCheckNewPass().setVisible(true);
		cpd.getJtfCheckNewPass().setVisible(false);
		cpd.getJlblCheckNewHide().setVisible(false);
		cpd.getJlblCheckNewView().setVisible(true);
	}// passwordStateCheck

	public void cancelChangePass() {
		cpd.dispose();
	}// cancelChangePass

	public void changePass() {
		try {
			char[] currentPasswordChars = cpd.getJpfCurrentPass().getPassword();
			char[] newPasswordChars = cpd.getJpfNewPass().getPassword();
			char[] checkPasswordChars = cpd.getJpfCheckNewPass().getPassword();

			String currentPassword = new String(currentPasswordChars); // char 배열을 문자열로 변환
			String newPassword = new String(newPasswordChars); //
			String checkPassword = new String(checkPasswordChars);

			if (currentPassword.isEmpty() || newPassword.isEmpty() || checkPassword.isEmpty()) {
				JOptionPane.showMessageDialog(cpd, "해당 비밀번호를 입력해주세요");
			} else {
				if (currentPassword.equals(newPassword) || currentPassword.equals(checkPassword)) {
					JOptionPane.showMessageDialog(cpd, "현재 비밀번호와 다른 비밀번호를 입력해주세요");
				} else {
					if (!newPassword.equals(checkPassword)) { // 문자열 비교에는 equals 메서드를 사용
						JOptionPane.showMessageDialog(cpd, "새로운 비밀번호를 올바르게 입력하세요");
					} else {
						EmpVO eVO = new EmpVO();
						String currentDBPass = eVO.getPass();
						if (currentPassword.equals(currentDBPass)) {
							// 사번 추가
							if (newPassword.equals(checkPassword)) {
								eVO.setEmpNo(cpd.getAd().getHcyE().getUser());
								String tempPass = new String();
								tempPass = checkPassword;
								eVO.setPass(tempPass);
								int updatedRows = AttendanceDAO.getInstance().updateChangePass(eVO);
								System.out.println("업데이트 행수  " + updatedRows);
								if (updatedRows >= 0) {
									JOptionPane.showMessageDialog(cpd, "비밀번호가 변경되었습니다.");
								} else {
									JOptionPane.showMessageDialog(cpd, "비밀번호 변경이 실패되었습니다.");
								}
							} else {
								JOptionPane.showMessageDialog(cpd, "입력하신 새 비밀번호가 다릅니다.");
							}
						} else {
							JOptionPane.showMessageDialog(cpd, "현재 비밀번호가 맞지 않습니다.");

						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cpd.getJlblNewView()) {
			textfieldState();
		} else if (e.getSource() == cpd.getJlblNewHide()) {
			passwordState();
		} else if (e.getSource() == cpd.getJlblCheckNewView()) {
			textfieldStateCheck();
		} else if (e.getSource() == cpd.getJlblCheckNewHide()) {
			passwordStateCheck();
		}

	}// mouseClicked

}// class
