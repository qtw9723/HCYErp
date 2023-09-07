package manageDoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton; 
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import java.util.Arrays;

public class RefDeptDialog extends JDialog {
	private JCheckBox jcbDeptList;
	private JScrollPane jspDeptList;
	private JButton jbtnApproveRef;
	private JButton jbtnCancel;
	private JList<JCheckBox> jlDeptList;
	private DefaultListModel<JCheckBox> listmodel;

	public RefDeptDialog() {
//		listmodel = new DefaultListModel<JCheckBox>();
//		listmodel.addElement("경영지원");
//		listmodel.addElement("법무지원");
//		listmodel.addElement("상품");
//		listmodel.addElement("영업");
//		listmodel.addElement("임원");
		String[] str = { "경영지원", "법무지원", "상품", "영업", "임원" };
//		jlDeptList = new JList<JCheckBox>(listmodel);
//		jcbDeptList=new JCheckBox[](); 
		for (int i = 0; i < 5; i++) {
			jcbDeptList = new JCheckBox(str[i]);
//			listmodel.addElement(jcbDeptList);
		}

		
//		jspDeptList=new JScrollPane(jlDeptList);

		jbtnApproveRef = new JButton("권한 부여");
		jbtnCancel = new JButton("취소");

		setLayout(null);
//		int num = 0;
//		for (int i = 0; i < 5; i++) {
//			if (i > 0) {
//				jcbDeptList.setBounds(0, num, 100, 30);
//			} else {
//				jcbDeptList.setBounds(0, 0, 100, 30);
//			}
//		}
		int num = 0;
		for (int i = 0; i < 100; i+=20) {
				jcbDeptList.setBounds(0, num+i, 100, 30);
		}

//		jspDeptList.setBounds(0, 0, 500, 400);
		jbtnApproveRef.setBounds(200, 500, 100, 40);
		jbtnCancel.setBounds(400, 500, 100, 40);

		add(jbtnApproveRef);
		add(jbtnCancel);
		add(jcbDeptList);

		setSize(600, 600);
		setVisible(true);
	}

	public JScrollPane getJspDeptList() {
		return jspDeptList;
	}

	public JButton getJbtnApproveRef() {
		return jbtnApproveRef;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

//	public JList<String> getJlDeptList() {
//		return jlDeptList;
//	}

	public static void main(String[] args) {
		new RefDeptDialog();
	}
}
