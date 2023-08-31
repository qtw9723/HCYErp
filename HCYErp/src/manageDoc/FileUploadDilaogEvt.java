package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class FileUploadDilaogEvt extends MouseAdapter implements ActionListener {
	private FileUploadDialog fud;

	private FileUploadDilaogEvt(FileUploadDialog fud) {
		this.fud = fud;
	}//constructor

	@Override
	public void actionPerformed(ActionEvent e) {
	}//actionPerformed

}//class
