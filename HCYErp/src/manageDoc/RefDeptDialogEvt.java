package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
		int mdCnt = 0;
		int rddCnt = 0;
		DocPermissionVO dpVO = null;
		List<Integer> docNoList = new ArrayList<Integer>();
		List<DocPermissionVO> dpVOList = new ArrayList<DocPermissionVO>();
		for( Entry<Integer, JCheckBox> entry:rdd.getMd().getJcheckBoxMap().entrySet()) {
			if(entry.getValue().isSelected()) {
				docNoList.add(entry.getKey());
				for(JCheckBox jcb:rdd.getJcbList()) {
					dpVO = new DocPermissionVO();
					if(jcb.isSelected()) {
						dpVO.setDocNo(entry.getKey());
						dpVO.setdeptName(jcb.getText());
						dpVOList.add(dpVO);
					continue;
					}//if
					rddCnt++;
				}//for
				if(rddCnt == rdd.getJcbList().size()) {
					JOptionPane.showMessageDialog(rdd, "참조할 부서를 선택해주세요!");
					return;
				}//if
				continue;
			}//if
			mdCnt++;
		}//for
		if(mdCnt == rdd.getMd().getJcheckBoxMap().size()) {
			JOptionPane.showMessageDialog(rdd, "부서 참조를 진행할 파일을 선택해주세요!");
			return;
		}//if
		ManageDocDAO.getInstance().deleteDocPermission(docNoList);
		ManageDocDAO.getInstance().insertDocPermission(dpVOList);
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
