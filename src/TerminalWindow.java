import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TerminalWindow extends JFrame implements ActionListener{
    
    private static TerminalWindow inst;

    private static JLabel passwordLabel, usernameLabel;
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
        panel.setBackground(new Color(230, 230, 230));

        ImageIcon logo = new ImageIcon("images/logo.png");
        Image image = logo.getImage();
        setIconImage(image);

        setSize(400, 200);
        setLocation(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        add(panel);

        JLabel otherLogo = new JLabel(logo);
        otherLogo.setBounds(277, 20, 120, 120);
        panel.add(otherLogo);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(100, 8, 70, 20);
        panel.add(usernameLabel);

        username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        panel.add(username);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 55, 70, 20);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(100, 75, 193, 28);
        panel.add(password);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.addActionListener((ActionListener) this);
        panel.add(loginButton);

        setVisible(true);
    }

    public static String getUsernameInput(){
        return username.getSelectedText();
    }

    public static String getPasswordInput(){
        return password.getSelectedText();
    }

    public void actionPerformed(ActionEvent ae){
        
        if(ae.getActionCommand().equals("Login")){
            String Username = getUsernameInput();
            String Password = getPasswordInput();
            
        }
        
    }
}
