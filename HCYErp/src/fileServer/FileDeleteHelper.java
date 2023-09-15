package fileServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDeleteHelper extends Thread {

	private Socket client;
	private ServerSocket server;
	private OutputStream writeStream;
	private FileInputStream fisWriteStream;
	private BufferedReader reader;
	private HCYFileServer hcyfs;
	private String fileName;
	
	public FileDeleteHelper(ServerSocket server, HCYFileServer hcyfs) throws IOException {
		this.server=server;
	}//constructor

	private void delete(BufferedReader reader) throws IOException {
		try {
		// 삭제할 파일 경로 지정
        StringBuilder filePath = new StringBuilder();
        filePath.append("C:/Users/user/HCYErpFile").append(File.separator).append(fileName);
        File file = new File(filePath.toString());
        
        //파일삭제
        PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
        if(file.delete()) {
	        writer.println(DeleteFalg.success);
	        hcyfs.getJtaConnectList().append(client.getInetAddress().getHostAddress()+"님이 \""+fileName+"\" 파일을 삭제 했습니다.\n");
        }else {
        	writer.println(DeleteFalg.fail);
        	hcyfs.getJtaConnectList().append(client.getInetAddress().getHostAddress()+"님이 \""+fileName+"\" 파일 삭제에 실패했습니다.\n");
        }//else
		}finally {
        //닫기
        if(fisWriteStream!=null) { fisWriteStream.close();}
        if(writeStream!=null) { writeStream.close();}
        if(reader!=null) { reader.close();}
        if(client!=null) { client.close();}
		}//finally
        
		//로그
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에"+client.getInetAddress().getHostAddress()+"님이 \""+fileName+"\" 파일을 업로드 했습니다.\n");
	}//upload
	
	@Override
	public void run() {
		try {
			while(true) {
			client = server.accept();
	        
			//버퍼리더 선언
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        fileName = reader.readLine();
	        
	        delete(reader);
			}//while
		} catch (IOException e) {
		}//catch
	}//run
	
}//class
