package fileServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileDownloadHelper extends Thread {

	private Socket client;
	private ServerSocket server;
	private DataInputStream disReadStream;
	private DataOutputStream dosWriteStream;
	
	public FileDownloadHelper(ServerSocket server) throws IOException {
		this.server=server;
		client = server.accept();
		disReadStream = new DataInputStream(client.getInputStream());
		dosWriteStream = new DataOutputStream(client.getOutputStream());
	}//constructor
	
	@Override
	public void run() {
	}//run
	
}//class
