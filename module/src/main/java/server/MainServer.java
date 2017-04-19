package server;

/**
 * Created by eric on 4/18/17.
 */
public class MainServer {
    private static final int port = 8090;

//    public static void main(String[] args)
//    {
    public void starter(){
        ClientHandler clientHandler = new ClientHandler(port);
        clientHandler.run();
    }
//    }


}
