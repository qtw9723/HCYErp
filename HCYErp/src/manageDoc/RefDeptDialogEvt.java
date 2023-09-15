package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import VO.DocPermissionVO;

public class RefDeptDialogEvt extends MouseAdapter implements ActionListener {
	private RefDeptDialog rdd;

	public RefDeptDialogEvt(RefDeptDialog rdd) {
		this.rdd = rdd;
		
		rdd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				rdd.dispose();
			}//windowClosing
		});//addWindowListener
	}//constructor

	public void approveRef() throws SQLException {//권한 부여
		DocPermissionVO dpVO = null;
		for( Entry<Integer, JCheckBox> entry:rdd.getMd().getJcheckBoxMap().entrySet()) {
			if(entry.getValue().isSelected()) {
				
				for(JCheckBox jcb:rdd.getJcbList()) {
					if(jcb.isSelected()) {
					dpVO = new DocPermissionVO();
					dpVO.setDocNo(entry.getKey());
					dpVO.setdeptName(jcb.getText());
					ManageDocDAO.getInstance().insertDocPermission(dpVO);
					}//if
				}//for
			}//if
		}//for
		JOptionPane.showMessageDialog(rdd, "전체 파일 권한부여에 성공했습니다.");
	}//approveRef
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rdd.getJbtnApproveRef()) {
			try {
				approveRef();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//catch
		}//if
		if(e.getSource()==rdd.getJbtnCancel()) {
			rdd.dispose();
		}//if
	}//actionPerformed

}//class
