import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;


public class TerminalWindow extends JFrame implements ActionListener{
    
    private static TerminalWindow inst;

    private static JLabel passwordLabel, usernameLabel, loginFailure;
    private static JTextField username;    
    private static JPasswordField password;
    private static JButton loginButton;
    
    public static TerminalWindow getInstance()
    {
        if (inst == null)
            inst = new TerminalWindow();
        return inst;
    }

    public void LoginWindow(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);

        setSize(500, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        add(panel);

        JLabel otherLogo = new JLabel(logo);
        otherLogo.setBounds(370, 20, 120, 120);
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
        loginButton.addActionListener((ActionListener) this);
        panel.add(loginButton);

        setVisible(true);
    }

   

    public void actionPerformed(ActionEvent ae){
        
        if(ae.getActionCommand().equals("Login")){
            //System.out.println(username.getText());
            Boolean validUser;
            Boolean validInt = username.getText().matches("-?\\d+");

            if(validInt == true){
                validUser = Database.findUser(Integer.parseInt(username.getText()));                    
            }
            else{validUser = false;} 

            if (validUser == true){
                
                    try {
                        Database.login(Integer.parseInt(username.getText()), Terminal.hashPass(password.getSelectedText()));
                    } catch (NumberFormatException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                 
            }
            else{
                loginFailure = new JLabel("Username not found");
                loginFailure.setBounds(150, 75, 70, 20);
                add(loginFailure);
            }
            
        }
        
        
    }
}
