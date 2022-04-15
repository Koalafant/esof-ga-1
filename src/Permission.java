import java.util.HashMap;

// By Cole
public class Permission {
    //Initialize fields
    String permissionName;
    String description;
    static HashMap<Permissions, Boolean> userPerms = new HashMap<Permissions, Boolean>();

    //Create enum of set in stone permissions
    public enum Permissions{
        ADD_ITEM,
        REMOVE_ITEM,
        MODIFY_ITEM,
        ADD_USER,
        REMOVE_USER,
        MODIFY_USER,
        VIEW_RECEIPT
    }

    //Init a permission with a name, description, and a HashMap of permissions and bools
    protected Permission(HashMap<Permissions, Boolean> userPerms, String permName, String permDesc){
        permissionName = permName;
        description = permDesc;
        this.userPerms = userPerms;
    }

    public static HashMap<Permissions, Boolean> initialize(){
        HashMap<Permissions, Boolean> ret = new HashMap<>();
        ret.put(Permissions.ADD_ITEM, false);
        ret.put(Permissions.REMOVE_ITEM, false);
        ret.put(Permissions.MODIFY_ITEM, false);
        ret.put(Permissions.ADD_USER, false);
        ret.put(Permissions.REMOVE_USER, false);
        ret.put(Permissions.MODIFY_USER, false);
        ret.put(Permissions.VIEW_RECEIPT, false);

        return ret;
    }

    //Add an enumed permission and the corresponding boolean to the hashmap
    protected static void addPermissionToMap(Permissions enumPerm, Boolean value){userPerms.put(enumPerm, value);}

    //Remove permission from the hashmap
    protected void removePermissionsFromMap(Permissions enumPerm){
        userPerms.remove(enumPerm);
    }

    //Get the whole hashmap of permissions and bools
    protected void getPermissions(){
        System.out.println(userPerms);
    }

    //Check HashMap for a specific permission
    public static boolean hasPermission(Permissions perm){

        //Check if the required permission is in the Map key.  If it is, check the value and return true/false accordingly
        if (userPerms.containsKey(perm)){
            if(userPerms.get(perm)){return true;}

            else{return false;}
        }
        else{return false;}
    }

    //Get the name of the permission
    public String getPermissionName(){return permissionName;}

    //Get the description of the permission
    public String getPermissionDesc(){return description;}

    // TESTING CODE REMOVE WHEN DONE
    public static void main(String[] args){
        //Initialize a HashMap permission list
        HashMap<Permissions, Boolean> permListManager = new HashMap<>();
        //Initialize a permission
        Permission ManagerPermissions = new Permission(permListManager, "Manager", "Manager permissions");
        //Test get and addPermissions
        ManagerPermissions.getPermissions();
        ManagerPermissions.addPermissionToMap(Permissions.ADD_ITEM, true);
        ManagerPermissions.getPermissions();
        //Test hasPermission
        System.out.println(ManagerPermissions.hasPermission(Permissions.REMOVE_USER));
        System.out.println(ManagerPermissions.hasPermission(Permissions.ADD_ITEM));
        //Test removePermission
        ManagerPermissions.removePermissionsFromMap(Permissions.ADD_ITEM);
        ManagerPermissions.getPermissions();
        ManagerPermissions.addPermissionToMap(Permissions.ADD_USER, false);
        //Testing second layer of checks in hasPermission
        System.out.println(ManagerPermissions.hasPermission(Permissions.ADD_USER));
    }
}
