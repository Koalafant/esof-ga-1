public abstract class Service {
    User currentUser;
    float currentPrice;
    String[] currentOrder;
    String[] currentPerms;

    abstract void login(User user);

    void logout(User user){

    }

    boolean isLoggedIn(){

    return false;}

    void display(){

    }
}
