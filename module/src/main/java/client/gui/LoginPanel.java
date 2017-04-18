package client.gui;

import client.Client;
import user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * Created by amir on 4/18/17.
 */
public class LoginPanel extends JPanel {
    private JButton btnConnect;
    private JLabel lblServerIp;
    private JLabel lblServerPort;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtServerIp;
    private JTextField txtServerPort;
    private JTextField txtUsername;
    private JTextField txtPassword;

    public LoginPanel() {
        setBackground(Color.LIGHT_GRAY);
        setName("Login");
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        // define buttons
        defineButtons();

        // actionForConnectButton
        btnConnectClicked();

        // define lables
        defineLabels();

        // define textFields
        defineTextFields();

        // add button to panel
        addButtons();

        // add lablels to panel
        addLables();

        // add textFields to panel
        addTexfFields();
    }

    private void btnConnectClicked(){
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                btnConnectAction();
            }
        });
    }

    private void btnConnectAction(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String serverIp = txtServerIp.getText();
        int serverPort =  Integer.parseInt(txtServerPort.getText());
        User user = new User(username, password);
        Client client = new Client(serverIp, serverPort , user);
    }

    private void addTexfFields(){
        txtServerPort.setBounds(150, 20, 120, 20);
        add(txtServerPort);
        txtServerPort.setVisible(true);

        txtServerIp.setBounds(150, 50, 120, 20);
        add(txtServerIp);
        txtServerIp.setVisible(true);

        txtUsername.setBounds(150, 80, 120, 20);
        add(txtUsername);
        txtUsername.setVisible(true);

        txtPassword.setBounds(150, 110, 120, 20);
        add(txtPassword);
        txtPassword.setVisible(true);
    }

    private void addLables() {
        lblServerIp.setBounds(50, 20, 100, 20);
        add(lblServerIp);
        lblServerIp.setVisible(true);
        lblServerPort.setBounds(50, 50, 100, 20);
        add(lblServerPort);
        lblServerPort.setVisible(true);
        lblUsername.setBounds(50, 80, 100, 20);
        add(lblUsername);
        lblUsername.setVisible(true);
        lblPassword.setBounds(50, 110, 100, 20);
        add(lblPassword);
        lblPassword.setVisible(true);
    }

    private void addButtons() {
        add(btnConnect);
        btnConnect.setBounds(120, 170, 100, 20);
        btnConnect.setVisible(true);
    }

    private void defineTextFields() {
        txtServerIp = new JTextField();
        txtServerPort = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JTextField();
    }

    private void defineLabels() {
        lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.black);

        lblServerIp = new JLabel("Server IP");
        lblServerIp.setForeground(Color.black);

        lblServerPort = new JLabel("Server Port");
        lblServerPort.setForeground(Color.black);

        lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.black);
    }

    private void defineButtons() {
        btnConnect = new JButton("Connect");
        btnConnect.setForeground(Color.black);
    }
}
