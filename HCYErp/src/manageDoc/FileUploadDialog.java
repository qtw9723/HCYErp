package manageDoc;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FileUploadDialog extends JDialog {
	private JList<String> jlFile;
	private JButton jbtnAddFile;
	private JButton jbtnDeleteFile;
	private JButton jbtnUpload;
	private JButton jbtnCancel;
	
	public FileUploadDialog() {
		
		
		jbtnAddFile=new JButton("파일첨부");
		jbtnDeleteFile=new JButton("첨부파일 삭제");
		jbtnUpload=new JButton("업로드확인");
		jbtnCancel=new JButton("취소");
	
		String file="파일명이였던것";
		
		DefaultListModel<String> listmodel=new DefaultListModel<String>();
		for(int i=0;i<100;i++) {
			listmodel.addElement(file);
		}
		
		jlFile=new JList<String>(listmodel);
		
		JScrollPane jsp=new JScrollPane();
		
		jsp.add(jlFile);

		setLayout(null);
		
		jbtnAddFile.setBounds(50,400,100,30);
		
		jsp.setBounds(0,0,500,400);
		
		add(jbtnAddFile);
		
		add(jsp);

		setVisible(true);
		
	}
}//class
