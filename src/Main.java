import java.io.*;
import java.util.Scanner;

public class Main {
	int x = 0;
	static String my_username;
	public static void main(String[] args) throws IOException {
		System.out.println("Please select log in or register, 1 for log in; 2 for register");
		
		Scanner in = new Scanner(System.in);
		int chosenState=0;
		String userName;
		String password;
		while(true) {
			System.out.println("Please select");
			chosenState = in.nextInt(); 
			in.nextLine();
			if(chosenState==1){
				logIn(in);
				break;
				
			}else if(chosenState==2){
				register(in);				
				break;
			}
		}
		while(true) {
			System.out.println("Please select: 1 for create new chatroom; 2 for enter chatroom");
			chosenState = in.nextInt(); 
			in.nextLine();
			if(chosenState==1){
				createChatroom(in);
				break;
				
			}else if(chosenState==2){
				enterChatroom(in);				
				break;
				}
		}
	}
	
	private static void logIn(Scanner in) {
		System.out.println("Please log in");
		File file = new File("./src/userInformation.txt"); 
		boolean loginSuccessed = false;
		while(true) {
			System.out.println("enter username");
			String userName=in.nextLine();
			System.out.println("enter password");
			String password=in.nextLine();
			  
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String st;
				while((st = br.readLine()) != null) {
					String[] after_split = st.split(" ");
					if(after_split[0].equals(userName)&& after_split[1].equals(password)) {
						System.out.println("log in successfully");
						loginSuccessed = true;
						break;
					}
				
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
			
			if(loginSuccessed) {
				my_username = userName;
				break;
			}
			else {
				System.out.println("username and password does not match, please try again");
			}
			
			
		}

		
	}
	
	private static void register(Scanner in) {
		System.out.println("Please register");
		System.out.println("enter username");
		String userName=in.nextLine();
		//need validation
		System.out.println("enter password");
		String password=in.nextLine();	
		String inputData = userName + " "+password ;
		System.out.println(inputData);
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("./src/userInformation.txt", true));
			out.append(inputData+"\n");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		my_username = userName;
		
		
	}
	private static void createChatroom(Scanner in) throws IOException {
        Server server=new Server(in);
        server.start(6666);
        
        while(true) {
        	System.out.println("Enter your message");
            String message = in.nextLine();
	        if(message.equals("quit")) {
	        	server.stop();
	        	System.out.println("you have shut down the server");
	        	break;
	        }
            String response = server.sendMessage(message);
            System.out.println("You have received a message" + response);
        }
	}
	
	private static void enterChatroom(Scanner in) throws IOException {
		GreetClient client = new GreetClient();
		System.out.println("Please enter the chatroom ip");
		String chatroom_ip = in.nextLine(); 
        client.startConnection(chatroom_ip, 6666);
        String response = client.sendMessage(my_username);
        System.out.println("from server:"+response);
        while(true) {
	        System.out.println("enter message:");
	        String message=in.nextLine();
	        if(message.equals("quit")) {
	        	client.stopConnection();
	        	System.out.println("you have quit the chatroom");
	        	break;
	        }
	        response = client.sendMessage(message);
	        System.out.println("from server:"+response);
        }
	}
}
