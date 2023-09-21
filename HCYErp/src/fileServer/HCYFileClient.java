package fileServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
		FileInputStream fis = null;
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

		} finally {
			if (os != null) {os.close();}//if
			if (fis != null) {fis.close();}//if
			if (writer != null) {writer.close();}//if
			if (socket != null) {socket.close();}//if
		} // finally
	}// uploadFile

	public boolean downloadFile(String filePath) throws UnknownHostException, IOException {
		Socket socket = null;
		DataOutputStream writer = null;
		FileOutputStream fos = null;
		InputStream is = null;
		boolean flag = false;
		try {
			socket = new Socket(serverIp, 36600);
			// 이름 및 확장자 보내기
			writer = new DataOutputStream(socket.getOutputStream());
			writer.writeUTF(filePath.substring(filePath.lastIndexOf(File.separator) + 1));
			writer.flush();

			fos = new FileOutputStream(filePath);
			is = socket.getInputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = 0;

			while ((bytesRead = is.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			} // while
			flag = true;
		} finally {
			if (is != null) {is.close();}//if
			if (fos != null) {fos.close();}//if
			if (writer != null) {writer.close();}//if
			if (socket != null) {socket.close();}//if
		} // finally
		return flag;
	}// uploadFile

	public boolean deleteFile(String fileName) throws UnknownHostException, IOException {
		PrintWriter writer = null;
		Socket socket = null;
		boolean flag = false;
		try {
			socket = new Socket(serverIp, 36700);
			// 이름 및 확장자 보내기
			writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(fileName);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			switch (reader.readLine()) {
			case "success":
				flag = true;
				break;
			}// switch
		} finally {
			if (writer != null) {writer.close();}//if
			if (socket != null) {socket.close();}//if
		} // finally
		return flag;
	}// deleteFile
	
	public void imageLoad() throws IOException {
		Socket socket = null;
		FileOutputStream fos = null;
		InputStream is = null;
		DataInputStream dis = null;
		try {
			socket = new Socket(serverIp, 36800);
			// 이름 및 확장자 보내기
			
			File dir = new File("C:/Users/user/HCYErpFile/images");
			// 폴더생성
			if (!dir.exists()) {
				dir.mkdirs();
			}//if
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			int length = dis.readInt();
			String fileName = "";
			close(socket, fos, is);
			for(int i = 0 ; i<length;i++) {
				socket = new Socket(serverIp, 36800);
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				fileName = dis.readUTF();
				fos = new FileOutputStream(dir.getAbsoluteFile()+File.separator+fileName);
				byte[] buffer = new byte[4096];
				int bytesRead = 0;
				while ((bytesRead = is.read(buffer)) != -1) {
					fos.write(buffer, 0, bytesRead);
				} // while
				close(socket, fos, is);
			}//for
		} finally {
			close(socket, fos, is);
		} // finally
	}//imageLoad

	private void close(Socket socket, FileOutputStream fos, InputStream is) throws IOException {
		if (is != null) {is.close();}//if
		if (fos != null) {fos.close();}//if
		if (socket != null) {socket.close();}//if
	}
}// class
