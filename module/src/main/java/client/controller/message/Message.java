package client.controller.message;

import client.model.Client;

import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by amir on 4/21/17.
 */
public class Message {

    private Client client;
    private String outputMessage;
    private Formatter formatter;
    private Scanner scanner;
    private String inputMessage;

    public Message (Client client) throws IOException{
        this.client = client;
        formatter = new Formatter(client.getSocket().getOutputStream());
        scanner = new Scanner(client.getSocket().getInputStream());
    }

    // setter getter
    public String getOutputMessage() {
        return outputMessage;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }
}
