import java.util.ArrayList;

//By Mason
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

    void display(ArrayList<String> perm){
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String s : perm) {
            builder.append(i + ": ");
            builder.append(s);
            builder.append("\n");
        }
        System.out.println(builder);
    }
}
