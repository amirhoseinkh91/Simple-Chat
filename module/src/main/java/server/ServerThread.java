package server;

import sun.plugin2.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eric on 4/19/17.
 */
public class ServerThread extends Thread {

    private ClientHandler clientHandler = null;
    private Socket socket = null;
    private int ID = -1;
    private String username = "";
    private ObjectInputStream streamIn = null;
    private ObjectOutputStream streamOut = null;


    public ServerThread(ClientHandler clientHandler, Socket socket) {
        super();
        this.clientHandler = clientHandler;
        this.socket = socket;
        this.ID = socket.getPort();
        Server server = new Server();
        server.run();
    }

    public void sendMsg(Message msg) {
        try {
            streamOut.writeObject(msg);
            streamOut.flush();
        } catch (IOException ex) {
            System.out.println("Exception [SocketClient : send(...)]");
        }
    }

    public int getID() {
        return this.ID;
    }

    public ObjectInputStream getStreamIn() {
        return streamIn;
    }

    public void open() throws IOException {
        streamOut = new ObjectOutputStream(socket.getOutputStream());
        streamOut.flush();
        streamIn = new ObjectInputStream(socket.getInputStream());
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (streamIn != null) streamIn.close();
        if (streamOut != null) streamOut.close();

    }
}
