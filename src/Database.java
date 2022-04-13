//I'll fix this more later
//By Alex
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Database {
	private static Database instance;
	private static String[][] database = new String[10][8]; //max 10 users currently
	private static String filePath = "";
	private static int highestID = 0;
	// private static int currentUser = 0;
	
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
		}
		
		//initialize highestID
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) > highestID) {
				highestID = Integer.parseInt(database[i][1]);
			}
		}
		
			
	}
	
	public static Database getInstance(String file) throws FileNotFoundException {
        if (instance == null) {
            instance = new Database(file);
        }
        return instance;
    }
	
	public void print() {
		System.out.println(Arrays.deepToString(database).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

	}
	
	//Check if a user exists by user id
	public boolean userExists(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) == id) {
				return true;
			}
		}
		return false;
	}
	
	//Name from id #
	public String getName(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) {
				continue;
			}
			return database[i][2];
		}
		return null;
	}
	
	//Get time clocked in for (seconds)
	public String getTime(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) {
				continue;
			}
			return database[i][7];
		}
		return null;
	}
	
	
	//Used for creating users (Returns current highest user ID)
	private boolean hasChangePerms(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) {
				continue;
			}
			return database[i][4].contains("manage");
		}
		return false;
	}
	
	//Returns whether someone is currently logged in
	private boolean loggedIn() {
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
	//-1 = bad pass, 0 = logged in already, 1 = log in
	public int login(int id, String hashedPass) {
		if(loggedIn()) { return -2; }
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			if(hashedPass.equals(database[i][3])) {
				if(database[i][5].equals("false")) {
					database[i][5] = "true";
					database[i][6] = java.time.Instant.now().getEpochSecond() + "";
					return 1;
				} else { //already logged in
					return 0;
				}
			}
		}
		return -1;
	}
	
	//Logs user out, given id # and password
	//-1 = bad pass, 0 = logged out already, 1 = log out
	public int logout(int id, String hashedPass) {
		if(!loggedIn()) { return -2; }
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			if(hashedPass.equals(database[i][3])) {
				if(database[i][5].equals("true")) {
					database[i][5] = "false";
					database[i][7] = (java.time.Instant.now().getEpochSecond() - Long.parseLong(database[i][6])) + "";
					database[i][6] = "0";
					return 1;
				} else { //already logged out
					return 0;
				}
			}
		}
		return -1;
	}
	
	//Deletes a user from the database given their user id
	//1 - succeeded, 0 - no such user, -1 no perms
	public boolean deleteUser(int id) throws FileNotFoundException {
		// if(!hasChangePerms(id)) { return -1; }
		int row = getRowNum(id);
		if(row != -1) { //user exists
			for(int i = 1; i < 8; i++) {
				database[row][i] = "-1";
			}
			instance.update();
			return true;
		}
		instance.update();
		return false;
	}
	
	//Adds a user to the database given their info in an array
	//of strings - should be a user object?
	// false - no space, true - successful
	public boolean addUser(String[] info) throws FileNotFoundException {
		// if(!hasChangePerms(id)) { return -1; }
			for(int i = 0; i < 10; i++) { //find an empty row to insert into
				if(database[i][1] != "-1") {continue;}
				
				String[] newUser = {i + "", ++highestID + "", info[0], info[1], info[2], "false", "0", "0"};
				
				for(int j = 0; j < 8; j++) { //add new user to database
					database[i][j] = newUser[j]; 
				}				
				
				instance.update();
				return true;
			}
			instance.update();
			return false;
	}

}


