import client.gui.login.LoginFrame;
import client.model.Client;
import server.ClientHandler;
import server.MainServer;
import server.Server;
import server.ServerThread;
import user.User;

/**
 * Created by amir on 4/17/17.
 */
public class SimpleChatMain {
    public static void main(String[] args) {
        Client client1 = new Client("localhost",8090,new User("amir","amir"));
        Client client2 = new Client("localhost",8090,new User("erfan","erfan"));
        Server.clients.add(client1);
        Server.clients.add(client2);

        ServerThread serverThread = new ServerThread(client1);
        serverThread.start();
    }

}
