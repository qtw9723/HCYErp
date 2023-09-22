package fileServer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class HCYFileServer extends JFrame {
	private JTextArea jtaConnectList;
	private JScrollPane jspJtaConnectListClient;
	private JButton jbtnOpenServer;
	private JButton jbtnCloseServer;
	private JButton jbtnVersionUpdate;

	
	
	public HCYFileServer() {
		super(":::::::::::::::파일서버:::::::::::::::::::::::");
		
		jtaConnectList=new JTextArea();
		jspJtaConnectListClient=new JScrollPane(jtaConnectList);
		jtaConnectList.setEditable(false);
		
		jspJtaConnectListClient.setBorder(new TitledBorder("접속자목록"));
		
		jbtnOpenServer=new JButton("서버실행");
		jbtnCloseServer=new JButton("종료");
		jbtnVersionUpdate = new JButton("버전 업데이트");
		
		JPanel jpSouth=new JPanel();
		
		jpSouth.add( jbtnOpenServer );
		jpSouth.add( jbtnCloseServer );
		jpSouth.add( jbtnVersionUpdate );
		
		add("Center", jspJtaConnectListClient);
		add("South",jpSouth);
		HCYFileServerEvt event = new HCYFileServerEvt(this);
		
		jbtnCloseServer.addActionListener(event);
		jbtnOpenServer.addActionListener(event);
		jbtnVersionUpdate.addActionListener(event);
		addWindowListener(event);
		
		setBounds(100, 100, 400, 500);
		setVisible(true);
	}//constructor

	public static void main(String[] args) {
		new HCYFileServer();
	}//main
	
	
	public JTextArea getJtaConnectList() {
		return jtaConnectList;
	}
	public JButton getJbtnOpenServer() {
		return jbtnOpenServer;
	}
	public JButton getJbtnCloseServer() {
		return jbtnCloseServer;
	}
	public JScrollPane getJspJtaConnectListClient() {
		return jspJtaConnectListClient;
	}
	public JButton getJbtnVersionUpdate() {
		return jbtnVersionUpdate;
	}
}//class
