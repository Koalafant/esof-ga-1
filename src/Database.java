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
	private static String[][] database = new String[10][6]; //max 10 users currently
	private static String filePath = "";
	
	private Database(String file) throws FileNotFoundException {
		filePath = file;
				
		String line = ""; // line of the file
		String[] lineData = new String[6]; // holds the data from an individual line		
		
		//File to DB
		Scanner input = new Scanner(new File(filePath));
		line = input.nextLine(); // clear header line
		
		while (input.hasNextLine()) {

			// gets the line and splits it into the array
			line = input.nextLine();
			lineData = line.split(",");
			int index = Integer.parseInt(lineData[0]);
			database[index][0] = lineData[0];
			
			for(int i = 1; i < 6; i++) {
				database[index][i] = lineData[i];
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
		
	//Update database file to current state of database object
	private void update() throws FileNotFoundException {
		PrintStream fout = new PrintStream(new FileOutputStream(filePath));
		fout.print("id,num,name,pass,perms,loggedin\n");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 6; j++) {
				fout.print(database[i][j] + ",");
			}
			fout.print("\n");
		}
		fout.close();
	}
	
	
	//Check if a user exists by user id
	public boolean findUser(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) == id) {
				return true;
			}
		}
		return false;
	}
	
	//Logs user in, given id # and password
	//-1 = bad pass, 0 = logged in already, 1 = log in
	public int login(int id, String hashedPass) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			if(hashedPass.equals(database[i][3])) {
				if(database[i][5].equals("false")) {
					database[i][5] = "true";
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
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) { continue; }
			if(hashedPass.equals(database[i][3])) {
				if(database[i][5].equals("true")) {
					database[i][5] = "false";
					return 1;
				} else { //already logged out
					return 0;
				}
			}
		}
		return -1;
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
	
	
	//used for deleting, check perms somewhere else.
	private int getRowNum(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) != id) {
				continue;
			}
			return i;
		}
		return -1;
	}
	
	//Deletes a user from the database given their user id
	//true - deleted, false - no one to delete
	public boolean deleteUser(int id) throws FileNotFoundException {
		int row = getRowNum(id);
		if(row != -1) { //user exists
			for(int i = 1; i < 6; i++) {
				database[row][i] = "-1";
			}
			instance.update();
			return true;
		}
		instance.update();
		return false;
	}
	
	//Adds a user to the database given all of their info in an array
	//of strings - should be a user object?
	// -1 = no space, 0 = already exists, 1 = added
	public int addUser(String[] info) throws FileNotFoundException {
		int row = getRowNum(Integer.parseInt(info[0]));
		if(row == -1) { //user doesn't already exist
			for(int i = 0; i < 10; i++) { //find an empty row to insert into
				if(database[i][1] != "-1") {continue;}
				
				database[i][0] = i + ""; //set row num
				
				for(int j = 0; j < 5; j++) { //fill rest of info
					database[i][j+1] = info[j]; 
				}
				instance.update();
				return 1;
			}
			instance.update();
			return -1;
		}
		instance.update();
		return 0;
	}

}


