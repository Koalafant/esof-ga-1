import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ESOF 322
 * Cole, Mason, Alex, Rory
 * GUI Restaurant point of sale.
 * Utilizes singleton and proxy patterns to keep dynamic permissions
 */
public class Terminal {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException  {

        //Initialize proxy
        initialize();
        POSProxy prox = new POSProxy();

        //Initialize GUI
        TerminalWindow tw = new TerminalWindow();
        tw.LoginWindow();
        }

    //by Mason
    /**
     * UNUSED
     * @param scanner
     * @return
     */
    public static int gatherID(Scanner scanner){
        System.out.print("User ID: ");
        try {
            return scanner.nextInt();
        }catch (InputMismatchException err){
            System.out.println("User ID is a number. Try again.");
            return -1;
        }
    }

    /**
     * Initializes database with its .csv file
     * @return UNUSED
     */
    public static Database initialize() throws FileNotFoundException {
        try {
            return Database.getInstance("../data/database.csv");
        }catch(FileNotFoundException e){
            return Database.getInstance("data/database.csv");
        }
    }
    
    //by Alex
    /**
     * SHA-256 standard hashing method. Stored passwords are hashed,
     * not plain text.
     * @param pass - sent through method without echoing.
     * @return a hashed password to store/auth
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String hashPass(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	BigInteger bigInt = new BigInteger(md.digest(pass.getBytes("UTF-8"))); 
    	String hashedPass = bigInt.toString(16);
    	return hashedPass;
    }
}
