package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		InputStreamReader input_sreader = null;
		OutputStreamWriter output_swriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		ServerSocket serverSocket = null;
		
		serverSocket = new ServerSocket(1234);
		
		while(true) {
			try {
				
				socket = serverSocket.accept();
				
				input_sreader = new InputStreamReader(socket.getInputStream());
				output_swriter = new OutputStreamWriter(socket.getOutputStream());
				
				bufferedReader = new BufferedReader(input_sreader);
				bufferedWriter = new BufferedWriter(output_swriter);
				
				while(true) {
					
					String mesFromClient = bufferedReader.readLine();
					
					System.out.println("Client: " + mesFromClient);
					
					bufferedWriter.write("Received.");
					bufferedWriter.newLine();
					bufferedWriter.flush();
					
					if(mesFromClient.equalsIgnoreCase("BYE")) {
						break;
					}
				}
				socket.close();
				input_sreader.close();
				output_swriter.close();
				bufferedReader.close();
				bufferedWriter.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
