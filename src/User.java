import java.util.ArrayList;


//By Mason
public abstract class User {

    public int id;
    public String name;
    private String password;
    protected ArrayList<String> permissions;
    boolean loggedIn;

    ArrayList<String> getPerm(){
        return permissions;
    }

    abstract void addPermissions(User user, String perm);

    protected void setPass(String pass){
        password = pass;
    }
}
