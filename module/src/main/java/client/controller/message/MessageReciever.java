package client.controller.message;

import client.model.Client;

import java.io.IOException;

/**
 * Created by amir on 4/21/17.
 */
public class MessageReciever extends Message implements Runnable {


    public MessageReciever(Client client) throws IOException {
        super(client);
    }

    public void run() {
        while (true) {
            recieveMessage();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String recieveMessage() {
        return getScanner().nextLine();
    }

    public boolean isValid() {
        String validate = recieveMessage();
        if (validate.equals("OK")) {
            return true;
        } else {
            return false;
        }
    }
}
