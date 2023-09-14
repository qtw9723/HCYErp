package manageEmp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import VO.EmpVO;

public class ManageEmpDialogEvt extends MouseAdapter implements ActionListener{

	private ManageEmpDialog med;
	
	public ManageEmpDialogEvt(ManageEmpDialog med) {
		this.med=med;
		
		med.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				med.dispose();
			}
			
		});//addWindowListener
		
	}//constructor

	public void okModify() throws SQLException {//바뀐정보들 업데이트
		EmpVO eVO=new EmpVO();
		//이름 수정
		eVO.setEname(med.getJtfEname().getText());
		
		//직급 수정
		eVO.setLevel(med.getJtfLevel().getText());
		
		//전화번호 수정
		eVO.setTel(med.getJtfTel().getText());
		
		//이메일 수정
		eVO.setEmail(med.getJtfEmail().getText());
		
		//부서 수정
		eVO.setDept(med.getJtfDept().getText());
		
		//팀 수정
		eVO.setTeam(med.getJtfTeam().getText());
		
		//직무 수정
		eVO.setJob(med.getJtfJob().getText());
		
		
		//근무지 수정
		eVO.setDeptLoc(med.getJtfLoc().getText());
		
		
		 // 연봉 수정
	    try {
	        eVO.setSal(Integer.parseInt(med.getJtfSal().getText()));
	    } catch (NumberFormatException e) {
	        // 정수로 변환할 수 없는 경우 예외 처리
	        JOptionPane.showMessageDialog(med, "연봉은 정수로 입력해야 합니다.");
	        return;
	    }

	    try {
	        int rowCnt = ManageEmpDAO.getInstance().updateEmpInfo(eVO);
	        
	        if (rowCnt > 0) {
	            JOptionPane.showMessageDialog(med, "사원정보가 변경되었습니다.");
	        } else {
	            JOptionPane.showMessageDialog(med, "사원정보 변경에 실패했습니다.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}//okModify
	
	public void cancel() {
		med.dispose();
	}//cancel
	
	public void modifyEmpInfo() {
		med.getJbtnModify().setVisible(false);
		med.getJbtnOK().setVisible(true);
		med.getJbtnCancel().setVisible(true);
		med.getJbtnOK().setBounds(180,320,150,50);
		med.getJbtnCancel().setBounds(350, 320, 150, 50);
		
	}//modifyEmpInfo
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==med.getJbtnModify()) {
			modifyEmpInfo();
		}else if(e.getSource()==med.getJbtnOK()) {
			try {
				okModify();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==med.getJbtnCancel()) {
			cancel();
		}
		
	}//actionPerformed
	
}//class
