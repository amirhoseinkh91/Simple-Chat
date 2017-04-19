package client.gui.login;

import client.gui.ChooseUser.ChooseUserFrame;
import client.model.Client;
import server.ClientHandler;
import user.User;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.EmptyStackException;

/**
 * Created by amir on 4/18/17.
 */
public class LoginPanel extends JPanel {
    private JButton btnConnect;
    private JLabel lblServerIp;
    private JLabel lblServerPort;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblErrorEnterFields;
    private JLabel lblErrorPortInteger;
    private JLabel lblUnknownUser;
    private JLabel lblConnectionFailed;
    private JTextField txtServerIp;
    private JTextField txtServerPort;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginPanel() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        // define buttons
        defineButtons();
        // define labels
        defineLabels();
        // define textFields
        defineTextFields();

        // add button to panel
        addButtons();
        // add labels to panel
        addLables();
        // add textFields to panel
        addTextfFields();

        // actionForConnectButton
        btnConnectClicked();

    }

    private void btnConnectClicked() {
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                btnConnectAction();
            }
        });

        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    btnConnectAction();
                }
            }
        });
    }


    private void btnConnectAction() {
        String username = null;
        String password = null;
        String serverIp = null;
        int serverPort = 0;
        User user = null;

        try {
            username = readUsername();
            password = readPassword();
            serverIp = readServerIp();
            serverPort = readServerPort();
            Client client = new Client(serverIp, serverPort, new User(username, password));
            openChooseFrame(client);
            ((Window) getRootPane().getParent()).dispose();
        } catch (NumberFormatException e) {
            printNumberFormatException();
        } catch (IllegalArgumentException e) {
            printEnterFieldsError();
        } catch (LoginException e) {
            printUnknownUserError();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void openChooseFrame(Client client) throws LoginException {
        boolean isUser = true;
        ClientHandler clientHandler = new ClientHandler(client);
        try{
        isUser = clientHandler.isValid();
        } catch (IOException e){
            printConnectionFailed();
        }

        if (isUser) {
            ChooseUserFrame chooseUserFrame = new ChooseUserFrame(client);
            chooseUserFrame.setVisible(true);
        } else {
            throw new LoginException();
        }
    }

    private void printConnectionFailed(){
        lblErrorEnterFields.setVisible(false);
        lblErrorPortInteger.setVisible(false);
        lblUnknownUser.setVisible(false);
        lblConnectionFailed.setVisible(true);
    }

    private void printUnknownUserError() {
        lblConnectionFailed.setVisible(false);
        lblErrorEnterFields.setVisible(false);
        lblErrorPortInteger.setVisible(false);
        lblUnknownUser.setVisible(true);
        txtPassword.setText("");
    }

    private void printNumberFormatException() {
        lblConnectionFailed.setVisible(false);
        lblErrorEnterFields.setVisible(false);
        lblUnknownUser.setVisible(false);
        lblErrorPortInteger.setVisible(true);
    }

    private void printEnterFieldsError() {
        lblConnectionFailed.setVisible(false);
        lblErrorPortInteger.setVisible(false);
        lblUnknownUser.setVisible(false);
        lblErrorEnterFields.setVisible(true);
    }

    private int readServerPort() throws NumberFormatException {
        int serverPort;
        try {
            serverPort = Integer.parseInt(txtServerPort.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
        return serverPort;
    }

    private String readServerIp() throws IllegalArgumentException {
        String ip = txtServerIp.getText();
        if (ip != null && (!ip.equals("")) && (!ip.equals(" "))) {
            return ip;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String readPassword() throws IllegalArgumentException {
        char[] pass = txtPassword.getPassword();
        if (pass != null && (pass.length > 0)) {
            return charArrayToString(pass);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String charArrayToString(char[] pass) {
        String password = "";
        for (int i = 0; i < pass.length; i++) {
            password += pass[i];
        }
        return password;
    }

    private String readUsername() throws IllegalArgumentException {
        String username = txtUsername.getText();
        if (username != null && (!username.equals("")) && (!username.equals(" "))) {
            return username;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void addTextfFields() {
        txtServerIp.setBounds(170, 20, 120, 25);
        add(txtServerIp);
        txtServerIp.setVisible(true);

        txtServerPort.setBounds(170, 50, 120, 25);
        add(txtServerPort);
        txtServerPort.setVisible(true);

        txtUsername.setBounds(170, 80, 120, 25);
        add(txtUsername);
        txtUsername.setVisible(true);

        txtPassword.setBounds(170, 110, 120, 25);
        add(txtPassword);
        txtPassword.setVisible(true);
    }

    private void addLables() {
        lblServerIp.setBounds(70, 20, 100, 20);
        add(lblServerIp);
        lblServerIp.setVisible(true);

        lblServerPort.setBounds(70, 50, 100, 20);
        add(lblServerPort);
        lblServerPort.setVisible(true);

        lblUsername.setBounds(70, 80, 100, 20);
        add(lblUsername);
        lblUsername.setVisible(true);

        lblPassword.setBounds(70, 110, 100, 20);
        add(lblPassword);
        lblPassword.setVisible(true);

        lblErrorPortInteger.setBounds(70, 140, 235, 20);
        add(lblErrorPortInteger);
        lblErrorPortInteger.setVisible(false);

        lblErrorEnterFields.setBounds(70, 140, 235, 20);
        add(lblErrorEnterFields);
        lblErrorEnterFields.setVisible(false);

        lblUnknownUser.setBounds(70, 140, 250, 20);
        add(lblUnknownUser);
        lblUnknownUser.setVisible(false);

        lblConnectionFailed.setBounds(70, 140, 250, 20);
        add(lblConnectionFailed);
        lblConnectionFailed.setVisible(false);
    }

    private void addButtons() {
        add(btnConnect);
        btnConnect.setBounds(130, 170, 100, 30);
        btnConnect.setVisible(true);
    }

    private void defineTextFields() {
        txtServerIp = new JTextField();
        txtServerPort = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
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

        lblErrorPortInteger = new JLabel("Server Port should be Integer.");
        lblErrorPortInteger.setForeground(Color.RED);
        lblErrorPortInteger.setHorizontalAlignment(SwingConstants.CENTER);

        lblErrorEnterFields = new JLabel("Please fill all parameters.");
        lblErrorEnterFields.setForeground(Color.RED);
        lblErrorEnterFields.setHorizontalAlignment(SwingConstants.CENTER);

        lblUnknownUser = new JLabel("Incorrect Username or Password.");
        lblUnknownUser.setForeground(Color.RED);
        lblUnknownUser.setHorizontalAlignment(SwingConstants.CENTER);

        lblConnectionFailed = new JLabel("Connection Failed.");
        lblConnectionFailed.setForeground(Color.RED);
        lblConnectionFailed.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void defineButtons() {
        btnConnect = new JButton("Connect");
        btnConnect.setForeground(Color.black);
    }
}
