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
	    eVO.setSal(Integer.parseInt(med.getJtfSal().getText()));
	    
	    //사원번호 수정
	    for(String name : med.getMe().getListName()) {
	    	if(med.getMe().getJlName().getSelectedValue().equals(name.substring(0, name.indexOf("/")))) {
	    		eVO.setEmpNo(Integer.parseInt(name.substring(name.indexOf("/")+1)));
	    	}
	    }
	    try {
	    	int rowCnt = ManageEmpDAO.getInstance().updateEmpModifyInfo(eVO);
	        System.out.println(rowCnt);
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
		med.getJbtnOK().setBounds(130,320,120,50);
		med.getJbtnCancel().setBounds(300, 320, 120, 50);
		
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
