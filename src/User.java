import java.util.ArrayList;

public class User {

    public int id;
    public String name;
    private String password;
    protected ArrayList<String> permissions;
    boolean loggedIn;

    //by Mason
    protected User(int id, String password, String name){
        this.id = id;
        this.password = password;
        this.name = name;
        permissions = new ArrayList<>();
        loggedIn = false;
    }

    ArrayList<String> getPerm(){
        return permissions;
    }

}
