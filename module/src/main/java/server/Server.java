package server;

import client.Client;
import user.User;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 4/18/17.
 */
public class Server {

    private static final int PORT = 8090;


    private Socket serverSocket;
    List<User> users=new ArrayList<User>();

    public Boolean isLogin(String username , String password , int serverPort , String serverIp)
    {
        Client client = new Client(serverIp , serverPort);
     for(User user : users)
     {
         if(user.getUsername().equals(username) && user.getPassword().equals(password)
                 && client.getServerPort()==serverPort && client.getServerIp().equals(serverIp))
         {
            return true;
         }
     }
        return false;
    }

    public List<User> getUsers()
    {
        return this.users;
    }

    public void onlineUsers()
    {

    }

    private void disconnect()
    {

    }
    private void close()
    {

    }

    private void run()
    {

    }

}
