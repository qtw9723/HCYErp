package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ResetPassDialogEvt extends MouseAdapter implements ActionListener {
	private ResetPassDialog fpd;

	public ResetPassDialogEvt(ResetPassDialog fpd) {
		this.fpd = fpd;
		fpd.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				fpd.dispose();
			}
			
		});//addWindowListener
	}// constructor

	public void resetPass() {
		if(fpd.getJtfEmpNo().getText().length()==0||fpd.getJtfEname().getText().length()==0||
				fpd.getJtfSsn().getText().length()==0) {
			JOptionPane.showMessageDialog(fpd, "값을 입력해주세요");
		}else {
			String InputEmpno=String.valueOf(fpd.getInputEmpno());
			String InputEname=String.valueOf(fpd.getInputEname());
			String InputSsn=String.valueOf(fpd.getInputSsn());
			
			if(fpd.getJtfEmpNo().getText().equals(InputEmpno)&&
				fpd.getJtfEname().getText().equals(InputEname)&&
				fpd.getJtfSsn().getText().equals(InputSsn)) {
				JOptionPane.showMessageDialog(fpd, "맞는 비밀번호다 비밀번호 초기화됨");
				fpd.getJtfEmpNo().setText("");
				fpd.getJtfEname().setText("");
				fpd.getJtfSsn().setText("");
			}else {
				JOptionPane.showMessageDialog(fpd, "올바른 정보를 입력하세요");
			}
			
		}
	}//resetPass
	
	public void cancel() {
		fpd.dispose();
	}//cancel
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fpd.getJbtnCancel()) {
			cancel();
		}else if(e.getSource()==fpd.getJbtnResetPass()) {
			resetPass();
		}
	}// actionPerformed

	

	
}// class
