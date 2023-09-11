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
	private DefaultListModel<String> listmodel;
	public FileUploadDialog() {
		
		
		jbtnAddFile=new JButton("파일첨부");
		jbtnDeleteFile=new JButton("첨부파일 삭제");
		jbtnUpload=new JButton("업로드확인");
		jbtnCancel=new JButton("취소");
	
		listmodel=new DefaultListModel<String>();
		
		jlFile=new JList<String>(listmodel);
		
		FileUploadDialogEvt fude=new FileUploadDialogEvt(this);
		jbtnAddFile.addActionListener(fude);
		jbtnDeleteFile.addActionListener(fude);
		jbtnUpload.addActionListener(fude);
		jbtnCancel.addActionListener(fude);

		
		JScrollPane jsp=new JScrollPane(jlFile);
		
//		jsp.add(jlFile);

		setLayout(null);
		
		
		
		jsp.setBounds(25,0,500,400);
		jbtnAddFile.setBounds(10,400,100,30);
		jbtnDeleteFile.setBounds(140,400,140,30);
		jbtnUpload.setBounds(310,400,100,30);
		jbtnCancel.setBounds(440,400,100,30);
		
		add(jbtnAddFile);
		add(jbtnDeleteFile);
		add(jbtnUpload);
		add(jbtnCancel);

		add(jsp);
		
		
		jsp.setVisible(true);
		
		
		
		setSize(580,500);
		setVisible(true);
		
	}//constructor
	
	public JList<String> getJlFile() {
		return jlFile;
	}

	public JButton getJbtnAddFile() {
		return jbtnAddFile;
	}

	public JButton getJbtnDeleteFile() {
		return jbtnDeleteFile;
	}

	public JButton getJbtnUpload() {
		return jbtnUpload;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public DefaultListModel<String> getListmodel() {
		return listmodel;
	}

	public static void main(String[]args) {
		new FileUploadDialog();
	}
}//class
