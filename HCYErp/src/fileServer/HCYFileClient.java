package fileServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HCYFileClient {
	private static HCYFileClient hcyFC;
	
	private HCYFileClient() {
	}//constructor
	
	public static HCYFileClient getInstance() {
		if(hcyFC == null) {
			hcyFC = new HCYFileClient();
		}//if
		return hcyFC;
	}//getInstance
	
	public void uploadFile(File file) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.10.146",36500);
		//이름 및 확장자 보내기
		String filePath = file.getAbsolutePath();
		String fileNameWithExtension = filePath.substring(filePath.lastIndexOf("\\")+1); // 파일 이름 및 확장자 설정
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(fileNameWithExtension);
        
        FileInputStream fis = new FileInputStream(file);
        OutputStream os = socket.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        
        while ((bytesRead = fis.read(buffer)) != -1) {
        	os.write(buffer, 0, bytesRead);
        }//while

        os.close();
        fis.close();
        writer.close();
        socket.close();
	}//uploadFile
	
	public void downloadFile(File file) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.10.146",36500);
		//이름 및 확장자 보내기
		String filePath = file.getAbsolutePath();
		String fileNameWithExtension = filePath.substring(filePath.lastIndexOf("\\")+1); // 파일 이름 및 확장자 설정
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(fileNameWithExtension);
        
        FileInputStream fis = new FileInputStream(file);
        OutputStream os = socket.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        
        while ((bytesRead = fis.read(buffer)) != -1) {
        	os.write(buffer, 0, bytesRead);
        }//while

        os.close();
        fis.close();
        writer.close();
        socket.close();
	}//uploadFile
}//class
