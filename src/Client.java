import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String args[]) throws IOException {
		Scanner input = new Scanner(System.in);

		// Open your connection to a server, at port 6789
		Socket s1 = new Socket(InetAddress.getLocalHost(),6789);
		// Get an input file handle from the socket and read the input
		InputStream s1In = s1.getInputStream();
		DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
		
		// Initializes objects needed to send data to the server
		DataInputStream dis = new DataInputStream(s1.getInputStream());

		int i = 0;
		while(true){
				String st = new String(dis.readUTF());
				System.out.println(st);
				String userInput = input.nextLine();
				//Sends data to server

				dout.writeUTF(userInput);
				dout.flush();
				//If user enters exit, then exit loop
				if(userInput.equals("exit"))
					break;
		}
		
		// When done, just close the connection and exit
		dis.close();
		dout.close();
		s1In.close();
		s1.close();
	}
}
