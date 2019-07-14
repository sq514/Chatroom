import java.net.*;
import java.io.*;
import java.util.Scanner;
public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
 
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
 
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        GreetClient client = new GreetClient();
        client.startConnection("192.168.1.4", 6666);
        Scanner in = new Scanner(System.in);
        while(true) {
	        System.out.println("enter message:");
	        String message=in.nextLine();
	        if(message.equals("quit")) {
	        	client.stopConnection();
	        	System.out.println("you have quit the chatroom");
	        	break;
	        }
	        String response = client.sendMessage(message);
	        System.out.println("from server:"+response);
        }
    }
}
