package server;

import client.model.Client;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by eric on 4/19/17.
 */
public class ServerThread implements Runnable{

    private Scanner scanner;
    private Formatter formatter;
    private Socket socket;
    private Client client;
    private Map<String , Socket> socketMap;


    public ServerThread(Socket socket , Map<String , Socket> socketList)
    {
        this.socket=socket;
        try {
            Scanner scanner=new Scanner(socket.getInputStream());
            Formatter formatter=new Formatter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socketMap=socketList;

    }

    @Override
    public void run() {

        while (true) {
            String nextline = scanner.nextLine();
            if (nextline == null) {
                continue;
            }
            if("close".equals(nextline))
            {
                formatter.format("%s%s" , "ok" , "\n");
                formatter.flush();
                scanner.close();
                formatter.close();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            if ("LIST".equals(nextline)) {
                StringBuilder builder = new StringBuilder(Integer.toString(socketMap.size())).append(" ");
                for (Map.Entry<String, Socket> socketEntry : socketMap.entrySet()) {
                    builder.append(socketEntry.getKey()).append(" ");
                }
                formatter.format("%s%s", builder.toString().trim(), "\n");
                formatter.flush();
            }
            if ("connect".equals(nextline)) {

                System.err.println("Client Ready to chat!");
                System.err.println(socketMap.get(nextline.split(" ")[1]));
                new Thread(new ChatThread(socket,
                        socketMap.get(nextline.split(" ")[1]))).start();
                formatter.format("%s%s", "OK", "\n").flush();
                new ChatThread(socketMap.get(nextline.split(" ")[1]), socket).run();
                System.err.println("Chat Over!");
            }
        }
    }
}
