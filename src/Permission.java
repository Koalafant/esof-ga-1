import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// By Cole
public class Permission {
    //Initialize fields
    String permissionName;
    String description;
    HashMap<User, Permissions> userPerms = new HashMap<User, Permissions>();
    ArrayList<Permission> permissions;

    enum Permissions{
        ADD_ITEM,
        REMOVE_ITEM,
        MODIFY_ITEM,
        ADD_USER,
        REMOVE_USER,
        MODIFY_USER,
        VIEW_RECEIPT,
    }
    //Init a permission using a name, description, and a list of abilities/permissions
    protected Permission(String permName, String permDesc, ArrayList<Permission> perms){
        permissionName = permName;
        description = permDesc;
        permissions = perms;
    }

    //Secondary Constructor for just name and description
    protected Permission(String permName, String permDesc){
        permissionName = permName;
        description = permDesc;
    }

    //Create a list of abilities that the permission has
    public boolean hasPermission(Permission perm){
        return permissions.contains(perm);
    }

    //Add permission to system
    protected void addPermission(Permission perm){
        permissions.add(perm);
    }

    //Remove permission from system
    protected void removePermission(Permission perm){
        permissions.remove(perm);
    }

    //Get the name of the permission
    public String getPermissionName(){
        return permissionName;
    }

    //Get the description of the permission
    public String getPermissionDesc(){
        return description;
    }

    public ArrayList<Permission> getPermissions(){
        return permissions;
    }

    // TESTING CODE
    public static void main(String[] args){
        //Test Cashier
        ArrayList<Permissions> cashierPermissions = new ArrayList<>(Arrays.asList(Permissions.values()));
        Cashier Joe = new Cashier(123, "123", "Joe");
        HashMap<Cashier, ArrayList<Permissions>> JoePerms = new HashMap<>();
        JoePerms.put(Joe, cashierPermissions);
        System.out.println(JoePerms.toString());


//        Permission cashier = new Permission("Cashier", "has read permissions", cashierPermissions);
//        Permission read = new Permission("Read", "Read menu items");
//        Permission write = new Permission("Write", "Add menu items");
//
//        cashier.addPermission(read);
//        System.out.println(cashier.hasPermission(read));
//
//        //Test Manager
//        ArrayList<Permission> managerPermissions = new ArrayList<Permission>();
//        Permission manager = new Permission("Manager", "has read permissions and write permissions", managerPermissions);
//
//        manager.addPermission(read);
//        manager.addPermission(write);
//        System.out.println(manager.hasPermission(write));
//
//        manager.removePermission(write);
//        System.out.println(manager.hasPermission(write));

    }
}
