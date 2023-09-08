package manageDoc;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class RefDeptDialog extends JDialog {
   
    private JCheckBox jcbManage;
    private JCheckBox jcbLaw;
    private JCheckBox jcbProduct;
    private JCheckBox jcbService;
    private JCheckBox jcbBusiness;
    private JCheckBox jcbExecutive;

    private JButton jbtnApproveRef;
    private JButton jbtnCancel;

    public RefDeptDialog() {
       setLayout(null);
       
       jcbManage=new JCheckBox("경영지원");
       jcbLaw=new JCheckBox("법무지원");
       jcbProduct=new JCheckBox("상품");
       jcbService=new JCheckBox("서비스");
       jcbBusiness=new JCheckBox("영업");
       jcbExecutive=new JCheckBox("임원");
       
       jbtnApproveRef=new JButton("권한 부여");
       jbtnCancel=new JButton("취소");
       
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
       
       jbtnApproveRef.setBounds(135, 115, 120, 50);
       jbtnCancel.setBounds(135, 180, 120, 50);
       
       RefDeptDialogEvt rdde=new RefDeptDialogEvt(this);
       
       jbtnApproveRef.addActionListener(rdde);
       jbtnCancel.addActionListener(rdde);
       
       setTitle("일단은 이겅가");
       setSize(300,400);
       setVisible(true);


       
    }

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

	public static void main(String[] args) {
        new RefDeptDialog();
    }
}
