package fileServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HCYFileServerEvt extends WindowAdapter implements ActionListener, Runnable {

	private HCYFileServer hcyfs;
	private ServerSocket uploadServer;
	private ServerSocket downloadServer;
	private ServerSocket deleteServer;
	private Thread serverThread;

	public HCYFileServerEvt(HCYFileServer hcyfs) {
		this.hcyfs = hcyfs;
	}// constructor

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == hcyfs.getJbtnOpenServer()) {
			try {
				openServer();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}//catch
		} // if
		if (ae.getSource() == hcyfs.getJbtnCloseServer()) {
			try {
				closeServer();
			} catch (IOException e) {
				e.printStackTrace();
			} // catch
		} // if
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent we) {
		try {
			closeServer();
		} catch (IOException e) {
			e.printStackTrace();
		} // catch
		hcyfs.dispose();
	}// windowClosing

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(JFrame.ABORT);
	}// windowClosed
	
	public void openServer() throws IOException, SQLException {
		// 폴더가 존재하지 않으면 폴더 생성
		File folder = new File("C:/Users/user/HCYErpFile");
		if (!folder.exists()) {
			if (folder.mkdirs()) {
				hcyfs.getJtaConnectList().append("C:/Users/user/HCYErpFile"+"에 폴더가 생성되었습니다.\n");
			} else {
				hcyfs.getJtaConnectList().append("폴더 생성에 실패했습니다.\n다시 시도하세요!\n");
				return;
			}//else
		}//if
		ServerDAO.getInstance().synchronize(folder.listFiles());
		
		if (serverThread != null) {
			JOptionPane.showMessageDialog(hcyfs, "서버가 동작중입니다.");
			return;
			}//if
		serverThread = new Thread(this);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		serverThread.start();
		hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에 서버가 실행되었습니다.\n");
		JOptionPane.showMessageDialog(hcyfs, "서버를 실행합니다.");
	}// startServer

	public void closeServer() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (uploadServer != null) {uploadServer.close();}
		if (downloadServer != null) {downloadServer.close();} 
		hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에 서버가 종료되었습니다.\n");
	}// startServer

	@Override
	public void run() {
		try {
			uploadServer = new ServerSocket(36500);
			downloadServer = new ServerSocket(36600);
			deleteServer = new ServerSocket(36700);
			FileDownloadHelper fdlh = null;
			FileUploadHelper fulh = null;
			FileDeleteHelper fdh = null;
			try {
				fulh = new FileUploadHelper(uploadServer,hcyfs);
				fdlh = new FileDownloadHelper(downloadServer,hcyfs);
				fdh = new FileDeleteHelper(deleteServer, hcyfs);
				fulh.start();
				fdlh.start();
				fdh.start();
			} catch (IOException e1) {
				System.out.println("Helper에서 예외");
				e1.printStackTrace();
			} // try
		} catch (IOException e1) {
			System.out.println("서버종료");
		} // catch
	}// run

}// class
