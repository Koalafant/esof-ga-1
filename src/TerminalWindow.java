import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

// Written by Rory
/**
 * <h1>Restaurant Interface GUI</h1>
 * Terminal window creates the on screen interface to login/logout and change permissions.
 * 
 * 
 * @author Rory Donley-Lovato
 * @since 4/17/2022
 */
public class TerminalWindow extends JFrame implements ActionListener{
    
    /**
     * All of the variables used by the GUI
     * inst and proxy are class references
     * passwordLabel, usernameLabel, and permissionID are title text
     * username and userID are input fields
     * password is a secure password input field
     * The 5 buttons are triggers for events in actionListener
     * The checkboxes are for the permissions screen when adding permissions to a user
     */
    private static TerminalWindow inst;
    private static POSProxy proxy;

    private static JLabel passwordLabel, usernameLabel, permissionID;
    private static JTextField username, userID;    
    private static JPasswordField password;
    private static JButton loginButton, logoutButton, changePermissions, exitPermissions, submitPermissions;
    private static JPanel panel;
    private static JCheckBox addItem, removeItem, modItem, addUser, removeUser, modUser, viewReceipt;
    
    /**
     * This method creates an instance of the TerminalWindow constructor
     * @return the created instance
     * @throws FileNotFoundException
     */
    public static TerminalWindow getInstance() throws FileNotFoundException {
        if (inst == null)
            inst = new TerminalWindow(new POSProxy());
        return inst;
    }

    /**
     * This is the base of the Terminal Window
     * It contains the base measurements for the GUI window
     * @param px
     */
    TerminalWindow(POSProxy px){
        proxy = px;
        panel = new JPanel();
        setResizable(false);
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 230));

        setSize(500, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        add(panel);
    }

    /**
     * This method creates the login window
     * User inputs their username and password and proceeds to the main menu if both are correct
     * Login button triggers events in actionListener to create error messages or create menu screen
     */
    public void LoginWindow(){
        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);

        JLabel otherLogo = new JLabel(logo);
        otherLogo.setBounds(350, 20, 120, 120);
        panel.add(otherLogo);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(150, 58, 70, 20);
        panel.add(usernameLabel);

        username = new JTextField();
        username.setBounds(150, 77, 193, 28);
        panel.add(username);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 105, 70, 20);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(150, 125, 193, 28);
        panel.add(password);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 160, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        setVisible(true);
    }

    /**
     * This method creates the main menu with 2 buttons 
     * for logout or open permissions screen
     * Permissions button will open the permissions screen
     * Logout will logout the user and return to the login screen
     */
    public void UserMenu(){               
        
        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);
        
        changePermissions = new JButton("Permissions");
        changePermissions.setBounds(120, 160, 110, 25);
        changePermissions.setForeground(Color.WHITE);
        changePermissions.setBackground(Color.BLACK);
        changePermissions.addActionListener(this);
        panel.add(changePermissions);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(260, 160, 90, 25);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLACK);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        setVisible(true);
    }

    /**
     * This method opens the permissions screen
     * Creates 7 checkboxes and a text field 
     * The checkboxes correspond to the seven permissions available
     * The userID input field will get the target ID that the permissions are being applied to
     */
    public void permissionsMenu(){
        
        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);
        
        permissionID = new JLabel("User ID");
        permissionID.setBounds(280, 110, 50, 25);
        panel.add(permissionID);
        
        userID = new JTextField();
        userID.setBounds(330, 110, 100, 25);
        panel.add(userID);
        
        // Creates all 7 checkboxes
        
        // Add item permission
        if(Permission.hasPermission(Permission.Permissions.ADD_ITEM) == true){
            addItem = new JCheckBox("Add Item", true);
            addItem.setBounds(60, 20, 150, 25);
            panel.add(addItem);
        }
        else{
            addItem = new JCheckBox("Add Item");
            addItem.setBounds(60, 20, 150, 25);
            panel.add(addItem);
        }
        
        // Remove Item permission
        if(Permission.hasPermission(Permission.Permissions.REMOVE_ITEM) == true){
            removeItem = new JCheckBox("Remove Item", true);
            removeItem.setBounds(60, 50, 150, 25);
            panel.add(removeItem);
        }
        else{
            removeItem = new JCheckBox("Remove Item");
            removeItem.setBounds(60, 50, 150, 25);
            panel.add(removeItem);
        }
            
        // Modify Item permission
        if(Permission.hasPermission(Permission.Permissions.MODIFY_ITEM) == true){
            modItem = new JCheckBox("Modify Item", true);
            modItem.setBounds(60, 80, 150, 25);
            panel.add(modItem);
        }
        else{
            modItem = new JCheckBox("Modify Item");
            modItem.setBounds(60, 80, 150, 25);
            panel.add(modItem);
        }
        
        // Add user permission
        if(Permission.hasPermission(Permission.Permissions.ADD_USER) == true){
            addUser = new JCheckBox("Add User", true);
            addUser.setBounds(280, 20, 150, 25);
            panel.add(addUser);
        }
        else{
            addUser = new JCheckBox("Add User");
            addUser.setBounds(280, 20, 150, 25);
            panel.add(addUser);
        }
        
        // Remove User permission
        if(Permission.hasPermission(Permission.Permissions.REMOVE_USER) == true){
            removeUser = new JCheckBox("Remove User", true);
            removeUser.setBounds(280, 50, 150, 25);
            panel.add(removeUser);
        }
        else{
            removeUser = new JCheckBox("Remove User");
            removeUser.setBounds(280, 50, 150, 25);
            panel.add(removeUser);
        }
        
        // Modify User permission
        if(Permission.hasPermission(Permission.Permissions.MODIFY_USER) == true){
            modUser = new JCheckBox("Modify User", true);
            modUser.setBounds(280, 80, 150, 25);
            panel.add(modUser);
        }
        else{
            modUser = new JCheckBox("Modify User");
            modUser.setBounds(280, 80, 150, 25);
            panel.add(modUser);
        }
        
        // View Receipt permission
        if(Permission.hasPermission(Permission.Permissions.VIEW_RECEIPT) == true){
            viewReceipt = new JCheckBox("View Receipt", true);
            viewReceipt.setBounds(60, 110, 150, 25);
            panel.add(viewReceipt);
        }
        else{
            viewReceipt = new JCheckBox("View Receipt");
            viewReceipt.setBounds(60, 110, 150, 25);
            panel.add(viewReceipt);
        }
        
        submitPermissions = new JButton("Submit");
        submitPermissions.setBounds(140, 160, 90, 25);
        submitPermissions.setForeground(Color.WHITE);
        submitPermissions.setBackground(Color.BLACK);
        submitPermissions.addActionListener(this);
        panel.add(submitPermissions);
        
        exitPermissions = new JButton("Back");
        exitPermissions.setBounds(260, 160, 90, 25);
        exitPermissions.setForeground(Color.WHITE);
        exitPermissions.setBackground(Color.BLACK);
        exitPermissions.addActionListener(this);
        panel.add(exitPermissions);
        
        setVisible(true);
    }

    /**
     * This method contains all of the events that trigger upon different button being pressed
     * 
     * Login checks if the username inputed is valid
     * If the username is in the database, it'll begin the login sequence with the password
     * and create the main menu if the password validates for that username
     * Displays error messages for username not being in the database, password being incorrect, and user already logged in
     * 
     * Logout triggers the logout sequence in database and recreates the login screen
     * 
     * Permissions creates the permissions menu from the main menu
     * 
     * Back recreates the main menu from the permissions menu
     * 
     * Submit triggers a permissions changing sequence
     * It goes through each checkbox and if the checkbox is true,
     * it will add the permission in the checkbox to the user listed in the user ID field
     * Upon completion it will display a permissions updated message
     * It will also display an error message if there is no user ID listed
     * or the ID inputted is not in the database
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        
        // Triggers login sequence
        if(ae.getActionCommand().equals("Login")){
                        
            Boolean validUser;
            Boolean validInt = username.getText().matches("-?\\d+");

            if(validInt == true){
                validUser = Database.userExists(Integer.parseInt(username.getText()));
            }
            else{validUser = false;} 

            if (validUser == true){                    
                    try {                        
                        int loginStatus = proxy.login(Integer.parseInt(username.getText()), Terminal.hashPass(String.valueOf(password.getPassword())));
                        switch (loginStatus){
                            case 1:
                                dispose();
                                TerminalWindow tw = new TerminalWindow(proxy);
                                tw.UserMenu();
                                break;
                            case -1:
                                JOptionPane.showMessageDialog(this, "Incorrect Username or Password", "WARNING", JOptionPane.WARNING_MESSAGE);
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(this, "User already logged in", "WARNING", JOptionPane.WARNING_MESSAGE);
                                break;
                        }
                                              
                    } catch (FileNotFoundException | UnsupportedEncodingException | NumberFormatException | NoSuchAlgorithmException e) {
                        e.getStackTrace();
                    }                      
            }
            else{
                JOptionPane.showMessageDialog(this, "Username not found", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }
        // Logout sequence
        else if(ae.getActionCommand().equals("Logout")){
            try{                
                int logoutStatus = proxy.logout(Integer.parseInt(username.getText()), Terminal.hashPass(String.valueOf(password.getPassword())));
                switch(logoutStatus){
                    case 1:
                        dispose();
                        TerminalWindow tw = new TerminalWindow(proxy);
                        tw.LoginWindow();
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(this, "Error Unknown", "WARNING", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(this, "User already logged out", "WARNING", JOptionPane.WARNING_MESSAGE);
                        break;
                }
                
            } catch (FileNotFoundException | UnsupportedEncodingException | NumberFormatException | NoSuchAlgorithmException e){
                e.getStackTrace();
            }
        }

        // Displays permissions menu
        else if(ae.getActionCommand().equals("Permissions")){
            dispose();
            TerminalWindow tw = new TerminalWindow(proxy);
            tw.permissionsMenu();
        }  

        // Returns to main menu from permissions menu  
        else if(ae.getActionCommand().equals("Back")){
            dispose();
            TerminalWindow tw = new TerminalWindow(proxy);
            tw.UserMenu();
        }
        
        else if(ae.getActionCommand().equals("Submit")){
            
            Boolean validUserID;
            Boolean validInt = userID.getText().matches("-?\\d+");

            if(validInt == true){
                validUserID = Database.userExists(Integer.parseInt(username.getText()));
            }
            else{validUserID = false;} 
            
            if(validUserID == true){
                // Changes Add Item permission
                try {
                    if(addItem.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.ADD_ITEM.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.ADD_ITEM.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Changes Remove Item permission
                try {
                    if(removeItem.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.REMOVE_ITEM.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.REMOVE_ITEM.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Changes Modify Item permission
                try {
                    if(modItem.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.MODIFY_ITEM.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.MODIFY_ITEM.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Changes Add User permission           
                try {
                    if(addUser.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.ADD_USER.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.ADD_USER.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Changes Remove User permission
                try {
                    if(removeUser.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.REMOVE_USER.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.REMOVE_USER.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Changes Modify User permission
                try {
                    if(modUser.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.MODIFY_USER.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.MODIFY_USER.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Changes View Receipt permission
                try {
                    if(viewReceipt.isSelected() == true){proxy.addPerm(Integer.parseInt(userID.getText()), Permission.Permissions.VIEW_RECEIPT.ordinal());}
                    else{proxy.removePerm(Integer.parseInt(userID.getText()), Permission.Permissions.VIEW_RECEIPT.ordinal());}
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TerminalWindow.class.getName()).log(Level.SEVERE, null, ex);
                }    

                
                JOptionPane.showMessageDialog(this, "Permissions Updated", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                
            } else{
                JOptionPane.showMessageDialog(this, "Enter a valid User ID", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}