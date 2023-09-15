package fileServer;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HCYFileClient {
	private static HCYFileClient hcyFC;
	private String serverIp = "192.168.10.145";

	private HCYFileClient() {
	}// constructor

	public static HCYFileClient getInstance() {
		if (hcyFC == null) {
			hcyFC = new HCYFileClient();
		} // if
		return hcyFC;
	}// getInstance

	public void uploadFile(File file) throws UnknownHostException, IOException {
		Socket socket = null;
		PrintWriter writer = null;
		FileInputStream  fis = null;
		OutputStream os = null;
		try {
		socket = new Socket(serverIp, 36500);
		// 이름 및 확장자 보내기
		String filePath = file.getAbsolutePath();
		String fileNameWithExtension = filePath.substring(filePath.lastIndexOf("\\") + 1); // 파일 이름 및 확장자 설정
		writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(fileNameWithExtension);

		fis = new FileInputStream(file);
		os = socket.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = 0;

		while ((bytesRead = fis.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		} // while

		}finally {
		os.close();
		fis.close();
		writer.close();
		socket.close();
		}//finally
	}// uploadFile

	public boolean downloadFile(String filePath) throws UnknownHostException, IOException {
		Socket socket = null;
		PrintWriter writer = null;
		FileOutputStream fos = null;
		InputStream is = null;
		
		boolean flag = false;
		try {
			socket = new Socket(serverIp, 36600);
			// 이름 및 확장자 보내기
			writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(filePath.substring(filePath.lastIndexOf("/") + 1));
			System.out.println("----00--");

			fos = new FileOutputStream(filePath);
			is = socket.getInputStream();
			System.out.println("----11--");
			byte[] buffer = new byte[4096];
			int bytesRead = 0;

			while ((bytesRead = is.read(buffer)) != -1) {
				System.out.println("----22--");
				fos.write(buffer, 0, bytesRead);
			} // while
			flag = true;
		} finally {
			is.close();
			fos.close();
			writer.close();
			socket.close();
		} // finally
		return flag;
	}// uploadFile

	public boolean deleteFile(String fileName) throws UnknownHostException, IOException {
		boolean flag = false;
		Socket socket = new Socket(serverIp, 36700);
		// 이름 및 확장자 보내기
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println(fileName);

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		switch (reader.readLine()) {
		case "success":
			flag = true;
			break;
		}// switch

		writer.close();
		socket.close();

		return flag;
	}// deleteFile
}// class
