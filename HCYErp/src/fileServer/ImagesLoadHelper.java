package fileServer;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImagesLoadHelper extends Thread {

	private FileInputStream fisWriteStream;
	private OutputStream outStream;
	private DataOutputStream dos;
	private HCYFileServer hcyfs;
	private ServerSocket server;
	private Socket client;

	public ImagesLoadHelper(ServerSocket server, HCYFileServer hcyfs) throws IOException {
		this.server = server;
		this.hcyfs = hcyfs;
		// 스트림 선언
	}// constructor

	private void ImageLoad() throws IOException {
		// 파일 받아서 쓰기
		byte[] buffer = new byte[4096];
		int bytesRead = 0;
		try {
			while ((bytesRead = fisWriteStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			} // while
		} finally {
			// 닫기
			if (fisWriteStream != null) {fisWriteStream.close();}
			if (outStream != null) {outStream.close();}
			if (client != null) {client.close();}
		} // finally
	}// upload

	@Override
	public void run() {
		try {
			while (true) {
				client = server.accept();
				outStream = client.getOutputStream();
				// 저장할 폴더 경로 지정
				String folderPath = "C:/Users/user/HCYErpFile/images";
				File[] fileArr = new File(folderPath).listFiles();
				dos = new DataOutputStream(outStream);
				dos.writeInt(fileArr.length);
				dos.flush();
				for (File file : fileArr) {
					String fullPath=file.getAbsolutePath();
					dos.writeUTF(fullPath.substring(fullPath.lastIndexOf(File.separator)+1));
					dos.flush();
					fisWriteStream = new FileInputStream(fullPath);
					ImageLoad();
				} // for
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에" + client.getInetAddress().getHostAddress()
						+ "님이 서버에 접속하셨습니다.\n");
			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} // catch
	}// run
}// class
