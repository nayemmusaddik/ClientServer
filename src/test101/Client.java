package test101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private static final String IP = "127.0.0.1";
	private static final int PORT = 9090;
	
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(IP, PORT);
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("Please Enter Username: ");
		BufferedReader usernameReader = new BufferedReader(new InputStreamReader(System.in));
		String username = usernameReader.readLine();
		writer.println(username);

		if (reader.readLine().equals(username)) {
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println("Please Enter Your Message: ");
				String comand = keyboardReader.readLine();


				if (comand.contains("quit")) {
					break; }
				writer.println(comand);

				String response = reader.readLine();
				System.out.println(response);
			}
			socket.close();
		}
	}
}
