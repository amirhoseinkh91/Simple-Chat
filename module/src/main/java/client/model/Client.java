package client.model;

import user.User;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by amir on 4/18/17.
 */
public class Client {

    String next;
    private Socket socket;
    private Scanner input;
    private Formatter output;
    private String serverIp;
    private int serverPort = 8090;
    private User user;

    // constructor
    public Client(String serverIp, int serverPort, User user) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.user = user;
//        start();
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

    public int getServerPort() {
        return serverPort;
    }


    public void start() {
        try {
            socket = new Socket(serverIp, serverPort);
            input = new Scanner(socket.getInputStream());
            output = new Formatter(socket.getOutputStream());
            String recieved;
            do {
                recieved = input.nextLine();
                output.format(recieved + "\n");
                // show output on my device
                // showOutput method
                output.flush();
                recieved = input.next();
                // shoe input
                // showInput method

            } while (!recieved.equals("exit"));
        } catch (ConnectException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            output.close();
            input.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
