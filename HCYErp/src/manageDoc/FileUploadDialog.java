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
	private DefaultListModel<String> listmodel;
	private JButton jbtnDeleteFile;
	private JList<String> jlFile;
	private JButton jbtnAddFile;
	private JButton jbtnUpload;
	private JButton jbtnCancel;
	private ManageDoc md;
	
	public FileUploadDialog(ManageDoc md) {
		this.md = md;
		
		getContentPane().setBackground(new Color(255,245,245));

		//버튼 선언
		jbtnDeleteFile=new JButton("첨부파일 삭제");
		jbtnUpload=new JButton("업로드확인");
		jbtnAddFile=new JButton("파일첨부");
		jbtnCancel=new JButton("취소");
		//파일 리스트
		listmodel=new DefaultListModel<String>();
		jlFile=new JList<String>(listmodel);
		JScrollPane jsp=new JScrollPane(jlFile);
		jsp.setBounds(25,25,500,350);

		//이벤트 등록
		FileUploadDialogEvt event=new FileUploadDialogEvt(this);
		jbtnAddFile.addActionListener(event);
		jbtnDeleteFile.addActionListener(event);
		jbtnUpload.addActionListener(event);
		jbtnCancel.addActionListener(event);
		
		//레이아웃 설정
		setLayout(null);
		
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 13);
		//버튼 레이아웃 설정
		//파일 추가 버튼
		jbtnAddFile.setBackground(new Color(0x6D47B0));
		jbtnAddFile.setForeground(Color.white);
		jbtnAddFile.setBounds(10,400,100,40);
		jbtnAddFile.setFont(jbtnFont);
		//삭제 버튼
		jbtnDeleteFile.setBackground(new Color(0x6D47B0));
		jbtnDeleteFile.setForeground(Color.white);
		jbtnDeleteFile.setBounds(140,400,140,40);
		jbtnDeleteFile.setFont(jbtnFont);
		//업로드 버튼
		jbtnUpload.setBackground(new Color(0x6D47B0));
		jbtnUpload.setForeground(Color.white);
		jbtnUpload.setBounds(310,400,100,40);
		jbtnUpload.setFont(jbtnFont);
		//취소버튼
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		jbtnCancel.setForeground(Color.white);
		jbtnCancel.setBounds(440,400,100,40);
		jbtnCancel.setFont(jbtnFont);
		
		//컴포넌트 추가
		//버튼 추가
		add(jbtnAddFile);
		add(jbtnDeleteFile);
		add(jbtnUpload);
		add(jbtnCancel);
		//스크롤 페인 추가
		add(jsp);
		jsp.setVisible(true);
		
		//다이얼로그 설정
		setTitle("파일업로드");
		setSize(580,500);
		setVisible(true);
		setResizable(false);
	}//constructor
	
	public DefaultListModel<String> getListmodel() {
		return listmodel;
	}
	public JButton getJbtnDeleteFile() {
		return jbtnDeleteFile;
	}
	public JList<String> getJlFile() {
		return jlFile;
	}
	public JButton getJbtnAddFile() {
		return jbtnAddFile;
	}
	public JButton getJbtnUpload() {
		return jbtnUpload;
	}
	public JButton getJbtnCancel() {
		return jbtnCancel;
	}
	public ManageDoc getMd() {
		return md;
	}
}//class
