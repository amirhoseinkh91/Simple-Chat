package server;

import java.io.IOException;

/**
 * Created by eric on 4/18/17.
 */
public class MainServer {
    private static final int port = 8090;

    public static void main(String[] args)
    {
        try {
            Server server=new Server();
        } catch (IOException e) {
            e.getMessage();
        }
    }


}
