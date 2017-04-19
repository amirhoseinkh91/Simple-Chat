package client.gui.login;

import javax.swing.*;

/**
 * Created by amir on 4/18/17.
 */
public class LoginFrame extends JFrame {

    private LoginPanel loginPanel;

    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 220, 366, 234);
        setResizable(false);
        setTitle("Login");

        loginPanel = new LoginPanel();
        add(loginPanel);
        loginPanel.setVisible(true);
    }

}
