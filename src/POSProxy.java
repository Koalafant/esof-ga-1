import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * By Mason and Alex
 * Proxy class that acts as middleman to protected/private database.
 * Ensures users have permission to call what methods they're trying to call.
 */
public class POSProxy extends Service {

	static PrintWriter logger;

	/**
	 * Constructor creates a logging writer to log GUI backend in terminal
	 * @throws FileNotFoundException - file used by database to track user data
	 */
	public POSProxy() throws FileNotFoundException {
		try {
			logger = new PrintWriter("data/log.txt");
		}catch(FileNotFoundException e){
			logger = new PrintWriter("../data/log.txt");
		}
	}

	/**
	 * Checks wether given id user has given perm given string i.e. "3"
	 * @param id user id. Unique to user
	 * @param perm String permission. String of an int. I.e. "357".
	 * @return true or false.
	 */
	public static boolean checkPerm(int id, String perm) {
		String[] perms = Database.getInstance().getPerms(id).split("");
		for (String p : perms) {
			if (p.equals(perm)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Logs a user in to the database.
	 * @param id user id.
	 * @param hashedPass hashed password so password is not echoed.
	 * @return return code for terminal window. See TerminalWindow.Java
	 * @throws FileNotFoundException - file used by database.
	 */
	public int login(int id, String hashedPass) throws FileNotFoundException {
		switch (Database.login(id, hashedPass)) {
			case 1:
				logger.write("Logged in " + id + "\n");
				logger.flush();
				return 1;
			case -1:
				logger.write("Error. Something went wrong\n");
				logger.flush();

				return -1;
			default:
				logger.write("User " + id + " already logged in\n");
				logger.flush();
				return 0;
		}
	}


	/**
	 * Logs current user out of system.
	 * @param id user id.
	 * @param hashedPass hashed password so password is not echoed.
	 * @return return code. See TerminalWindow.Java
	 * @throws FileNotFoundException - file used by database.
	 */
	public int logout(int id, String hashedPass) throws FileNotFoundException {
		switch (Database.logout(id, hashedPass)) {
			case 1:
				logger.write("Logged out " + id + "\n");
				logger.flush();
				return 1;
			case -1:
				logger.write("Error. Something went wrong\n");
				logger.flush();
				return -1;
			default:
				logger.write("User " + id + " already logged out\n");
				logger.flush();
				return 0;
		}
	}

	/**
	 * Deletes a user from the database.
	 * @param id user id to be deleted.
	 * @throws FileNotFoundException - file used by database.
	 */
	public void deleteUser(int id) throws FileNotFoundException {
		if (checkPerm(Database.getInstance().currentUserID, "4")) {
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

	/**
	 * adds a user to the database.
	 * @param id user id.
	 * @param info other stuff like permissions and type of user.
	 * @throws FileNotFoundException - file used by database.
	 */
	public void addUser(int id, String[] info) throws FileNotFoundException {
		if (checkPerm(Database.getInstance().currentUserID, "3")) {
			boolean success = Database.getInstance().addUser(info);
			if (success) {
				logger.write("User " + info[0] + " successfully added.\n");
			} else {
				logger.write("An error occurred.\n");
			}
		} else {
			logger.write("Invalid permissions\n");
		}
		logger.flush();
	}

	/**
	 * adds a permission to the given user.
	 * @param id user id to be granted permission.
	 * @param perm permission to be granted.
	 * @throws FileNotFoundException - file used by database.
	 */
	public void addPerm(int id, int perm) throws FileNotFoundException {
		if(checkPerm(Database.getInstance().currentUserID, "5")){
			if(Database.getInstance().addPerm(id, perm)){
				logger.write("Permission " + Permission.Permissions.values()[perm] + " added to user " + id + "\n");
			}else{
				logger.write("An error occured.\n");
			}
		}else{
			logger.write("Invalid permissions\n");
		}
		logger.flush();
	}

	/**
	 * removes a permission from the given user.
	 * @param id user id to be used.
	 * @param perm permission to be removed.
	 * @throws FileNotFoundException - file used by database.
	 */
	public void removePerm(int id, int perm) throws FileNotFoundException {
		if(checkPerm(Database.getInstance().currentUserID, "5")){
			if(Database.getInstance().removePerm(id, perm)){
				logger.write("Permission " + Permission.Permissions.values()[perm] + " deleted from User " + id + "\n");
			}else{
				logger.write("An error occured.\n");
			}
		}else{
			logger.write("Invalid permissions\n");
		}
		logger.flush();
	}

}
