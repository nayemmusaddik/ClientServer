package test101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
	
	private Socket client;
	private BufferedReader reader;
	private PrintWriter writer;
	String username;
	
	public ClientHandler(Socket client, String username) throws IOException{
		this.client = client;
		this.username=username;
		this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		this.writer = new PrintWriter(client.getOutputStream(),true);
		writer.println(username);
	}

	@Override
	public void run() {
		try {
			while(true) {
				String request = reader.readLine();
				System.out.println("#["+username+" LOG]:RECEIVED MSG BODY: "+request);
				System.out.println("> Reply Sent to: "+ username);

				writer.println("#[SERVER]: "+request);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}finally {
			
			try {
				reader.close();
				writer.close();
				client.close();
				System.out.println("Connection Closed");
			} catch (IOException e) {
			}
		}
	}


}
