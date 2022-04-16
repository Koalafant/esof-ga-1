import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Terminal {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException  {

//    	Scanner scanner = new Scanner(System.in);
//    	DBtesting(scanner);

        //Initialize database
        Database db = initialize();

        //Initialize proxy
        POSProxy prox = new POSProxy(db);

        //initalize GUI
        TerminalWindow tw = new TerminalWindow();
        tw.LoginWindow();

        }

    //by Mason
    public static int gatherID(Scanner scanner){
        System.out.print("User ID: ");
        try {
            return scanner.nextInt();
        }catch (InputMismatchException err){
            System.out.println("User ID is a number. Try again.");
            return -1;
        }
    }

    public static Database initialize(){
        try {
            return Database.getInstance("../data/database.csv");
        }catch(FileNotFoundException e){
            System.out.println("Abort, abort\nThrow the computer away\n3\n2\n1\nEverything is Broken!");
        }
    	//Arraylist of Users
        return null;
    }
    
    //by Alex
    public static String hashPass(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	BigInteger bigInt = new BigInteger(md.digest(pass.getBytes("UTF-8"))); 
    	String hashedPass = bigInt.toString(16);
    	return hashedPass;
    }

    public static boolean authUser(int id, String hash){

    return false;}

    //TODO - move log in and out controllers for the database into the POSProxy class.
    //Messy. Demonstrates basic database functionality. Not much error checking.
    public static void DBtesting(Scanner scanner) throws FileNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {    	
//    	
//		Database DB = Database.getInstance("../data/database.csv");
//		DB.print();
//		System.out.println();
//       
//        //Login
//        System.out.print("Enter user id to log in: ");
//        int userID = scanner.nextInt();
//        scanner.nextLine(); //dump \n
//        if(DB.userExists(userID)) {
//        	System.out.print("\nEnter password: ");
//        	String pass = hashPass(scanner.nextLine());
//        	if(DB.login(userID, pass) == 1) {
//        		System.out.println("\nWelcome, " + DB.getName(userID) + "! Successfully logged in.");
//        	} else if(DB.login(userID, pass) == 0){
//        		System.out.println(DB.getName(userID) + ", you are already logged in!");
//        	} else if(DB.login(userID, pass) == -1){
//        		System.out.println("Incorrect username/password.");
//        	} else {
//        		System.out.println("Someone is already logged in!");
//        	}
//        } else {
//        	System.out.println("Invalid user ID.");
//        }
//        System.out.println();
//        DB.print();
//        
//        //Logout
//        System.out.print("Enter user id to log out: ");
//        userID = scanner.nextInt();
//        scanner.nextLine(); //dump \n
//        if(DB.userExists(userID)) {
//        	System.out.print("\nEnter password: ");
//        	String pass = hashPass(scanner.nextLine());
//        	if(DB.logout(userID, pass) == 1) {
//        		System.out.println(DB.getName(userID) + ", you have successfully logged out. You were logged in for " + DB.getTime(userID) + " seconds.");
//        	} else if(DB.logout(userID, pass) == 0){
//        		System.out.println(DB.getName(userID) + ", you are already logged out!");
//        	} else if(DB.logout(userID, pass) == -1){
//        		System.out.println("Incorrect password.");
//        	} else {
//        		System.out.println("No one is logged in!");
//        	}
//        } else {
//        	System.out.println("Invalid user ID.");
//        }
//        
//        System.out.println();
//        DB.print();
//
//        
//        //Delete user
//        System.out.print("Enter user id to delete: "); //11
//        userID = scanner.nextInt();
//        scanner.nextLine(); //dump \n
//        if(DB.deleteUser(userID) == 1) {
//        	System.out.println("User successfully deleted.");
//        } else if(DB.deleteUser(userID) == 0) {
//        	System.out.println("No such user to delete.");
//        } else {
//        	System.out.println("No perms");
//        }
//        
//        System.out.println();
//        DB.print();
//        
//        
//        //Add user
//        System.out.println("\nAdding a user...");
//        String[] newUserInfo = {"Sam",hashPass("aPASS"),"cash manage"};
//        if(DB.addUser(newUserInfo) == 1) {
//        	System.out.println("User successfully added.");
//        } else if(DB.deleteUser(userID) == 0) {
//        	System.out.println("No space.");
//        } else {
//        	System.out.println("No perms");
//        }
//		
//		
//      //Add permission
//      System.out.print("Enter user id to give a permission to: "); 
//      int userID = scanner.nextInt();
//      scanner.nextLine(); //dump \n
//      System.out.print("Enter permission number to give: "); 
//      int perm = scanner.nextInt();
//      scanner.nextLine(); //dump \n
//      if(DB.addPerm(userID, perm)) {
//      	System.out.println("Permission successfully added.");
//      } else {
//    	  System.out.println("Uh Oh :(");
//      }
//      
//    System.out.println();
//    DB.print();
//      
//      //Remove permission
//      System.out.print("Enter user id to remove a permission from: "); 
//      userID = scanner.nextInt();
//      scanner.nextLine(); //dump \n
//      System.out.print("Enter permission number to remove: "); 
//      perm = scanner.nextInt();
//      scanner.nextLine(); //dump \n
//      if(DB.removePerm(userID, perm)) {
//      	System.out.println("Permission successfully removed. (even if they didn't have that permission)");
//      } else {
//    	  System.out.println("Uh Oh!");
//      }
//        
//        System.out.println();
//        DB.print();
//        
//        System.out.println("-------- DATABASE TEST END --------\n");
    }
    
    
}

