package test101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	private static final int PORT = 9090;

	public static void main(String[] args) throws IOException{
		System.out.println("test101.Server Started");
		ServerSocket request = new ServerSocket(PORT);
		System.out.println("Waiting for Clients...");

		try {
			while(true) {
				String username;
				Socket client = request.accept();
				username= String.valueOf(client.getPort());
				System.out.println("#[LOG]: Connection Established with: "+username);
				BufferedReader usernameReader= new BufferedReader(new InputStreamReader(client.getInputStream()));
				username=usernameReader.readLine();
				System.out.println("> Set Username: "+username);
				ClientHandler clientThread = new ClientHandler(client,username);
				new Thread(clientThread).start();
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}finally {
			request.close();
		}
	}
}
