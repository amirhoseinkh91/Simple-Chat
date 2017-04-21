package client.controller.message;

import client.model.Client;

import java.io.IOException;

/**
 * Created by amir on 4/21/17.
 */
public class MessageSender extends Message implements Runnable {

    public MessageSender(Client client) throws IOException {
        super(client);
    }

    // run for Runnable Interface
    public void run() {
        while (true) {
            sendMessage();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void login() {
        String msg = "LOGIN " + getClient().getUser().getUsername() + " " + getClient().getUser().getPassword() + "\n";
        setOutputMessage(msg);
        getFormatter().format(msg);
        getFormatter().flush();
    }

    public void list() {
        String msg = "LIST";
        getFormatter().format(msg);
        getFormatter().flush();
    }

    public void connect() {
        String msg = "CONNECT " + getClient().getUser().getUsername();
        getFormatter().format(msg);
        getFormatter().flush();
    }

    public void close() {
        String msg = "CLOSE";
        getFormatter().format(msg);
        getFormatter().flush();
    }

    public void sendMessage() {
        String msg = getClient().getUser().getUsername() + ": " + getOutputMessage() + "\n";
        getFormatter().format(msg);
        getFormatter().flush();
    }

}
