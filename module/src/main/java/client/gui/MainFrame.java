package client.gui;

import javax.swing.*;

/**
 * Created by amir on 4/18/17.
 */
public class MainFrame extends JFrame {

    private LoginPanel loginPanel;
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 366, 234);
        setResizable(false);

        loginPanel = new LoginPanel();
        add(loginPanel);
        loginPanel.setVisible(true);
    }

}
