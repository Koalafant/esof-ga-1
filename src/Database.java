//By Alex
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Database extends Service{
	private static Database instance; // Represents database
	private static String[][] database = new String[10][8]; //max 10 users
	private static String filePath = ""; // Filepath to CSV of database
	private static int highestID = 0; // Highest user ID in use
	protected static int currentUserID = -1; // User ID currently logged in
	
	private Database(String file) throws FileNotFoundException {
		filePath = file;
				
		String line = ""; // line of the file
		String[] lineData = new String[8]; // data from an individual line		
		
		// Take data from CSV and put it in Database object
		Scanner input = new Scanner(new File(filePath));
		line = input.nextLine(); // clear header line
		
		while (input.hasNextLine()) {

			// get the line and split it into the array
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
	
	
    /**
     * Get instance of the Database
     * @param file - path to file of data for the database
     * @return current instance of the database
     * @throws FileNotFoundException
     */
	protected static Database getInstance(String file) throws FileNotFoundException {
        if (instance == null) {
            instance = new Database(file);
        }
        return instance;
    }

	
    /**
     * Get instance of the Database
     * @return current instance of the database
     * @throws FileNotFoundException
     */
	protected static Database getInstance(){
		if(instance != null){
			return instance;
		}
		else{
			return null;
		}
	}
	
	
    /**
     * Print formatted Database data
     * @return UNUSED
     */
	private void print() {
		System.out.println(Arrays.deepToString(database).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
	
    /**
     * Check if a user exists by user ID
     * @param id - user to check
     * @return boolean - true if user exists in the database
     */
	public static boolean userExists(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) == id) {
				return true;
			}
		}
		return false;
	}
	
	
    /**
     * Get a user's name from their ID
     * @param id - user to get the name of
     * @return user's name; null if user does not exist
     */
	private String getName(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			return database[i][2];
		}
		return null;
	}
	
	
    /**
     * Get the time a given user was logged in for
     * @param id - user to get time of
     * @return the number of seconds the user was logged in for;
     * 		   null if user does not exist
     */
	private String getTime(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			return database[i][7];
		}
		return null;
	}
	
	
    /**
     * Get permissions for a given user
     * @param if - user to get permissions of
     * @return string of permissions the user has; null if user does not exist
     */
	protected String getPerms(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			return database[i][4];
		}
		return null;
	}
	
	
    /**
     * Get whether someone is currently logged in
     * @return boolean - true if someone is logged in
     */
	private static boolean loggedIn() {
		for(int i = 0; i < 10; i++) {
			if(database[i][5].equals("true")) {
				return true;
			}
		}
		return false;
	}
		

	/**
     * Get row in the database of a given user. Used when deleting users.
     * @param id - user id to get the row of
     * @return row number; -1 if user does not exist
     */
	private int getRowNum(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) {
				continue;
			}
			return i;
		}
		return -1;
	}
	
	
    /**
     * Update the database CSV to the current state of the database object.
     * @return UNUSED
     */
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
	
	
    /**
     * Logs a user into the system
     * @param id - user to log in
     * @param hashedPass - hashed user's password
     * @return 1 - successfully logged in; 0 - already logged in; -1 incorrect password;
     * 		   -2 someone else is already logged in
     * @throws FileNotFoundException
     */
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
	
	
    /**
     * Logs a user out of the system
     * @param id - user to log out
     * @param hashedPass - hashed user's password
     * @return 1 - successfully logged out; 0 - already logged out; -1 incorrect password;
     * 		   -2 no one is logged in
     * @throws FileNotFoundException
     */
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
	

    /**
     * Deletes a user from the database
     * @param id - user to be deleted
     * @return boolean - success on deletion
     * @throws FileNotFoundException
     */
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
	
	
    /**
     * Adds a user to the database
     * @param info - name, hashed password, permissions
     * @return boolean - success on addition
     * @throws FileNotFoundException
     */
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

	
    /**
     * Add a permission to a given user
     * @param id - user to add permission to
     * @param perm - permission to add 
     * @return boolean - success addition
     * @throws FileNotFoundException
     */
	protected boolean addPerm(int id, int perm) throws FileNotFoundException {
		for(int i = 0; i < 10; i++) { 
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			
			database[i][4] += perm+"";
			
			instance.update();
			return true;
		}
		return false;
	}
	
	
    /**
     * Remove a permission from a given user
     * @param id - user to remove permission from
     * @param perm - permission to remove 
     * @return boolean - success removal
     * @throws FileNotFoundException
     */
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