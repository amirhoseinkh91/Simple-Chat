package server;


import user.User;

import java.io.*;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by eric on 4/18/17.
 */
public class ClientHandler extends Thread {
    private User currentUser;
    private Socket currentSocket;
    private BufferedReader in;
    private PrintWriter out;
    Server server = new Server();

    public ClientHandler(User currentUser) {
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
                    String user = currentUser.getUsername();
                    user = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (currentUser == null || currentUser.getUsername().trim().length() == 0) {
                    return;
                }
                synchronized (server.getUsers()) {
                    if (!server.getUsers().contains(currentUser)) {
                        server.getUsers().add(currentUser);
                        break;
                    }
                }

            }
        }
    }
}
