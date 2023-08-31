package fileServer;

import java.net.Socket;

public class FileHelper {

	private Socket client;
	
	public FileHelper(Socket client) {
		this.client=client;
	}
	
}
