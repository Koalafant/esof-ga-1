public class rootUser extends User {

    //by Mason
    protected rootUser(){
        super(0, "toor", "root");
        this.loggedIn = false;
        this.permissions.add("all");
    }
}
