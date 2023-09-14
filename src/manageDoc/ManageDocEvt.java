package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import VO.DocPermissionVO;

public class ManageDocEvt extends MouseAdapter implements ActionListener {
	private ManageDoc md;

	public ManageDocEvt(ManageDoc md) {
		this.md = md;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		//업로드 버튼
		if (e.getSource() == md.getJbtnFileUpload()) {
			new FileUploadDialog(this).setBounds(md.getHcyE().getX() + 100, md.getHcyE().getY() + 100, 500, 500);
		} // if
		
		//다운로드 버튼
		if (e.getSource() == md.getJbtnFileDownload()) {
			
		} // if
		
		//파일 삭제 버튼
		if (e.getSource() == md.getJbtnFileDelete()) {
			StringBuilder msg = new StringBuilder();
			msg.append("다음의 파일을 선택하셨습니다!\n");
			List<Integer> docNoList = new ArrayList<Integer>();
			for(Entry<Integer, JCheckBox> entry:md.getCheckBoxMap().entrySet()){
				if(entry.getValue().isSelected()) {
					docNoList.add(entry.getKey());
					msg.append(entry.getValue().getText()).append("\n");
				}//if
			}//for
			msg.append("위의 파일들을 삭제 하시겠습니까?");
			
			//삭제 확인
			switch (JOptionPane.showConfirmDialog(md, msg.toString())) {
			case JOptionPane.OK_OPTION:
				try {
					ManageDocDAO.getInstance().deleteDoc(docNoList);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(md, "데이터 베이스에서 문제가 발생했습니다.\n기술팀에 문의해 주세요!");
				}//catch
				break;

			default:
			}//switch 
			
		} // if
		
		//참조 버튼
		if (e.getSource() == md.getJbtnRef()) {
		} // if
		
	}// actionPerformed

	public ManageDoc getMd() {
		return md;
	}

	
}// class
