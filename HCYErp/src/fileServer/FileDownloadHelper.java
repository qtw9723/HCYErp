package fileServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDownloadHelper extends Thread {

	private Socket client;
	private ServerSocket server;
	private OutputStream writeStream;
	private FileInputStream fisWriteStream;
	private BufferedReader reader;
	private HCYFileServer hcyfs;
	private String fileName;
	
	public FileDownloadHelper(ServerSocket server, HCYFileServer hcyfs) throws IOException {
		this.server=server;
	}//constructor

	private void download(ServerSocket server, BufferedReader reader) throws IOException {
		//파일 직렬화 해서 보내기
        byte[] buffer = new byte[4096];
        int bytesRead=0;
        while((bytesRead=fisWriteStream.read(buffer)) != -1) {
        	writeStream.write(buffer,0,bytesRead);
        }//while
        //닫기
        if(fisWriteStream!=null) { fisWriteStream.close();}
        if(writeStream!=null) { writeStream.close();}
        if(reader!=null) { reader.close();}
        if(client!=null) { client.close();}
        if(server!=null) { server.close();}
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에"+client.getInetAddress().getHostAddress()+"님이 \""+fileName+"\" 파일을 업로드 했습니다.\n");
	}//upload
	
	@Override
	public void run() {
		try {
			client = server.accept();
	        
			//버퍼리더 선언
			// 클라이언트로부터 파일 이름 및 확장자 정보 받기
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        fileName = reader.readLine();
	        
	        // 보내줄 폴더 경로 지정
	        StringBuilder folderPath = new StringBuilder();
	        folderPath.append("C:/Users/user/HCYErpFile").append(File.separator).append(fileName);
	        File folder = new File(folderPath.toString());
	        
	        //스트림 생성
	        fisWriteStream = new FileInputStream(folder);
	        writeStream = client.getOutputStream();
	        
	        download(server, reader);
		} catch (IOException e) {
		}//catch
	}//run
	
}//class
