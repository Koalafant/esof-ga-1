public class rootUser extends User {

    //by Mason
    protected rootUser(){
        id = 0;
        name = "toor";
        setPass("root");
        this.loggedIn = false;
        this.permissions.add("all");
    }

    @Override
    void addPermissions(User user, String perm) {
        user.getPerm().add(perm);
    }
}
