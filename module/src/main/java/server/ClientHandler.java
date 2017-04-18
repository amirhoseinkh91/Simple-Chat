package server;


import client.Client;
import user.User;

import java.io.*;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

/**
 * Created by eric on 4/18/17.
 */
public class ClientHandler extends Thread {
    private Client currentUser;
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    private Socket currentSocket;
    private BufferedReader in;
    private PrintWriter out;
    Server server = new Server();

    public ClientHandler(Client currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void run() {
        try {
            try {
                in = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out = new PrintWriter(currentSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                out.println("username");
                try {
                    String user = currentUser.getUser().getUsername();
                    user = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (currentUser.getUser().getUsername() == null ||
                        currentUser.getUser().getUsername().trim().length() == 0) {
                    return;
                }
                synchronized (server.getUsers()) {
                    if (!server.getUsers().contains(currentUser.getUser())) {
                        server.getUsers().add(currentUser.getUser());
                        break;
                    }
                }

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
                if (currentSocket != null) {
                    server.getUsers().remove(currentSocket);

                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    currentSocket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}
