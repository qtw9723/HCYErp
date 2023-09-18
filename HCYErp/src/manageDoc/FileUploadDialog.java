package manageDoc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FileUploadDialog extends JDialog {
	private JList<String> jlFile;
	private JButton jbtnAddFile;
	private JButton jbtnDeleteFile;
	private JButton jbtnUpload;
	private JButton jbtnCancel;
	private DefaultListModel<String> listmodel;
	private ManageDoc md;
	
	public FileUploadDialog(ManageDoc md) {
		this.md = md;
		
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

		setLayout(null);
		
		jsp.setBounds(25,25,500,350);
		
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 12);
		jbtnAddFile.setBounds(10,400,100,40);
		jbtnAddFile.setBackground(new Color(0x6D47B0));
		jbtnAddFile.setFont(jbtnFont);
		jbtnAddFile.setForeground(Color.white);
		jbtnDeleteFile.setBounds(140,400,140,40);
		jbtnDeleteFile.setBackground(new Color(0x6D47B0));
		jbtnDeleteFile.setFont(jbtnFont);
		jbtnDeleteFile.setForeground(Color.white);
		jbtnUpload.setBounds(310,400,100,40);
		jbtnUpload.setBackground(new Color(0x6D47B0));
		jbtnUpload.setFont(jbtnFont);
		jbtnUpload.setForeground(Color.white);
		jbtnCancel.setBounds(440,400,100,40);
		jbtnCancel.setBackground(new Color(0x6D47B0));
		jbtnCancel.setFont(jbtnFont);
		jbtnCancel.setForeground(Color.white);
		
		add(jbtnAddFile);
		add(jbtnDeleteFile);
		add(jbtnUpload);
		add(jbtnCancel);

		add(jsp);
		
		jsp.setVisible(true);
		
		setTitle("파일업로드");
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

	public ManageDoc getMd() {
		return md;
	}
	
	
}//class
