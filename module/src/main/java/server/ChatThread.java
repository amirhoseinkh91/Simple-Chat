package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by eric on 4/21/17.
 */
public class ChatThread implements Runnable {

    private final Socket firstSocket;
    private final Socket secondSocket;

    public ChatThread(Socket firstSocket, Socket secondSocket) {
        this.firstSocket = firstSocket;
        this.secondSocket = secondSocket;
    }

    @Override
    public void run() {

        try(Scanner scanner = new Scanner(firstSocket.getInputStream());
        Formatter formatter=new Formatter(secondSocket.getOutputStream()))
        {
            formatter.format("").flush();
            while (true) {
                String line = scanner.nextLine();
                formatter.format("%s%s", line, "\n");
                formatter.flush();
            }
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, null, e);
        }

    }

}
