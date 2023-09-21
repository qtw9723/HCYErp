package fileServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImagesLoadHelper extends Thread {

	private Socket client;
	private ServerSocket server;
	private InputStream readStream;
	private FileOutputStream fosWriteStream;
	private HCYFileServer hcyfs;
	private String fileName;
	
	public ImagesLoadHelper(ServerSocket server, HCYFileServer hcyfs) throws IOException {
		this.server=server;
		this.hcyfs = hcyfs;
        //스트림 선언
	}//constructor

	private void ImageLoad() throws IOException {
		//파일 받아서 쓰기
        byte[] buffer = new byte[4096];
        int bytesRead=0;
        
        try {
        while((bytesRead=readStream.read(buffer)) != -1) {
        	fosWriteStream.write(buffer,0,bytesRead);
        }//while
        } finally {
        //닫기
        if(fosWriteStream!=null) { fosWriteStream.close();}
        if(readStream!=null) { readStream.close();}
        if(client!=null) { client.close();}
        }//finally
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		hcyfs.getJtaConnectList().append(sdf.format(new Date()) + "에"+client.getInetAddress().getHostAddress()+"님이 \""+fileName+"\" 파일을 업로드 했습니다.\n");
	}//upload
	
	@Override
	public void run() {
		try {
			while (true) {
				client = server.accept();
				readStream = client.getInputStream();
				
				// 저장할 폴더 경로 지정
				String folderPath = "C:/Users/user/HCYErpFile/images";
				File[] file = new File(folderPath).listFiles();
				
				fosWriteStream = new FileOutputStream(folderPath+File.separator+fileName);
				ImageLoad();
			}//while
		} catch (IOException e) {
			e.printStackTrace();
		}//catch
	}//run
	
}//class
