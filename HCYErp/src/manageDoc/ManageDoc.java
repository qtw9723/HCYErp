package manageDoc;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class ManageDoc extends JPanel {
	private JScrollPane jspDocList;
	private JButton jbtnFileUpload;
	private JButton jbtnFileDownload;
	private JButton jbtnFileDelete;
	private JButton jbtnRef;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	
	public ManageDoc() {
		setLayout(null);
		
		JPanel jpDoc = new JPanel();
		//다오 추가시 연결
		for(int i =0;i<100;i++) {
			jpDoc.add(new JCheckBox("문서"+i));
			
		}// 다다다ㅏ다다다다다오오오오오오오옹
		
		
		//문서 목록 체크박스
		jpDoc.setLayout(new BoxLayout(jpDoc, BoxLayout.Y_AXIS));//세로 정렬
		jspDocList = new JScrollPane(jpDoc);
		add(jspDocList);
		jspDocList.setBounds(100,50,800,500);
		jspDocList.setBorder(new TitledBorder("문서 목록"));
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		add(jbtnLogOut);
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		//업로드 버튼
		jbtnFileUpload = new JButton("파일업로드");
		jbtnFileUpload.setBounds(930,100,130,55);
		add(jbtnFileUpload);
		//다운로드 버튼
		jbtnFileDownload = new JButton("다운로드");
		jbtnFileDownload.setBounds(930,200,130,55);
		add(jbtnFileDownload);
		//파일 삭제
		jbtnFileDelete = new JButton("파일 삭제");
		jbtnFileDelete.setBounds(930,300,130,55);
		add(jbtnFileDelete);
		jbtnRef = new JButton("부서 참조");
		jbtnRef.setBounds(930,400,130,55);
		add(jbtnRef);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		
	}//constructor
}// class
