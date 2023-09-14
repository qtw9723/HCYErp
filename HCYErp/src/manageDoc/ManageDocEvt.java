package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VO.DocPermissionVO;

public class ManageDocEvt extends MouseAdapter implements ActionListener {
	private ManageDoc md;

	public ManageDocEvt(ManageDoc md) {
		this.md = md;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == md.getJbtnFileUpload()) {
			new FileUploadDialog(this).setBounds(md.getHcyE().getX() + 100, md.getHcyE().getY() + 100, 500, 500);
		} // if
	}// actionPerformed

	public ManageDoc getMd() {
		return md;
	}

	
}// class
