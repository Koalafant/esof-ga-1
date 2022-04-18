import java.util.HashMap;

// By Cole
/**
 * Permission class for creating user permissions
 */
public class Permission {
    //Initialize fields
    String permissionName;
    String description;
    static HashMap<Permissions, Boolean> userPerms = new HashMap<Permissions, Boolean>();

    /**
     * Enum of set in stone permissions that a user could use
     */
    public enum Permissions{
        ADD_ITEM,
        REMOVE_ITEM,
        MODIFY_ITEM,
        ADD_USER,
        REMOVE_USER,
        MODIFY_USER,
        VIEW_RECEIPT
    }

    /**
     * Permission class constructor to initialize a permission with a name, description, and a HashMap of permissions and bools
     * @param userPerms a hashmap of user permissions with enumerated Permissions as keys and Booleans as values.
     * @param permName name of the permission
     * @param permDesc a quick description of the permission
     */
    protected Permission(HashMap<Permissions, Boolean> userPerms, String permName, String permDesc){
        permissionName = permName;
        description = permDesc;
        this.userPerms = userPerms;
    }

    /**
     * Initialize a default map of false permissions
     * @return default map of false permissions
     */
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

    /**
     * Add an enumerated permission and the corresponding boolean to the hashmap
     * @param enumPerm an enumerated permissions
     * @param value a boolean value associated with the permission
     */
    protected static void addPermissionToMap(Permissions enumPerm, Boolean value){userPerms.put(enumPerm, value);}

    /**
     * Remove a desired permission from the hashmap
     * @param enumPerm enumerated permission to remove from the hashmap
     */
    protected void removePermissionsFromMap(Permissions enumPerm){
        userPerms.remove(enumPerm);
    }

    /**
     * Get the whole hashmap of permissions and boolean values
     */
    protected void getPermissions(){
        System.out.println(userPerms);
    }

    /**
     * Check hashmap for a specific enumerated permission
     * @param perm an enumerated permission to check for in permissions hashmap
     * @return a boolean value depending on if the desired enumerated permission is in the hashmap
     */
    public static boolean hasPermission(Permissions perm){

        //Check if the required permission is in the Map key.  If it is, check the value and return true/false accordingly
        if (userPerms.containsKey(perm)){
            if(userPerms.get(perm)){return true;}
            else{return false;}
        }
        else{return false;}
    }

    /**
     * Get the name of the permission
     * @return the name of the permission
     */
    public String getPermissionName(){return permissionName;}

    /**
     * Get the description of the permission
     * @return the description of the permission
     */
    public String getPermissionDesc(){return description;}
}

