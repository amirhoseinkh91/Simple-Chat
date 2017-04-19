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
            try {
                connectUser(currentUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }

    private void stop()
    {
        if(thread!=null)
        {
            thread.stop();
            closeConnection();
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

    public boolean isValid(Client client)
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

    public ServerThread findUserThread(String usr){
        for(int i = 0; i < clientCount; i++){
            if(clientsThreads[i].getName().equals(usr)){
                return clientsThreads[i];
            }
        }
        return null;
    }
    private void addThread(Socket socket){
        if (clientCount < clientsThreads.length){
            clientsThreads[clientCount] = new ServerThread(this, socket);
            try{
                clientsThreads[clientCount].open();
                clientsThreads[clientCount].start();
                clientCount++;
            }
            catch(IOException ioe){
                System.out.println("\nError opening thread: ");
            }
        }
        else{
            System.out.println("\nClient refused: maximum " + clientsThreads.length + " reached.");
        }
    }
    public synchronized void remove(int ID){
        int pos = findClient(ID);
        if (pos >= 0){
            ServerThread toTerminate = clientsThreads[pos];
            System.out.println("\nRemoving client thread " + ID + " at " + pos);
            if (pos < clientCount-1){
                for (int i = pos+1; i < clientCount; i++){
                    clientsThreads[i-1] = clientsThreads[i];
                }
            }
            clientCount--;
            try{
                toTerminate.close();
            }
            catch(IOException ioe){
                System.out.println("\nError closing thread: ");
            }
            toTerminate.stop();
        }
    }
}
