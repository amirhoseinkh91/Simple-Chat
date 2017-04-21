package runApp;



import server.Server;

import user.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by amir on 4/17/17.
 */
public class SimpleChatMain{

    public static Map<String, User> userList;
    private static int port = 8090;

    public static void main(String[] args) {
        System.out.println("Waiting for clients...");
        initPort(args);
        try {
            Server server = new Server(port);
            server.run();
        } catch (IOException ex) {
            //Logger.getAnonymousLogger().log(Level.SEVERE, null, ex);
        }
    }

    public static void initPort(String[] input)
    {
        if (input.length == 0) {
            port = 8090;
        } else {
            port = Integer.parseInt(input[0]);
        }
    }

    static
    {
        userList=new HashMap<String, User>();
        userList.put("Amir" , new User("Amir" , "Amir"));
        userList.put("erfan" , new User("erfan" , "erfan"));
        userList.put("mojtaba" , new User("mojtaba" , "mojtaba"));
    }

}

