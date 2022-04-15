import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TerminalWindow extends JFrame implements ActionListener{
    
    private static TerminalWindow inst;

    private static JLabel passwordLabel, usernameLabel, loginFailure;
    private static JTextField username, permissions;    
    private static JPasswordField password;
    private static JButton loginButton, logoutButton, changePermissions;
    private static JPanel panel;
    
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
    public void LoginWindow(){
        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);

        JLabel otherLogo = new JLabel(logo);
        otherLogo.setBounds(277, 20, 120, 120);
        panel.add(otherLogo);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(150, 8, 70, 20);
        panel.add(usernameLabel);

        username = new JTextField();
        username.setBounds(150, 27, 193, 28);
        panel.add(username);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 55, 70, 20);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(150, 75, 193, 28);
        panel.add(password);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 110, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        setVisible(true);
    }

    public void UserMenu(){
        
        changePermissions = new JButton("Permissions");
        changePermissions.setBounds(140, 110, 90, 25);
        changePermissions.setForeground(Color.WHITE);
        changePermissions.setBackground(Color.BLACK);
        changePermissions.addActionListener(this);
        panel.add(changePermissions);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(260, 110, 90, 25);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLACK);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        setVisible(true);
    }

    public void permissionsMenu(){
        
    }
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getActionCommand().equals("Login")){
            Boolean validUser;
            System.out.println(username.getText());
            Boolean validInt = username.getText().matches("-?\\d+");

            if(validInt == true){
                validUser = Database.userExists(Integer.parseInt(username.getText()));
            }
            else{validUser = false;} 

            if (validUser == true){
                
                    try {
                        Database.login(Integer.parseInt(username.getText()), Terminal.hashPass(password.getSelectedText()));
                        getContentPane().removeAll();
                        getContentPane().repaint();

                        UserMenu();
                      
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                        
                 
            }
            else{
                JOptionPane.showMessageDialog(this, "Username not found", "WARNING", JOptionPane.WARNING_MESSAGE);
                //loginFailure = new JLabel("Username not found");
                //loginFailure.setBounds(150, 75, 70, 20);
                //getContentPane().add(loginFailure);
            }           
        }
        else if(ae.getActionCommand().equals("Logout")){
            try{
                Database.logout(Integer.parseInt(username.getText()), Terminal.hashPass(password.getSelectedText()));
                getContentPane().removeAll();
                getContentPane().repaint();
                LoginWindow();
            } catch (Exception e){
                e.getStackTrace();
            }
        }   
        else if(ae.getActionCommand().equals("Permissions")){
            getContentPane().removeAll();
            getContentPane().repaint();
            permissionsMenu();
        }     
    }
}
