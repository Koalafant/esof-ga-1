import java.util.ArrayList;

//By Mason
public abstract class Service {
    User currentUser;


    void logout(User user){
        currentUser.loggedIn = false;
        currentUser = null;
    }

    boolean isLoggedIn(){
    if(currentUser != null){
        return true;
    }return false;
    }

}
