package manageDoc;

import java.awt.Color;
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
		setLayout(null);

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

		try {
			List<String> fileNameList = new ArrayList<String>();
			for (Entry<Integer, JCheckBox> entry : md.getJcheckBoxMap().entrySet()) {
				if (entry.getValue().isSelected()) {
					fileNameList.add(entry.getValue().getText());
				} // if
			} // for
			String userDept = "";
			for (String fileName : fileNameList) {
				String tempDept = ManageDocDAO.getInstance().selectDept(fileName);
				if (!userDept.equals(tempDept)) {
					new Exception("다른 부서의 문서 선택");
				} // if
			} // for
			for (JCheckBox jcb : jcbList) {
				if (jcb.getText().equals(userDept)) {
					jcb.setSelected(true);
					jcb.setEnabled(false);
				} // if
			} // for
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(md, "여러 문서에 대한 열람 권한 부여는 같은 부서에서 제작한 문서끼리만 가능합니다.");
			dispose();
		} // catch

		jbtnApproveRef = new JButton("권한 부여");
		jbtnCancel = new JButton("취소");

		add(jcbManage);
		add(jcbLaw);
		add(jcbProduct);
		add(jcbService);
		add(jcbBusiness);
		add(jcbExecutive);
		add(jbtnApproveRef);
		add(jbtnCancel);

		jcbManage.setBounds(10, 10, 100, 60);
		jcbLaw.setBounds(10, 60, 100, 60);
		jcbProduct.setBounds(10, 110, 100, 60);
		jcbService.setBounds(10, 160, 100, 60);
		jcbBusiness.setBounds(10, 210, 100, 60);
		jcbExecutive.setBounds(10, 260, 100, 60);

		jbtnApproveRef.setBounds(135, 190, 120, 50);
		jbtnApproveRef.setBackground(new Color(0x8244AD));
		jbtnCancel.setBounds(135, 255, 120, 50);
		jbtnCancel.setBackground(new Color(0x5E5E5E));

		RefDeptDialogEvt rdde = new RefDeptDialogEvt(this);

		jbtnApproveRef.addActionListener(rdde);
		jbtnCancel.addActionListener(rdde);

		setTitle("일단은 이겅가");
		setBounds(md.getHcyE().getX() + 400, md.getHcyE().getY() + 200, 300, 400);
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
