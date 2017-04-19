package server;

import client.model.Client;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eric on 4/19/17.
 */
public class ServerThread extends Thread{

    private ClientHandler clientHandler = null;
    private Client client;
    private int ID = -1;
   // private String username = "";
    private ObjectInputStream streamIn = null;
    private ObjectOutputStream streamOut = null;


    public ServerThread(Client client) {
        super();
        this.client=client;
    }

   public void sendMsg(Message msg) {
        try {
            streamOut.writeObject(msg);
            streamOut.flush();
        } catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }

    @Override
    public void run() {
        super.run();
    }

    public int getID() {
        return this.ID;
    }

    public ObjectInputStream getStreamIn() {
        return streamIn;
    }

    public void open() throws IOException {
        streamOut = new ObjectOutputStream(client.getSocket().getOutputStream());
        streamOut.flush();
        streamIn = new ObjectInputStream(client.getSocket().getInputStream());
    }

    public void close() throws IOException {
        if (client.getSocket() != null) client.getSocket().close();
        if (streamIn != null) streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}
