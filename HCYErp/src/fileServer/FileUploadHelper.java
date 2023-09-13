package fileServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadHelper extends Thread {

	private Socket client;
	private ServerSocket server;
	private ObjectInputStream oisReadStream;
	private ObjectOutputStream oosWriteStream;
	
	public FileUploadHelper(ServerSocket server) throws IOException {
		this.server=server;
		client = server.accept();
		
		// 저장할 폴더 경로 지정
        String folderPath = "C:/Users/user/HCYErpFile";

        // 폴더가 존재하지 않으면 폴더 생성
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("폴더가 생성되었습니다.");
            } else {
                System.out.println("폴더 생성에 실패했습니다.");
                return;
            }//else
        }//if
        
		oisReadStream = new ObjectInputStream(client.getInputStream());
		oisReadStream.readObject()
		
		
		
		
		oosWriteStream = new FileOutputStream();
		
		
	}//constructor
	
	@Override
	public void run() {
	}//run
	
}//class
