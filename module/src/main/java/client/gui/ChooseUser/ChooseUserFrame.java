package client.gui.ChooseUser;

import client.gui.chat.ChatFrame;
import client.gui.login.LoginFrame;
import client.model.Client;
import server.ClientHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by amir on 4/19/17.
 */
public class ChooseUserFrame extends JFrame {

    static boolean isClosed = false;
    private List<Client> idleHosts;
    private JButton btnShowIdles;
    private JButton btnConnectToHost;
    private JButton btnClose;
    private JList list;
    private String username;
    private Client client;

    public ChooseUserFrame(Client client) {
        this.client = client;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(300, 100, 500, 500);
        setTitle("Choose Host");
        setLayout(null);

        defineButtons();
        defineList();
        addButtons();
        addList();

        addActionsToButtons();
        addActionsToList();
    }

    private void addActionsToList() {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                username = (String) list.getSelectedValue();
            }
        });
    }

    // setter for List<Client> idleHosts
    private void setIdleHosts() {
        this.idleHosts = ClientHandler.getList();
    }

    private void addActionsToButtons() {
        showIdleButtonAction();
        connectToHostButtonAction();
        closeButtonAction();
    }

    private void closeButtonAction() {
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ((Window) getRootPane().getParent()).dispose();
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            }
        });
    }

    private void showIdleButtonAction() {
        btnShowIdles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setIdleHosts();
                showListOfIdleHosts();
            }
        });
    }

    private void showListOfIdleHosts() {
        list.clearSelection();
        DefaultListModel listModel = new DefaultListModel();
        list.setModel(listModel);
        for (Client client : idleHosts) {
            listModel.addElement(client.getUser().getUsername());
        }
    }

    private void connectToHostButtonAction() {
        btnConnectToHost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                connectToSelectedClient();
                openChatFrame();
                ((Window) getRootPane().getParent()).dispose();
            }
        });
    }

    private void connectToSelectedClient() {
        List<Client> clients = ClientHandler.getList();
        for (Client clt : clients) {
            if(clt.getUser().getUsername().equals(this.client)){
                ClientHandler.clientConnections(clt, this.client);
            }
        }
    }

    private void addList() {
        add(list);
        list.setVisible(true);
    }

    private void defineList() {
        list = new JList();
        list.setBounds(10, 20, 200, 400);
    }

    private void addButtons() {
        add(btnConnectToHost);
        btnConnectToHost.setVisible(true);

        add(btnShowIdles);
        btnShowIdles.setVisible(true);

        add(btnClose);
        btnClose.setVisible(true);
    }

    private void defineButtons() {
        btnShowIdles = new JButton("Show Online Idle Hosts");
        btnShowIdles.setBounds(240, 70, 230, 30);

        btnConnectToHost = new JButton("Connect To This Host...");
        btnConnectToHost.setBounds(240, 140, 230, 30);

        btnClose = new JButton("Close");
        btnClose.setBounds(310, 400, 80, 30);
    }

    private void openChatFrame() {
        ChatFrame chatFrame = new ChatFrame();
        chatFrame.setVisible(true);
    }
}
