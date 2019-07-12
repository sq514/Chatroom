import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        System.out.println("start waiting for client");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting;
        Scanner inputScanner = new Scanner(System.in);
        while(true){
            greeting = in.readLine();
            System.out.println(greeting);
            System.out.println("Enter your message");
            String message = inputScanner.nextLine();
            out.println(message);
        }



    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
        Server server=new Server();
        server.start(6666);
    }
}
