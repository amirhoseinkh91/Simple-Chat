package client;

import user.User;

import java.io.IOException;
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
    }

    public Client(){

    }

    // setter getters


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Formatter getOutput() {
        return output;
    }

    public void setOutput(Formatter output) {
        this.output = output;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void start() {
        try {
            socket = new Socket(serverIp, serverPort);
            input = new Scanner(socket.getInputStream());
            output = new Formatter(socket.getOutputStream());
            String recieved;
            do {
                next = input.next();
                output.format(next + "\n");
                // show output on my device
                // showOutput method
                output.flush();
                recieved = input.next();
                // shoe input
                // showInput method

            } while (!recieved.equals("exit"));
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
