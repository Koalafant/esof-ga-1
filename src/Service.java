public abstract class Service {
    User currentUser;
    float currentPrice;
    String[] currentOrder;
    String[] currentPerms;

    abstract void login(User user);

    void logout(User user){
        currentUser.loggedIn = false;
        currentUser = null;
    }

    boolean isLoggedIn(){
    if(currentUser != null){
        return true;
    }return false;
    }

    void display(){

    }
}
