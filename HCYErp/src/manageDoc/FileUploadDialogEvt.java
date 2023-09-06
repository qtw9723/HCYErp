package manageDoc;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
	        	String selectPath = selectedFile.getAbsolutePath();
	        	if(exist(selectPath)) {//중복 파일이 없으면 실행
	        		fud.getListmodel().addElement(selectPath);
	        	}else {
	        		JOptionPane.showMessageDialog(jfc, "이미 존재하는 파일입니다");
	        	}
	        	
	        }else {
	        	JOptionPane.showMessageDialog(jfc, "파일을 업로드 해주세요");
	        }
	    }
	}//addFile
	
	private boolean exist(String filePath) {//이미 파일이 존재하면 false
		DefaultListModel<String>file=fud.getListmodel();
		for(int i =0;i<file.getSize();i++) {
			String existPath=file.getElementAt(i);
			if(filePath.equals(existPath)) {
				return false;//중복 파일 존재
			}
		}
		return true;//중복 파일 없음
		
	}
	
	public void deleteFile() {
		int selectedFile=fud.getJlFile().getSelectedIndex();
		
		if(selectedFile>=0) {//jlist니까 0도 포함해야지 멍충아
			fud.getListmodel().remove(selectedFile);
		}
	}//deleteFile
	
	public void uploadFile() {//DB로 jlist 파일첨부된것 보내기
		
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
