import java.io.FileNotFoundException;

public class POSProxy extends Service{



    
    //by Alex below
    
	//Logs user in, given id #, password, and Database
	public String login(int id, String hashedPass, Database DB) {
		String name = DB.getName(id);
		switch (DB.login(id, hashedPass)) {
			case 1:
				return "Welcome, " + name + "! Successfully logged in.";
			case 0:
				return name + ", you are already logged in!";
			case -1:
				return "Invalid Id # / Password";
			case -2:
				return "Someone is already logged in";
			default:
				return "An error occurred.";
		}
	}
	
	
	//Logs user out, given id #, password, and Database
	public String logOut(int id, String hashedPass, Database DB) {
		String name = DB.getName(id);
		String time = DB.getTime(id);
		switch (DB.logout(id, hashedPass)) {
			case 1:
				return name + ", you have successfully logged out. \nYou were logged in for " + time;
			case 0:
				return name + ", you are already logged out!";
			case -1:
				return "Invalid Id # / Password";
			case -2:
				return "No one is currently logged in";
			default:
				return "An error occurred.";
		}
	}
	
	//Deletes a user from the database given user id and Database
	public String deleteUser(int id, Database DB) throws FileNotFoundException {
		switch (DB.deleteUser(id)) {
			case 1:
				return "User successfully deleted.";
			case 0:
				return "No such user to delete.";
			case -1:
				return "You do not have permission to delete a user";
			default:
				return "An error occurred.";
		}
	}
	
	//Deletes a user from the database given user id and Database
	public String addUser(String[] info, Database DB) throws FileNotFoundException {
		switch (DB.addUser(info)) {
			case 1:
				return "User successfully added.";
			case 0:
				return "There is no more room in the budget - fire someone and then try again.";
			case -1:
				return "You do not have permission to add a user";
			default:
				return "An error occurred.";
		}
	}

	@Override
	void login(User user) {

	}
}
