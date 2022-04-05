public class PointOfService extends Service {

    private static PointOfService pos;
    private PointOfService(){

    }

    //by Mason
    protected static PointOfService getInstance(){
        if(pos == null){
            pos = new PointOfService();
        }
        return pos;
    }

    @Override
    void login(User user) {
        user.loggedIn = true;
        currentUser = user;
    }
}
