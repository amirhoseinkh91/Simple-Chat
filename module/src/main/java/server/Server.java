package server;


import runApp.SimpleChatMain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by eric on 4/18/17.
 */
public class Server {

    public static Map<String, Socket> socketList = new HashMap<>();
    public static List<String> loginNames = new ArrayList<String>();
    ServerSocket serverSocket;

    public Server(int port) throws IOException {

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, null, e);
            throw new IOException("Port isn't open.");
        }
        System.err.println("Server Created!");

    }

    public void run() {
        System.out.println("server is running now");
        listen();
    }

    private void listen() {
        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    count++;
                    Scanner scanner = new Scanner(socket.getInputStream());
                    Formatter formatter = new Formatter(socket.getOutputStream());
                    String[] input = scanner.nextLine().split(" ");
                    if (!"login".equals(input[0])) {
                        socket.close();
                        continue;
                    }
                    if (SimpleChatMain.userList.containsKey(input[1])) {
                        if (SimpleChatMain.userList.get(input[1]).getPassword().equals(input[2])) {
                            socketList.put(input[1], socket);
                            formatter.format("%s%s", "ok", "\n");
                            formatter.flush();
                            new Thread(new ServerThread(socket, socketList)).start();
                            System.out.println("thread is created");
                        } else
                            socket.close();
                    } else
                        socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}




