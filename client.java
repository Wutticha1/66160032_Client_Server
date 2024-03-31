package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
	public static void main(String[] args) {
		Socket socket = null;
		InputStreamReader input_sreader = null;
		OutputStreamWriter output_swriter = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			socket = new Socket("localhost",1234);
			
			input_sreader = new InputStreamReader(socket.getInputStream());
			output_swriter = new OutputStreamWriter(socket.getOutputStream());
			
			bufferedReader = new BufferedReader(input_sreader);
			bufferedWriter = new BufferedWriter(output_swriter);
			
			Scanner input = new Scanner(System.in);
			
			while(true) {
				String mesTosend = input.nextLine();
				
				bufferedWriter.write(mesTosend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				System.out.println("Server: " + bufferedReader.readLine());
				
				if(mesTosend.equalsIgnoreCase("BYE")) {
					break;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if(socket != null) 
					socket.close();
				
				if(input_sreader != null)
					input_sreader.close();
				
				if(output_swriter != null)
					output_swriter.close();
				
				if(bufferedReader != null)
					bufferedReader.close();
				
				if(bufferedWriter != null)
					bufferedWriter.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
