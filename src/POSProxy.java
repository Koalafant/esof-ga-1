public class POSProxy extends Service{

    //by Mason
    boolean hasPermission(User user, String perm){
        if(user.getPerm().contains(perm)){
            return true;
        }
    return false;}

    @Override
    void login(User user) {
        if(hasPermission(user, "login") || hasPermission(user, "all")){
            PointOfService.getInstance().login(user);
        }
    }
    
    
    

//IGNORE THIS FOR NOW 
//  	
//  	public static Database getInstance(String file) throws FileNotFoundException {
//      }
//  	
//  	public void print() {
//
//  	}
//  	  	
//  	//Check if a user exists by user id
//  	public boolean findUser(int id) {
//
//  	}
//  	
//  	//Logs user in, given id # and password
//  	//-1 = bad pass, 0 = logged in already, 1 = log in
//  	public int login(int id, String hashedPass) {
//
//  	}
//  	
//  	//Logs user out, given id # and password
//  	//-1 = bad pass, 0 = logged out already, 1 = log out
//  	public int logout(int id, String hashedPass) {
//
//  	}
//  	
//  	//Name from id #
//  	public String getName(int id) {
//
//  	}
//  	
//  	
//  	//used for deleting, check perms somewhere else.
//  	private int getRowNum(int id) {
//
//  	}
//  	
//  	//Deletes a user from the database given their user id
//  	//true - deleted, false - no one to delete
//  	public boolean deleteUser(int id) throws FileNotFoundException {
//
//  	}
//  	
//  	//Adds a user to the database given all of their info in an array
//  	//of strings - should be a user object?
//  	// -1 = no space, 0 = already exists, 1 = added
//  	public int addUser(String[] info) throws FileNotFoundException {
//
//
//  }




}
