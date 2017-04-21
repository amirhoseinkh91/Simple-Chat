package client.model;

import user.User;

import java.io.Serializable;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by amir on 4/18/17.
 */
public class Client implements Serializable {

    private Socket socket;
    private Scanner input;
    private Formatter output;
    private String serverIp;
    private User user;

    // constructor
    public Client(String serverIp, User user) {
        this.serverIp = serverIp;
        this.user = user;
    }

    public Client() {

    }

    // setter getters
    public User getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getServerIp() {
        return serverIp;
    }


}
