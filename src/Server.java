import java.net.*;
import java.io.*;
public class Server {
	public static void main(String[] args) throws IOException{
		ServerSocket s = new ServerSocket(6789);
		Socket s1 = s.accept();
		
		OutputStream s1out = s1.getOutputStream();
		DataOutputStream dos = new DataOutputStream(s1out);
		DataInputStream dIn = new DataInputStream(s1.getInputStream());
		String[] messages = {"I'm bored, wanna chat :D","How are you today!","Thats great to hear!","I am the last element in the array. My messages will now repeat"};
		
		
		//Send the first message to the user.
		dos.writeUTF(messages[0]);
		int i = 1;
		
		//Loops through each element in the messages array and sends it to the user
		serverloop:
		while(true){
			//Prints user string for debugging
			String clientString = dIn.readUTF();
			System.out.println(clientString);
			
			//Exit or print message
			switch(clientString){
				case "exit":
					dos.writeUTF("Goodbye!");
					break serverloop;
				default: 
					dos.writeUTF(messages[i]);
					i++;
					break;
			}
			
			//If end of messages, reloop
			if(i==messages.length)
				i=0;
		}
		
		//Close all running objects
		dIn.close();
		dos.close();
		s1out.close();
		s1.close();
	}
}
