
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Written by Rory
public class TerminalWindow extends JFrame implements ActionListener{
    
    private static TerminalWindow inst;

    private static JLabel passwordLabel, usernameLabel, permissionsUpdated;
    private static JTextField username;    
    private static JPasswordField password;
    private static JButton loginButton, logoutButton, changePermissions, exitPermissions, submitPermissions;
    private static JPanel panel;
    private static JCheckBox addItem, removeItem, modItem, addUser, removeUser, modUser, viewReceipt;
    
    public static TerminalWindow getInstance()
    {
        if (inst == null)
            inst = new TerminalWindow();
        return inst;
    }

    TerminalWindow(){
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

    // Login Window, take username and password and checks against record before allowing access
    public void LoginWindow(){
        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);

        JLabel otherLogo = new JLabel(logo);
        otherLogo.setBounds(277, 20, 120, 120);
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

    // Main menu once logged in, can proceed to permissions menu or logout
    public void UserMenu(){               
        
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

    // Menu to change any permissions
    public void permissionsMenu(){
        
        // Creates all 7 checkboxes, set them as true if user already has the permission
        
        // Add item permission
        if(Permission.hasPermission(Permission.Permissions.ADD_ITEM) == true){
            addItem = new JCheckBox("Add Item", true);
            addItem.setBounds(60, 20, 150, 25);
            panel.add(addItem);
        }
        else{
            addItem = new JCheckBox("Add Item", true);
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
                        Database.login(Integer.parseInt(username.getText()), Terminal.hashPass(password.getSelectedText()));
                        dispose();
                        TerminalWindow tw = new TerminalWindow();
                        tw.UserMenu();                      
                    } catch (Exception e) {
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
                Database.logout(Integer.parseInt(username.getText()), Terminal.hashPass(password.getSelectedText()));
                dispose();
                TerminalWindow tw = new TerminalWindow();
                tw.LoginWindow();
            } catch (Exception e){
                e.getStackTrace();
            }
        }   
        // Displays permissions menu
        else if(ae.getActionCommand().equals("Permissions")){
            dispose();
            TerminalWindow tw = new TerminalWindow();
            tw.permissionsMenu();
        }  
        // Returns to main menu from permissions menu  
        else if(ae.getActionCommand().equals("Back")){
            dispose();
            TerminalWindow tw = new TerminalWindow();
            tw.UserMenu();
        }
        
        else if(ae.getActionCommand().equals("Submit")){
            
            // Changes Add Item permission
            if(addItem.isSelected() == true){Permission.addPermissionToMap(Permission.Permissions.ADD_ITEM, true);}
            else{Permission.addPermissionToMap(Permission.Permissions.ADD_ITEM, false);}

            // Changes Remove Item permission
            if(removeItem.isSelected() == true){Permission.addPermissionToMap((Permission.Permissions.REMOVE_ITEM), true);}
            else{Permission.addPermissionToMap(Permission.Permissions.REMOVE_ITEM, false);}

            // Changes Modify Item permission
            if(modItem.isSelected() == true){Permission.addPermissionToMap(Permission.Permissions.MODIFY_ITEM, true);}
            else{Permission.addPermissionToMap(Permission.Permissions.MODIFY_ITEM, false);}

            // Changes Add User permission
            if(addUser.isSelected() == true){Permission.addPermissionToMap(Permission.Permissions.ADD_USER, true);}
            else{Permission.addPermissionToMap(Permission.Permissions.ADD_USER, false);}

            // Changes Remove User permission
            if(removeUser.isSelected() == true){Permission.addPermissionToMap(Permission.Permissions.REMOVE_USER, true);}
            else{Permission.addPermissionToMap(Permission.Permissions.REMOVE_USER, false);}

            // Changes Modify User permission
            if(modUser.isSelected() == true){Permission.addPermissionToMap(Permission.Permissions.MODIFY_USER, true);}
            else{Permission.addPermissionToMap(Permission.Permissions.MODIFY_USER, false);}

            // Changes View Receipt permission
            if(viewReceipt.isSelected() == true){Permission.addPermissionToMap(Permission.Permissions.VIEW_RECEIPT, true);}
            else{Permission.addPermissionToMap(Permission.Permissions.VIEW_RECEIPT, false);}

            permissionsUpdated = new JLabel("Permissions Updated");
            permissionsUpdated.setBounds(200, 200, 150, 25);
            panel.add(permissionsUpdated);
        }
    }
}