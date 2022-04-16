import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class POSProxy extends Service{

	Database db;
	static PrintWriter logger;

	public POSProxy(Database db) throws FileNotFoundException {
		this.db = db;
		logger = new PrintWriter("../data/log.txt");
	}

    //by Alex below
    
	//Logs user in, given id #, password, and Database
	public void login(int id, String hashedPass) throws FileNotFoundException {
		switch(db.login(id, hashedPass)){
			case 1:
				logger.write("Logged in " + id + "\n");
				break;
			case -1:
				logger.write("Error. Something went wrong\n");
			default:
				logger.write("User " + id + " already logged in\n");
				break;
		}
	}
	
	
	//Logs user out, given id #, password, and Database
	public void logout(int id, String hashedPass) throws FileNotFoundException {
		switch(db.logout(id, hashedPass)){
			case 1:
				logger.write("Logged out " + id + "\n");
				break;
			case -1:
				logger.write("Error. Something went wrong\n");
			default:
				logger.write("User " + id + " already logged out\n");
				break;
		}
	}
	
	//Deletes a user from the database given user id and Database
	public void deleteUser(int id) throws FileNotFoundException {
		switch (db.deleteUser(id)) {
			case 1:
				logger.write("User " + id + " successfully deleted.");
			case 0:
				logger.write("No such user to delete.");
			case -1:
				logger.write("You do not have permission to delete a user");
			default:
				logger.write("An error occurred.");
		}
	}
	
	//Deletes a user from the database given user id and Database
	public void addUser(String[] info) throws FileNotFoundException {
		switch (db.addUser(info)) {
			case 1:
				logger.write("User " + info[0] + " successfully added.");
			case 0:
				logger.write("There is no more room in the budget - fire someone and then try again.");
			case -1:
				logger.write("You do not have permission to add a user");
			default:
				logger.write("An error occurred.");
		}
	}


}
