package manageDoc;

import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import VO.DocVO;
import login.HCYErp;

@SuppressWarnings("serial")
public class ManageDoc extends JPanel {
	private JScrollPane jspDocList;
	private JButton jbtnFileUpload;
	private JButton jbtnFileDownload;
	private JButton jbtnFileDelete;
	private JButton jbtnRef;
	private JButton jbtnLogOut;
	private JLabel jlblLogoTxt;
	private HCYErp hcyE;
	private JPanel jpDoc;
	private Map<Integer,JCheckBox> jcheckBoxMap;
	
	public ManageDoc(HCYErp hcyE) {
		this.hcyE=hcyE;
		ManageDocEvt event = new ManageDocEvt(this);
		
		setLayout(null);
		
		jpDoc = new JPanel();
		// 문서 리스트 추가
		List<DocVO> dVOList = null;
		try {
			dVOList = ManageDocDAO.getInstance().selectDoc(hcyE.getUser());
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		JCheckBox jcbdVO =null;
		jcheckBoxMap = new HashMap<Integer, JCheckBox>();
		for(DocVO dVO:dVOList) {
			jcbdVO = new JCheckBox(dVO.getDocName());
			jpDoc.add(jcbdVO);
			jcheckBoxMap.put(dVO.getDocNo(), jcbdVO);
		}// for
		
		//문서 목록 체크박스
		jpDoc.setLayout(new BoxLayout(jpDoc, BoxLayout.Y_AXIS));//세로 정렬
		jspDocList = new JScrollPane(jpDoc);
		add(jspDocList);
		jspDocList.setBounds(100,50,800,500);
		jspDocList.setBorder(new TitledBorder("문서 목록"));
		
		//로그아웃 버튼
		jbtnLogOut = new JButton("로그아웃");
		jbtnLogOut.setBounds(1000,510,150,40);
		jbtnLogOut.setBackground(new Color(0xE0E0E0));
		jbtnLogOut.addActionListener(event);
		add(jbtnLogOut);
		//텍스트 로고
		jlblLogoTxt = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYTextLogo.png"));
		jlblLogoTxt.setBounds(930,450,300,300);
		add(jlblLogoTxt);
		
		//업로드 버튼
		jbtnFileUpload = new JButton("파일업로드");
		jbtnFileUpload.setBounds(930,100,130,55);
		jbtnFileUpload.setBackground(new Color(0x8244AD));
		jbtnFileUpload.addActionListener(event);
		add(jbtnFileUpload);
		//다운로드 버튼
		jbtnFileDownload = new JButton("다운로드");
		jbtnFileDownload.setBounds(930,200,130,55);
		jbtnFileDownload.setBackground(new Color(0x8244AD));
		jbtnFileDownload.addActionListener(event);
		add(jbtnFileDownload);
		//파일 삭제
		jbtnFileDelete = new JButton("파일 삭제");
		jbtnFileDelete.setBounds(930,300,130,55);
		jbtnFileDownload.setBackground(new Color(0x8244AD));
		jbtnFileDelete.addActionListener(event);
		add(jbtnFileDelete);
		//부서참조
		jbtnRef = new JButton("부서 참조");
		jbtnRef.setBounds(930,400,130,55);
		jbtnRef.setBackground(new Color(0x8244AD));
		jbtnRef.addActionListener(event);
		add(jbtnRef);
		
		// 배경 설정
		JLabel jlblBG = new JLabel(new ImageIcon("C:/Users/user/git/HCYErp/HCYErp/src/image/HCYErp배경.png"));
		jlblBG.setBounds(0, 0, 1200, 700);
		add(jlblBG);
		hcyE.getList().add(this);
		
	}//constructor

	
	public JScrollPane getJspDocList() {
		return jspDocList;
	}

	public JButton getJbtnFileUpload() {
		return jbtnFileUpload;
	}

	public JButton getJbtnFileDownload() {
		return jbtnFileDownload;
	}

	public JButton getJbtnFileDelete() {
		return jbtnFileDelete;
	}

	public JButton getJbtnRef() {
		return jbtnRef;
	}

	public JButton getJbtnLogOut() {
		return jbtnLogOut;
	}

	public JLabel getJlblLogoTxt() {
		return jlblLogoTxt;
	}

	public HCYErp getHcyE() {
		return hcyE;
	}


	public JPanel getJpDoc() {
		return jpDoc;
	}


	public Map<Integer, JCheckBox> getJcheckBoxMap() {
		return jcheckBoxMap;
	}
	
}// class
