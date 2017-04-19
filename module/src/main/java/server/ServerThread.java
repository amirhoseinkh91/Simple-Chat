package server;

import client.model.Client;
import com.sun.xml.internal.ws.wsdl.writer.document.Message;

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

    public ServerThread(Client client1 , Client client2)
    {
        this.client=client1;
        this.client=client2;
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
        ServerSocket serverSocket = null;
        Socket socket1=null;
        Socket socket2=null;
        try {
            serverSocket = new ServerSocket(8090);
            socket1=serverSocket.accept();
            socket2=serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
