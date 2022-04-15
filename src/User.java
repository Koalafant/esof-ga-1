import java.util.ArrayList;
import java.util.HashMap;


//By Mason
public class User {

    HashMap<Permission.Permissions, Boolean> perms;
    public int id;
    public String name;
    private String password;
    boolean loggedIn;

    public User(int id, String name, String hash) {
        this.id = id;
        this.name = name;
        password = hash;
        perms = Permission.initialize();
        loggedIn = false;
        }

    public boolean hasPerm(User user, Permission.Permissions perm){
        if(user.perms.get(perm)){
            return true;
        }
        return false;
    }
    }
