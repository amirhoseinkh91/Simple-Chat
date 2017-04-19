package server;



import client.model.Client;
import user.User;
import java.io.BufferedReader;
import java.io.*;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by eric on 4/18/17.
 */
public class ClientHandler extends Thread {
    private Client currentUser;
    //private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    private ServerSocket serverSocket;
//    private BufferedReader in;
//    private PrintWriter out;
    List<Client> clients = new ArrayList<Client>();
    Server server = new Server();


    public ClientHandler(Client currentUser) {
        this.currentUser = currentUser;
    }

    private void connectUser(Client client) throws IOException {
        if(isValid(currentUser)) {
           serverSocket.accept();
           clients.add(client);
        }
    }
    private void checkOnlineUsers()
    {
        if(isValid(currentUser))
        {
            for(Client client : clients)
            {
                if(client.getSocket().isConnected())
                {
                    //pass to GUI
                    getList();
                }
            }
        }
    }

    private List<Client> getList()
    {
        return this.clients;
    }

    public void closeConnection()
    {
        try {
            currentUser.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients.remove(currentUser);
    }
//    private void checkOfflineUsers()
//    {
//        if(isValid(currentUser))
//        {
//            for(Client client : clients)
//            {
//                if(client.getSocket().isClosed())
//                {
//                    this.clients.remove(client);
//                }
//            }
//        }
//        if(!isValid(currentUser))
//        {
//            for(Client client : clients)
//            {
//                try {
//                    client.getSocket().close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    private boolean isValid(Client client)
    {
        for(int i=0;i<clients.size();i++)
            if (clients.contains(client)) {
                return true;
            }
        return false;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
                        + "\nGoodbye!");
                server.close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                continue;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
