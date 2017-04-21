/*
package server;


import client.model.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static server.Server.clients;

*/
/*
*
 * Created by eric on 4/18/17.
*//*





public class ClientHandler implements Runnable {
    Thread thread = null;
    private Client currentUser;
    private ServerThread clientsThreads[];
    private int port = 8090;
    private ServerSocket serverSocket;
    private Socket socket;
    //    DataInputStream dataInputStream;
//    DataOutputStream dataOutputStream;
    private int clientCount = 0;


    public ClientHandler(Socket socket, Client currentUser) {

        this.currentUser = currentUser;
        this.socket = socket;
    }

    public ClientHandler(int port) {
        this.port = port;
        this.clientsThreads = new ServerThread[100];
        try {
            serverSocket = new ServerSocket(port);
            start();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public static List<Client> getList() {
        return clients;
    }

    public static void clientConnections(Client sender, Client reciever) {
        ServerThread serverThread = new ServerThread(sender, reciever);

    }

    private void start() {
        if (thread == null) {
            thread = new Thread();
            try {
                connectUser(currentUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }

    public void login(String username, String password) {
        for (Client client : clients) {
           // if (client)
        }
    }

    public boolean isLogin(String username) {
        for (Client client : clients) {
            if (client.getUser().getUsername().equals(username))
                return true;
        }
        return false;
    }

    public boolean isBusy() {
        return false;
    }

    public void connect() throws IOException {
        //search database
        serverSocket.accept();
    }

    private void stop() {
        if (thread != null) {
            thread.stop();
            closeConnection();
            thread = null;
        }
    }

    private int findClient(int id) {
        for (Client client : clients) {
            if (client.getUser().getId() == id) {
                return id;
            }
        }
        return -1;
    }

    private void connectUser(Client client) throws IOException {
        this.serverSocket.accept();
        addThread();
    }

    private void checkOnlineUsers() throws IOException {
        if (isValid()) {
            for (Client client : clients) {
                if (client.getSocket().isConnected()) {
                    //pass to GUI
                    getList();
                }
            }
        }
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

    public void closeConnection() {
        try {
            currentUser.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients.remove(currentUser);
    }

    public boolean isValid() throws IOException {
        for (int i = 0; i < clients.size(); i++)
            if (clients.contains(this.currentUser)) {
                connectUser(currentUser);
                return true;
            }
        return false;
    }

    public void run() {
        while (thread != null) {
            try {
                addThread();
            } catch (Exception ioe) {
                System.out.println("\nServer accept error: \n");
            }
        }

    }

    public ServerThread findUserThread(String usr) {
        for (int i = 0; i < clientCount; i++) {
            if (clientsThreads[i].getName().equals(usr)) {
                return clientsThreads[i];
            }
        }
        return null;
    }

    public void addThread() {
        if (clientCount < clientsThreads.length) {
            clientsThreads[clientCount] = new ServerThread(currentUser);
            try {
                clientsThreads[clientCount].open();
                clientsThreads[clientCount].start();
                serverSocket.accept();
                clientCount++;
            } catch (IOException ioe) {
                System.out.println("\nError opening thread: ");
            }
        } else {
            System.out.println("\nClient refused: maximum " + clientsThreads.length + " reached.");
        }
    }

    public synchronized void remove(int ID) {
        int pos = findClient(ID);
        if (pos >= 0) {
            ServerThread toTerminate = clientsThreads[pos];
            System.out.println("\nRemoving client thread " + ID + " at " + pos);
            if (pos < clientCount - 1) {
                for (int i = pos + 1; i < clientCount; i++) {
                    clientsThreads[i - 1] = clientsThreads[i];

                }
            }
            clientCount--;
            try {
                toTerminate.close();
            } catch (IOException ioe) {
                System.out.println("\nError closing thread: ");
            }
            toTerminate.stop();
        }
    }
}
*/
