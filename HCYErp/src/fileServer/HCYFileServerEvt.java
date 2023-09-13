package fileServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class HCYFileServerEvt extends WindowAdapter implements ActionListener, Runnable {

	private HCYFileServer hcyfs;
	private ServerSocket uploadServer;
	private ServerSocket DownloadServer;
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
			} // catch
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
	
	public void openServer() throws IOException {
		if (serverThread != null) {
			JOptionPane.showMessageDialog(hcyfs, "서버가 동작중입니다.");
		} else {
			serverThread = new Thread(this);
			serverThread.start();
		} // else
	}// startServer

	public void closeServer() throws IOException {
		if (server != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			server.close();
			hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에 서버가 종료되었습니다.\n");
		} // if
	}// startServer

	@Override
	public void run() {
		try {
			uploadServer = new ServerSocket(36500);
			DownloadServer = new ServerSocket(36600);
			FileDownloadHelper fdlh = null;
			FileUploadHelper fulh = null;
			try {
				fulh = new FileUploadHelper(uploadServer);
				fdlh = new FileDownloadHelper(DownloadServer);
				fulh.start();
				fdlh.start();
			} catch (IOException e1) {
				System.out.println("Helper에서 예외");
				e1.printStackTrace();
			} // try
		} catch (IOException e1) {
			System.out.println("서버종료");
		} // try
	}// run

}// class
