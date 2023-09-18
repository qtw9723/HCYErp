package attendance;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import VO.EmpVO;
import login.HCYErpDAO;

public class ChangePassDialogEvt extends MouseAdapter implements ActionListener,FocusListener {

	private ChangePassDialog cpd;
	//텍스트 필드 디자인
	private Border focusField=BorderFactory.createLineBorder(new Color(0xBC5BC2),2);// Line border
	private Border unfocusField=BorderFactory.createLineBorder(Color.LIGHT_GRAY);// Line border

	public ChangePassDialogEvt(ChangePassDialog cpd) {
		this.cpd = cpd;
		cpd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cpd.dispose();
			}//windowClosing
		});// addWindowListener
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cpd.getJbtnOK()) {
			changePass();
		} // end if
		if (e.getSource() == cpd.getJbtnCancel()) {
			cancelChangePass();
		} // end if
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent e) {
		//새로운 비밀번호 보기
		if (e.getSource() == cpd.getJlblNewView()) {
			textfieldState();
		}//if
		//새로운 비밀번호 숨기기
		if (e.getSource() == cpd.getJlblNewHide()) {
			passwordState();
		}//if 
		//재입력한 비밀번호 보기
		if (e.getSource() == cpd.getJlblCheckNewView()) {
			textfieldStateCheck();
		}//if
		//재입력한 비밀번호 숨기기
		if (e.getSource() == cpd.getJlblCheckNewHide()) {
			passwordStateCheck();
		}//if
	}// mouseClicked

	@Override
	public void focusGained(FocusEvent e) {
		//현재 패스워드 필드
		if(e.getSource()==cpd.getJpfCurrentPass()) {
			cpd.getJpfCurrentPass().setBorder(unfocusField);
		}//if
		//새 패스워드 필드
		if(e.getSource()==cpd.getJpfNewPass()) {
			cpd.getJpfNewPass().setBorder(unfocusField);
		}//if
		//새 패스워드 확인 필드
		if(e.getSource()==cpd.getJpfCheckNewPass()) {
			cpd.getJpfCheckNewPass().setBorder(unfocusField);
		}//if
	}//focusGained

	@Override
	public void focusLost(FocusEvent e) {
		//현대 패스워드 필드
		if(e.getSource()==cpd.getJpfCurrentPass()) {
			cpd.getJpfCurrentPass().setBorder(focusField);
		}//if
		//새 패스워드 필드
		if(e.getSource()==cpd.getJpfNewPass()) {
			cpd.getJpfNewPass().setBorder(focusField);
		}//if
		//새 패스워드 확인 필드
		if(e.getSource()==cpd.getJpfCheckNewPass()) {
			cpd.getJpfCheckNewPass().setBorder(focusField);
		}//if
	}//focusLost
	
	public void textfieldState() {
		//현재 비밀번호 미입력
		if (cpd.getJpfCurrentPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "현재 비밀번호를 입력하세요");
			return;
		}//if
		//새 비밀번호 미입력
		if (cpd.getJpfNewPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "새 비밀번호를 입력해주세요");
			return;
		}//if
		//비밀번호 보이게 설정
		cpd.getJpfNewPass().setVisible(false);
		cpd.getJtfNewPass().setVisible(true);
		//비밀번호 보이기 이미지 전환
		cpd.getJlblNewHide().setVisible(true);
		cpd.getJlblNewView().setVisible(false);
		//비밀번호 꺼내기
		String password = new String(cpd.getJpfNewPass().getPassword());
		cpd.getJtfNewPass().setText(password);
	}// textfieldState
	
	public void textfieldStateCheck() {
		if (cpd.getJpfCurrentPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "현재 비밀번호를 입력하세요");
			return;
		}//if
		if (cpd.getJpfNewPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "새 비밀번호를 입력하세요");
			return;
		}//if
		if (cpd.getJpfCheckNewPass().getPassword().length == 0) {
			JOptionPane.showMessageDialog(cpd, "비밀번호 확인란을 입력하세요");
			return;
		}//if
		cpd.getJpfCheckNewPass().setVisible(false);
		cpd.getJtfCheckNewPass().setVisible(true);
		
		cpd.getJlblCheckNewView().setVisible(false);
		cpd.getJlblCheckNewHide().setVisible(true);
		
		char[] passwordChars = cpd.getJpfCheckNewPass().getPassword();
		String password = new String(passwordChars);
		
		cpd.getJtfCheckNewPass().setText(password);
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
			//입력한 패스워드 불러오기
			String currentPassword = new String(cpd.getJpfCurrentPass().getPassword()); 
			String newPassword = new String(cpd.getJpfNewPass().getPassword()); 
			String checkPassword = new String(cpd.getJpfCheckNewPass().getPassword());
			//빈칸 입력시
			if (currentPassword.isEmpty() || newPassword.isEmpty() || checkPassword.isEmpty()) {
				JOptionPane.showMessageDialog(cpd, "모든 칸이 필수 입력입니다.");
				return;
			} // end if
			
			if (currentPassword.equals(newPassword) || currentPassword.equals(checkPassword)) {
				JOptionPane.showMessageDialog(cpd, "현재 비밀번호와 다른 비밀번호를 입력해주세요");
				return;
			} // end if
			
			// 문자열 비교에는 equals 메서드를 사용
			if (!newPassword.equals(checkPassword)) {
				JOptionPane.showMessageDialog(cpd, "새로운 비밀번호를 올바르게 입력하세요");
				return;
			} // end if
			
			// 비밀번호
			EmpVO eVO = new EmpVO();
			String dbPass = HCYErpDAO.getInstance().geteVO().getPass();
			
			if (!currentPassword.equals(dbPass)) {
				JOptionPane.showMessageDialog(cpd, "현재 비밀번호가 맞지 않습니다.");
				return;
			} // end if
			
			// 사번 추가
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
			} // end else
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// changePass

}// class