import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class POSProxy extends Service {

	static PrintWriter logger;

	public POSProxy() throws FileNotFoundException {
		try {
			logger = new PrintWriter("data/log.txt");
		}catch(FileNotFoundException e){
			logger = new PrintWriter("../data/log.txt");
		}
	}

	//checks for given permission given int
	public static boolean checkPerm(int id, String perm) {
		String[] perms = Database.getInstance().getPerms(id).split(" ");
		for (String p : perms) {
			if (p.equals(perm)) {
				return true;
			}
		}
		return false;
	}

	//by Alex and Mason
	//Logs user in, given id #, password, and Database
	public void login(int id, String hashedPass) throws FileNotFoundException {
		switch (Database.login(id, hashedPass)) {
			case 1:
				logger.write("Logged in " + id + "\n");
				break;
			case -1:
				logger.write("Error. Something went wrong\n");
			default:
				logger.write("User " + id + " already logged in\n");
				break;
		}
		logger.flush();
	}


	//Logs user out, given id #, password, and Database
	public void logout(int id, String hashedPass) throws FileNotFoundException {
		switch (Database.logout(id, hashedPass)) {
			case 1:
				logger.write("Logged out " + id + "\n");
				break;
			case -1:
				logger.write("Error. Something went wrong\n");
			default:
				logger.write("User " + id + " already logged out\n");
				break;
		}
		logger.flush();
	}

	//Deletes a user from the database given user id and Database
	public void deleteUser(int id) throws FileNotFoundException {
		if (checkPerm(id, "4")) {
			boolean success = Database.getInstance().deleteUser(id);
			if (success) {
				logger.write("User " + id + " successfully deleted.");
			} else {
				logger.write("An error occurred.");
			}
		} else {
			logger.write("Invalid permissions\n");
		}
		logger.flush();
	}

	//Deletes a user from the database given user id and Database
	public void addUser(int id, String[] info) throws FileNotFoundException {
		if (checkPerm(id, "3")) {
			boolean success = Database.getInstance().addUser(info);
			if (success) {
				logger.write("User " + info[0] + " successfully added.");
			} else {
				logger.write("An error occurred.");
			}
		} else {
			logger.write("Invalid permissions\n");
		}
		logger.flush();
	}
}
