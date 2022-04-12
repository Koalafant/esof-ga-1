import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Terminal {
    public static void main(String[] args) throws FileNotFoundException  {

        title();
        TerminalWindow tw = new TerminalWindow();
        tw.LoginWindow();
        
        POSProxy prox = new POSProxy();

        //Database testing
        Scanner scanner = new Scanner(System.in);
        DBtesting(scanner);
        

       while(true){
    	   //Scanner scanner = new Scanner(System.in);
            

            //gather user input
            int id = gatherID(scanner);
            if(id == -1){
                continue;
            }
            //gather and hash password
            String hash = hashPass(scanner, id);

            //if hash is correct, log user in
           //TODO - must add file to keep track of users.
           //TODO - "user" must be built from this todo file.
            boolean validated = authUser(id, hash);
            if(validated){
                //prox.login(user);
            }
        }
        /**
         * TODO - Rory - Initiate login sequence
         * take user input, crosscheck against "database"
         * hash password to login
         * finish login and logout methods - include time clocked in
         *
         * TODO - Rest of us - Finish POSProxy and POS classes
         * POS is a static protected - only proxy can access it.
         * Implement AddPermissions() method. accesible only if
         * user has the correct permissions.
         * Create a mock permission for the root user to perform.
         *
         * TODO - Rory - Implement convolutional neural network model to
         * take in data and output a recommended order for the customer -
         * based off their weight, height, gender, preferences, and favorite
         * movie. Also, train a reinforcement model robot to help guide
         * customers to the bathroom of whichever establishment.
         * You must use arbitrary pathing algorithms to implement this -
         * Not all buildings have the same walls.
         *
         *
         * TODO - Rory - teach that robot how to play the piano if you have time.
         */
    }

    //by Mason
    public static void title() {
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::..:....:\n" +
                ":::::::::::.:::::::..:.....::::::::::::::::::::::::::::~JJ???~:\n" +
                "::::::::::.::.:..:......::.::::::.:.::.::::.::::::::::.?PPGGP5^\n" +
                ":::::..::...:..............:.::::.......:.:...::::::::.:JPGGPP!\n" +
                ":::::::::::::....:.........:.:.:......::..::.:..:.:::::.:YPGP57\n" +
                ":::::::::::.:::::::::...::::::.:::::::::::::::::::::::::.?PGP5!\n" +
                ":::::::::.::::::::::::::::::::::::::::::::::::::::::::::.?GGPY:\n" +
                "::::::::::..:::::::::::::::::::::::::::::::::::::::::::.^YGGPY:\n" +
                ":::::::::::::::::::::::::::::::::::::::::::::::::      :7Y5P5?::\n" +
                ".::::::::::::::::.::::::::::::::::::::::::::^^^~~~~     ~~~!?~::::::\n" +
                ".:::::::::::::::::::::::::...:::::::::::::^~~~~~~~~      ~~~~~~~~~^^::\n" +
                ":::::::::::.::::::::.::::::.::::::::::::^\t\t                   ~~:\n" +
                ":::::::::::::::::::::::::::::::::::::.:^~^\t\t\t                   ::\n" +
                ":::::::::::::::::::::::::::::::::::::^\t\t\t\t                  !^\n" +
                "::::::::::::::::::::::::::::::::::::^\t\t\t\t                  !~\n" +
                ":::::::::::::::::::::::::::::::.::^\t\t\t\t                     ~!!^\n" +
                "::::::::::::::::::::::::::::::::^~\t\t\t\t                      ~!~:\n" +
                "::::::::::::::::::::::::::::::^~\t\t\t\t                     ~~!!^\n" +
                "::::::::::::::::::::::::::::^~\t\t\t\t\t                      ~~!!^\n" +
                ":::::::::::::..:::::::::.:^^\t\t\t\t\t                     ~~~~!~^\n" +
                ":::::::::::::::::::::::::^\t  _ _\t    _ _ _      _     _ _ _\t          !~^\n" +
                ".::::::::::::::::::::::\t^\t ||_ _\\    |\t      /\t\\   | _ _ |\n" +
                "...:::::::::::::::::.:^~~!\t ||_ _ |   |_ _ _    /|\t|\\  | _ _||               ~\n" +
                "..::::.::..::.::.::..:~~!\t ||_ _/    |_ _ _   |---- | |   \\                !^\n" +
                "....................:^~!\t ||\t       |\t    |\t  | |    \\\t            !!~\n" +
                "....................:^\t\t ||\t       |_ _ _   |\t  | |     \\              ~~\n" +
                "....................:~\t\t         _ _\t   _\t _ _ _\t                 ~~\n" +
                "....................^~!\t\t        ||_ _\\\t  / \\\t|  _  \\\t\t             !~\n" +
                "....................^!!!\t        ||_ _ |\t /| |\\\t| | |  |\t             !~\n" +
                "....................:~!\t            ||_ _/  | ----| | | |  |  \t            !!~\n" +
                "::::::::::::::::::..:~!\t\t        ||\t    |     |\t| |_|  |\t            !!^\n" +
                "::::::::::::::::::::.^~!7\t        ||\t    |     |\t|_ _ _/\t\t           7!~:\n" +
                "::::::::::::::::::::::^!\t\t\t\t\t\t                            !\n" +
                ":::::::::::::::::::::::^!!\t\t\t\t\t\t                            :\n" +
                ":::::::::::::::::::::::::~\t\t\t\t\t\t                          ~:\n" +
                "::::::::::::::::::::::::::^!\t\t\t\t\t\t                     ~^^\n" +
                ":::::::::::::::::::::^^^^^~~!\t\t\t\t\t\t                    ~~~~^^^\n" +
                "::::::::::::::::::::^^^~~~!! ~~\t\t\t\t\t\t                          ^\n" +
                ":::::::::::::::::^^^^^~~~~!!!777?JY55555YYY55555555YY5555555P5555YYJJJJ??77!!\n" +
                "::::::::::::::::::^^^^^~~~!!!77??JJY5PGGGGGGGGGGGGGGBBBBGGGPP55YYYJJJJ??77!!\n" +
                ":::::::::::::::::::::^^^^^~~~!!!777???JJYY555PPPPGGGPPPP555YYYJJJ????77!!\n" +
                ":::::::::::::::::::::::::::^^^^^^~~~~~!!!!!77777??????????77777!!!\n" +
                ".::::::::::::::::::::::::::::::::::::^^^^^^^^^~~~~~~~~~\n");
    }

    //by Mason
    public static int gatherID(Scanner scanner){
        System.out.print("User ID: ");
        try {
            return scanner.nextInt();
        }catch (InputMismatchException err){
            System.out.println("User ID is a number. Try again.");
            return -1;
        }
    }

    public static String hashPass(Scanner scanner, int id){
        System.out.printf("Enter Password for User %d: ", id);
        //do not store password in any variable!
        //TODO - Rory - Hash in SHA-256 or AES.
        return "a";
    }

    public static boolean authUser(int id, String hash){

    return false;}

    //TODO - move log in and out controllers for the database into the POSProxy class.
    //Messy. Demonstrates basic database functionality. Not much error checking.
    public static void DBtesting(Scanner scanner) throws FileNotFoundException {
        Database DB;
        try {
            DB = new Database("../database.csv");
        }catch(Exception e){
            DB = new Database(("database.csv"));
        }
		DB.print();
		System.out.println();
        
        //Login
        System.out.print("Enter user id to log in: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); //dump \n
        if(DB.findUser(userID)) {
        	System.out.print("\nEnter password: ");
        	String pass = scanner.nextLine();
        	if(DB.login(userID, pass) == 1) {
        		System.out.println("\nWelcome, " + DB.getName(userID) + "! Successfully logged in.");
        	} else if(DB.login(userID, pass) == 0){
        		System.out.println(DB.getName(userID) + ", you are already logged in!");
        	} else {
        		System.out.println("Incorrect password.");
        	}
        } else {
        	System.out.println("Invalid user ID.");
        }
        DB.update();
        System.out.println();
        DB.print();
        
        //Logout
        System.out.print("Enter user id to log out: ");
        userID = scanner.nextInt();
        scanner.nextLine(); //dump \n
        if(DB.findUser(userID)) {
        	System.out.print("\nEnter password: ");
        	String pass = scanner.nextLine();
        	if(DB.logout(userID, pass) == 1) {
        		System.out.println(DB.getName(userID) + ", you have sccessfully logged out.");
        	} else if(DB.logout(userID, pass) == 0){
        		System.out.println(DB.getName(userID) + ", you are already logged out!");
        	} else {
        		System.out.println("Incorrect password.");
        	}
        } else {
        	System.out.println("Invalid user ID.");
        }
        
        DB.update();
        System.out.println();
        DB.print();
        
        
        //Delete user
        System.out.print("Enter user id to delete: "); //11
        userID = scanner.nextInt();
        scanner.nextLine(); //dump \n
        if(DB.deleteUser(userID)) {
        	System.out.println("User successfully deleted.");
        } else {
        	System.out.println("No such user to delete.");
        }
        
        DB.update();
        System.out.println();
        DB.print();
        
        
        //Add user
        System.out.println("\nAdding a user...");
        String[] newUserInfo = {"11", "Sam2", "aPASS", "cash", "false"};
        if(DB.addUser(newUserInfo)) {
        	System.out.println("New user successfully added.");
        } else {
        	System.out.println("No space to add user. Please fire someone then try again.");
        }
        
        DB.update();
        System.out.println();
        DB.print();
        
        System.out.println("-------- DATABASE TEST END --------\n");
    }
    
    
}

