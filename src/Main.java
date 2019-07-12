import java.io.*;
import java.util.Scanner;

public class Main {
	int x = 0;
	public static void main(String[] args) {
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
		
		
	}
}
