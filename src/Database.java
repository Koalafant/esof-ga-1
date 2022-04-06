//I'll fix this more later
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Database {
	
	static String[][] database = new String[10][6]; //max 10 users currently
	static String filePath = "";
	
	public Database(String file) throws FileNotFoundException {
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
	
	public void print() {
		System.out.println(Arrays.deepToString(database).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

	}
		
	//update database file to current state of database object
	public void update() throws FileNotFoundException {
		PrintStream fout = new PrintStream(new FileOutputStream(filePath));
		fout.print("id,num,name,pass,perms,loggedin\n");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 6; j++) {
				fout.print(database[i][j] + ",");
			}
			fout.print("\n");
		}
	}
	
	//Check if a user exists
	public boolean findUser(int id) {
		for(int i = 0; i < 10; i++) {
			if(Integer.parseInt(database[i][1]) == id) {
				return true;
			}
		}
		return false;
	}
	
	
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
	
	
	//true - deleted, false - no one to delete
	public boolean deleteUser(int id) {
		int row = getRowNum(id);
		if(row != -1) { //user exists
			for(int i = 1; i < 6; i++) {
				database[row][i] = "-1";
			}
			return true;
		}
		return false;
	}
	
	
	//true - added, false - already exists
	public boolean addUser(String[] info) {
		int row = getRowNum(Integer.parseInt(info[0]));
		if(row == -1) { //user doesn't already exist
			for(int i = 0; i < 10; i++) { //find an empty row to insert into
				if(database[i][1] != "-1") {continue;}
				
				database[i][0] = i + ""; //set row num
				
				for(int j = 0; j < 5; j++) { //fill rest of info
					database[i][j+1] = info[j]; 
				}
				return true;
			}
			return false;
		}
		return false;

	}

}


