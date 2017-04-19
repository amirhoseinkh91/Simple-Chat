package server;


import client.model.Client;
import com.sun.xml.internal.ws.wsdl.writer.document.Message;

import java.util.ArrayList;
import java.util.List;

import static java.io.FileDescriptor.in;
import static java.io.FileDescriptor.out;

/**
 * Created by eric on 4/18/17.
 */
public class Server extends Thread{
    ServerThread serverThread;
    ClientHandler clientHandler;
    static List<Client> clients = new ArrayList<Client>();
    static List<String> loginNames = new ArrayList<String>();

    @Override
    public void run() {
        super.run();
        while (true){
            try{
                Message msg = (Message) serverThread.getStreamIn().readObject();
                //clientHandler.handle(serverThread.getID(), msg);
            }
            catch(Exception ioe){
                System.out.println(serverThread.getID() + " ERROR reading: " + ioe.getMessage());
                clientHandler.remove(serverThread.getID());
                stop();
            }
        }
    }
}
