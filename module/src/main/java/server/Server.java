package server;

import client.Client;
import user.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 4/18/17.
 */
public class Server {
    private static final int PORT = 8090;
    private Socket serverSocket;
    List<Client> clients=new ArrayList<Client>();
    Client client;


    public Boolean isLogin(String username , String password , int serverPort , String serverIp)
    {
        for(Client clientUser : clients)
        {
         if(clientUser.getUser().getUsername().equals(username) && clientUser.getUser().getUsername().equals(password)
                 && client.getServerPort()==serverPort && client.getServerIp().equals(serverIp))
         {
            return true;
         }
     }
        return false;
    }

    public List<Client> getUsers()
    {
        return this.clients;
    }


    private void close()
    {

    }

    private void run()
    {

    }

}
