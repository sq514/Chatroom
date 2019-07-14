import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner input_scanner;
    
    public Server(Scanner in) {
    	input_scanner = in;
    }
    public void start(int port) throws IOException {
        System.out.println("start waiting for client");
        serverSocket = new ServerSocket(6666);
        InetAddress localhost = InetAddress.getLocalHost(); 
        String ip_address = (localhost.getHostAddress()).trim();
        System.out.println("you have created a chatroom, your Ip address is "+ip_address);
        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String connectedUser;

        
        
        connectedUser = in.readLine();
        System.out.println(connectedUser+" has connected to the chat room ");

        


    }
    
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
    	Scanner in = new Scanner(System.in);
        Server server=new Server(in);
        server.start(6666);
        
        while(true) {
        	System.out.println("Enter your message");
            String message = in.nextLine();
            String response = server.sendMessage(message);
            System.out.println("You have received a message" + response);
        }
        
    }
}
