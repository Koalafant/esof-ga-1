public class POSProxy extends Service{

    boolean hasPermission(User user, String perm){

    return false;}

    @Override
    void login(User user) {
        if(hasPermission(user, "login")){
            PointOfService.getInstance().login(user);
        }
    }


}
