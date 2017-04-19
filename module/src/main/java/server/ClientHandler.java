package server;



import client.model.Client;
import user.User;

import javax.xml.transform.sax.SAXSource;
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
import java.util.StringTokenizer;

import static server.Server.clients;
import static server.Server.loginNames;

/**
 * Created by eric on 4/18/17.
 */
public class ClientHandler implements Runnable {
    private Client currentUser;
    private ServerThread clientsThreads[];
    private int port=8090;
    private ServerSocket serverSocket;
    Thread thread = null;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    private Socket clientSocket;
    int clientCount=0;




    public ClientHandler(Client currentUser) {
        this.currentUser = currentUser;
    }
    public ClientHandler(int port)
    {
        this.port=port;
        this.clientsThreads=new ServerThread[100];
        try {
            serverSocket = new ServerSocket(port);
            start();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private void start()
    {
        if(thread==null)
        {
            thread=new Thread();
            thread.start();
        }
    }

    private void stop()
    {
        if(thread!=null)
        {
            thread.stop();
            thread=null;
        }
    }

    private int findClient(int id)
    {
        for(Client client : clients)
        {
            if(client.getUser().getId()==id)
            {
                return id;
            }
        }
        return -1;
    }


    private void addThread(Socket socket)
    {
        if (clientCount < clients.size()){
            clients.get(clientCount) = new server(this, socket);
            try{
                clients[clientCount].open();
                clients[clientCount].start();
                clientCount++;
            }
            catch(IOException ioe){
                ui.jTextArea1.append("\nError opening thread: " + ioe);
            }
        }
        else{
            ui.jTextArea1.append("\nClient refused: maximum " + clients.length + " reached.");
        }
    }

    private void connectUser(Client client) throws IOException {
        if(isValid(currentUser)) {
            this.serverSocket.accept();
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
        return clients;
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
        while (thread != null){
            try{
                addThread(serverSocket.accept());
            }
            catch(Exception ioe){
                System.out.println("\nServer accept error: \n");
            }
        }

    }

    public void sendUserList(String user) {
        for (int i = 0; i < this.clientCount; i++) {
            findUserThread(user).sendMsg(cl);
        }
    }
    public ServerThread findUserThread(String usr){
        for(int i = 0; i < clientCount; i++){
            if(clients[i].username.equals(usr)){
                return clients[i];
            }
        }
        return null;
    }
}
