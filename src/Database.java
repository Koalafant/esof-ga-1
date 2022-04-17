//By Alex
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Database extends Service{
	private static Database instance;
	private static String[][] database = new String[10][8]; //max 10 users currently
	private static String filePath = "";
	private static int highestID = 0;
	protected static int currentUserID = -1;
	
	private Database(String file) throws FileNotFoundException {
		filePath = file;
				
		String line = ""; // line of the file
		String[] lineData = new String[8]; // holds the data from an individual line		
		
		//File to DB
		Scanner input = new Scanner(new File(filePath));
		line = input.nextLine(); // clear header line
		
		while (input.hasNextLine()) {

			// gets the line and splits it into the array
			line = input.nextLine();
			lineData = line.split(",");
			int index = Integer.parseInt(lineData[0]);
			database[index][0] = lineData[0];
			
			for(int i = 1; i < 8; i++) {
				database[index][i] = lineData[i];
			}
			
			//Make sure everyone is marked as logged off
			database[index][5] = "false";
		}
		
		//initialize highestID
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) > highestID) {
				highestID = Integer.parseInt(database[i][1]);
			}
		}
	}
	
	protected static Database getInstance(String file) throws FileNotFoundException {
        if (instance == null) {
            instance = new Database(file);
        }
        return instance;
    }

	protected static Database getInstance(){
		if(instance != null){
			return instance;
		}
		else{
			return null;
		}
	}

	private static Boolean findUser(String name) {
		return false;
	}

	private void print() {
		System.out.println(Arrays.deepToString(database).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
	//Check if a user exists by user id
	public static boolean userExists(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) == id) {
				return true;
			}
		}
		return false;
	}
	
	//Name from id #
	private String getName(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			return database[i][2];
		}
		return null;
	}
	
	//Get time clocked in for (seconds)
	private String getTime(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			return database[i][7];
		}
		return null;
	}
	
	//Get permissions for a given user
	protected String getPerms(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			return database[i][4];
		}
		return null;
	}
	
	
	//Returns whether someone is currently logged in
	private static boolean loggedIn() {
		for(int i = 0; i < 10; i++) {
			if(database[i][5].equals("true")) {
				return true;
			}
		}
		return false;
	}
		
	//Used for deleting users
	private int getRowNum(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) {
				continue;
			}
			return i;
		}
		return -1;
	}
	
	//Update database file to current state of database object
	//Called automatically after adding / deleting a user
	private void update() throws FileNotFoundException {
		PrintStream fout = new PrintStream(new FileOutputStream(filePath));
		fout.print("id,num,name,pass,perms,loggedin\n");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 8; j++) {
				fout.print(database[i][j] + ",");
			}
			fout.print("\n");
		}
		fout.close();
	}
	
	//Logs user in, given id # and password
	//-2 someone is logged in, -1 = bad pass, 0 = logged in already, 1 = log in
	protected static int login(int id, String hashedPass) throws FileNotFoundException {
		if(loggedIn()) { return -2; }
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			if(hashedPass.equals(database[i][3])) {
				if(database[i][5].equals("false")) {
					database[i][5] = "true";
					database[i][6] = java.time.Instant.now().getEpochSecond() + "";
					instance.update();
					currentUserID = Integer.parseInt(database[i][1]);
					return 1;
				} else { //already logged in
					return 0;
				}
			}
		}
		return -1;
	}
	
	//Logs user out, given id # and password
	//-2 = no one is logged in, -1 = bad pass, 0 = logged out already, 1 = log out
	protected static int logout(int id, String hashedPass) throws FileNotFoundException {
		if(!loggedIn()) { return -2; }
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			if(hashedPass.equals(database[i][3])) {
				if(database[i][5].equals("true")) {
					database[i][5] = "false";
					database[i][7] = (java.time.Instant.now().getEpochSecond() - Long.parseLong(database[i][6])) + "";
					database[i][6] = "0";
					instance.update();
					currentUserID = -1;
					return 1;
				} else { //already logged out
					return 0;
				}
			}
		}
		return -1;
	}
	
	//Deletes a user from the database given their user id
	//returns true on success
	protected boolean deleteUser(int id) throws FileNotFoundException {
		int row = getRowNum(id);
		if(row != -1) { //user exists
			for(int i = 1; i < 8; i++) {
				database[row][i] = "-1";
			}
			instance.update();
			return true;
		}
		return false;
	}
	
	//Adds a user to the database given their info in an array
	//of strings. Returns true on success
	protected boolean addUser(String[] info) throws FileNotFoundException {
		for(int i = 0; i < 10; i++) { //find an empty row to insert into
			if(database[i][1] != "-1") {continue;}
			
			String[] newUser = {i + "", ++highestID + "", info[0], info[1], info[2], "false", "0", "0"};
			
			for(int j = 0; j < 8; j++) { //add new user to database
				database[i][j] = newUser[j]; 
			}				
			
			instance.update();
			return true;
		}
		return false;
	}

	
	//Adds a permission to a given user. Returns true on success
	protected boolean addPerm(int id, int perm) throws FileNotFoundException {
		for(int i = 0; i < 10; i++) { 
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			
			database[i][4] += perm+"";
			
			instance.update();
			return true;
		}
		return false;
	}
	
	//Removes a permission from a given user. Returns true on success
	protected boolean removePerm(int id, int perm) throws FileNotFoundException {
		for(int i = 0; i < 10; i++) { 
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			
			database[i][4] = database[i][4].replace(perm+"", "");
			
			instance.update();
			return true;
		}
		return false;
	}
}



