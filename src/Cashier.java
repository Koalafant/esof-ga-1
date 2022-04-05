import java.util.ArrayList;

public class Cashier extends User{

    //by Mason
    protected Cashier(int id, String password, String name){
        this.id = id;
        setPass(password);
        this.name = name;
        permissions = new ArrayList<>();
        loggedIn = false;
    }

    @Override
    void addPermissions(User user, String perm) {
        if(this.permissions.contains("addPerm") || this.permissions.contains("all")){
            user.permissions.add(perm);
        }
    }
}
