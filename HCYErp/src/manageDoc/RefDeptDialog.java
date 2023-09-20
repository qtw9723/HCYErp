package manageDoc;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class RefDeptDialog extends JDialog {

	private JCheckBox jcbManage;
	private JCheckBox jcbLaw;
	private JCheckBox jcbProduct;
	private JCheckBox jcbService;
	private JCheckBox jcbBusiness;
	private JCheckBox jcbExecutive;
	private List<JCheckBox> jcbList;

	private JButton jbtnApproveRef;
	private JButton jbtnCancel;

	private ManageDoc md;

	public RefDeptDialog(ManageDoc md) {
		this.md = md;
		
		//다이얼로그 배경색
		getContentPane().setBackground(new Color(255,245,245));
		
		setLayout(null);

		//체크박스
		jcbList = new ArrayList<JCheckBox>();

		jcbManage = new JCheckBox("경영지원");
		jcbList.add(jcbManage);
		jcbLaw = new JCheckBox("법무지원");
		jcbList.add(jcbLaw);
		jcbProduct = new JCheckBox("상품");
		jcbList.add(jcbProduct);
		jcbService = new JCheckBox("서비스");
		jcbList.add(jcbService);
		jcbBusiness = new JCheckBox("영업");
		jcbList.add(jcbBusiness);

		// 체크박스 배경: ArrayList에 있는 모든 JCheckBox의 배경색 변경
        Color newBackgroundColor = new Color(255,245,245); 
        for (JCheckBox checkBox : jcbList) {
            checkBox.setBackground(newBackgroundColor);
        }//end for
		
		try {
			List<String> fileNameList = new ArrayList<String>();
			for (Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
				if (entry.getValue().isSelected()) {
					fileNameList.add(entry.getValue().getText());
				} // if
			} // for
			String Dept = "";
			String comp = "";
			for (String fileName : fileNameList) {
				comp = ManageDocDAO.getInstance().selectDept(fileNameList.get(0));
				Dept = ManageDocDAO.getInstance().selectDept(fileName);
				if (!comp.equals(Dept)) {
					throw new Exception("다른 부서의 문서 선택");
				} // if
			} // for
			for (JCheckBox jcb : jcbList) {
				if (jcb.getText().equals(Dept)) {
					jcb.setSelected(true);
					jcb.setEnabled(false);
				} // if
			} // for
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(md, "여러 문서에 대한 열람 권한 부여는 같은 부서에서 제작한 문서끼리만 가능합니다.");
			dispose();
			return;
		} // catch

		//버튼
		jbtnApproveRef = new JButton("권한 부여");
		jbtnCancel = new JButton("취소");

		//체크박스 폰트
		Font jcbFont = new Font("맑은 고딕", Font.BOLD, 17);
		jcbManage.setFont(jcbFont);
		jcbLaw.setFont(jcbFont);
		jcbProduct.setFont(jcbFont);
		jcbService.setFont(jcbFont);
		jcbBusiness.setFont(jcbFont);
		
		add(jcbManage);
		add(jcbLaw);
		add(jcbProduct);
		add(jcbService);
		add(jcbBusiness);
		add(jbtnApproveRef);
		add(jbtnCancel);

		jcbManage.setBounds(30, 20, 100, 60);
		jcbLaw.setBounds(30, 70, 100, 60);
		jcbProduct.setBounds(30, 120, 100, 60);
		jcbService.setBounds(30, 170, 100, 60);
		jcbBusiness.setBounds(30, 220, 100, 60);
		

		//버튼 폰트
		Font jbtnFont = new Font("맑은 고딕", Font.BOLD, 15);
		
		jbtnApproveRef.setBounds(20, 300, 120, 40);
		jbtnApproveRef.setFont(jbtnFont);
		jbtnApproveRef.setBackground(new Color(0x6D47B0));
		jbtnApproveRef.setForeground(Color.white);
		jbtnCancel.setBounds(150, 300, 120, 40);
		jbtnCancel.setFont(jbtnFont);
		jbtnCancel.setBackground(new Color(0x5E5E5E));
		jbtnCancel.setForeground(Color.white);;

		RefDeptDialogEvt rdde = new RefDeptDialogEvt(this);

		jbtnApproveRef.addActionListener(rdde);
		jbtnCancel.addActionListener(rdde);

		setTitle("부서 참조");
		setBounds(md.getHcyE().getX() + 400, md.getHcyE().getY() + 200, 305, 400);
		setVisible(true);

	}// constructor

	public JCheckBox getJcbManage() {
		return jcbManage;
	}

	public JCheckBox getJcbLaw() {
		return jcbLaw;
	}

	public JCheckBox getJcbProduct() {
		return jcbProduct;
	}

	public JCheckBox getJcbService() {
		return jcbService;
	}

	public JCheckBox getJcbBusiness() {
		return jcbBusiness;
	}

	public JCheckBox getJcbExecutive() {
		return jcbExecutive;
	}

	public JButton getJbtnApproveRef() {
		return jbtnApproveRef;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public List<JCheckBox> getJcbList() {
		return jcbList;
	}

	public ManageDoc getMd() {
		return md;
	}

}
