package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class ResetPassDialogEvt extends MouseAdapter implements ActionListener {
	private ResetPassDialog rpd;

	public ResetPassDialogEvt(ResetPassDialog rpd) {
		this.rpd = rpd;
		rpd.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				rpd.dispose();
			}
			
		});//addWindowListener
	}// constructor

	public void resetPass() throws SQLException {
		if(rpd.getJtfEmpNo().getText().length()==0||rpd.getJtfEname().getText().length()==0||
				rpd.getJtfSsn().getText().length()==0) {
			JOptionPane.showMessageDialog(rpd, "값을 입력해주세요");
			return;
		}
			int empNo=Integer.parseInt(rpd.getJtfEmpNo().getText());
			String ename=rpd.getJtfEname().getText();
			String ssn=rpd.getJtfSsn().getText();
			
			HCYErpDAO.getInstance().selectLogin(empNo);
			boolean flag=false;
			

			flag=HCYErpDAO.getInstance().selectLogin(empNo);
			
		    if(flag) {
		    	EmpVO eVO=new EmpVO();
		    	//사원번호
		    	eVO.setEmpNo(empNo);
		    	
		    	//이름
		    	eVO.setEname(ename);
		    	
		    	//주민번호
		    	eVO.setSsn(ssn);
		    	
		    	
		    	HCYErpDAO.getInstance().updatePassword(eVO);
		    	JOptionPane.showMessageDialog(rpd, "비밀번호가 초기화되었습니다");
		    
		    }else {
		    	JOptionPane.showMessageDialog(rpd, "올바른 정보를 입력하세요");
		    }
		    
	}//resetPass
	
	public void cancel() {
		rpd.dispose();
	}//cancel
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rpd.getJbtnCancel()) {
			cancel();
		}else if(e.getSource()==rpd.getJbtnResetPass()) {
			try {
				resetPass();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}// actionPerformed

	

	
}// class
