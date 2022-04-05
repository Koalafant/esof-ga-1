public class POSProxy extends Service{

    //by Mason
    boolean hasPermission(User user, String perm){
        if(user.getPerm().contains(perm)){
            return true;
        }
    return false;}

    @Override
    void login(User user) {
        if(hasPermission(user, "login") || hasPermission(user, "all")){
            PointOfService.getInstance().login(user);
        }
    }


}
