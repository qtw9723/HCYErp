package manageDoc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

public class FileUploadDialogEvt extends MouseAdapter implements ActionListener {
	private FileUploadDialog fud;
	private  String selectPath ;
	public FileUploadDialogEvt(FileUploadDialog fud) {
		this.fud = fud;
		
		fud.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				fud.dispose();
			}
			
		});
	}//constructor

	public void addFile() {
	    JFileChooser jfc = new JFileChooser();
	    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY); // 파일만 선택하도록 설정

	    int value = jfc.showOpenDialog(fud);

	    if (value == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = jfc.getSelectedFile();

	        if (selectedFile.isFile()) { // 선택한 것이 파일인 경우에만 처리
	        	System.out.println("df");
	            String selectPath = selectedFile.getAbsolutePath();
	            System.out.println(selectPath);
//	            DefaultListModel<String> dlm = (DefaultListModel<String>) fud.getJlFile().getModel();
	            fud.getListmodel().addElement(selectPath);
	        }
	    }
	}//addFile
	
	public void deleteFile() {
		
	}//deleteFile
	
	public void uploadFile() {
		
	}//uploadFile
	
	
	
	public String getSelectPath() {
		return selectPath;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fud.getJbtnCancel()) {
			fud.dispose();
		}
		if(e.getSource()==fud.getJbtnAddFile()) {
			addFile();
		}
		if(e.getSource()==fud.getJbtnDeleteFile()) {
			deleteFile();
		}
		if(e.getSource()==fud.getJbtnUpload()) {
			uploadFile();
		}
	}//actionPerformed

}//class
