import java.util.ArrayList;

// By Cole
public class Permission {
    //Initialize fields
    String permissionName;
    String description;
    ArrayList<Permission> permissions;

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
    public ArrayList<Permission> hasPermissions(){
        return permissions;
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

    // TESTING CODE
    public static void main(String[] args){
        //Test Cashier
        ArrayList<Permission> cashierPermissions = new ArrayList<Permission>();
        Permission cashier = new Permission("Cashier", "has read permissions", cashierPermissions);
        Permission read = new Permission("Read", "Read menu items");
        Permission write = new Permission("Write", "Add menu items");

        cashier.addPermission(read);
        System.out.println(cashier.hasPermissions());

        //Test Manager
        ArrayList<Permission> managerPermissions = new ArrayList<Permission>();
        Permission manager = new Permission("Manager", "has read permissions and write permissions", managerPermissions);
        
        manager.addPermission(read);
        manager.addPermission(write);
        System.out.println(manager.hasPermissions());
    }
}
