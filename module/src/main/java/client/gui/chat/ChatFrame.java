package client.gui.chat;

import client.gui.ChooseUser.ChooseUserFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by amir on 4/18/17.
 */
public class ChatFrame extends JFrame implements Runnable{
    private JButton btnClose;
    private JButton btnSend;
    private JTextField textField;
    private TextArea senderChatBox;
    private TextArea chatBox;

    static String sendMessage = "";

    public ChatFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(250,10,700,750);
        setBackground(Color.BLACK);
        setTitle("Chat");
        setLayout(null);

        // define buttons
        defineButtons();
        // add buttons
        addButtons();

        defineChatBoxArea();
        addChatBoxArea();

        defineTextField();
        // adds textField for writing message in it
        addTextFields();
        // button Send Clicked and button close clicked
        btnSendClicked();
        closeButtonAction();

    }

    private void defineChatBoxArea() {
        senderChatBox = new TextArea();
        senderChatBox.setEditable(false);
        senderChatBox.setBounds(30,50,310,500);
        senderChatBox.setBackground(Color.WHITE);
        senderChatBox.setForeground(Color.BLACK);
        senderChatBox.setFocusable(false);
        senderChatBox.setBackground(Color.cyan);
        senderChatBox.setFont(new Font("Serif",Font.PLAIN,20));

        chatBox = new TextArea();
        chatBox.setEditable(false);
        chatBox.setBackground(Color.WHITE);
        chatBox.setBounds(370,50,310,500);
        chatBox.setForeground(Color.BLACK);
        chatBox.setFocusable(false);
        chatBox.setBackground(Color.WHITE);
        chatBox.setFont(new Font("Serif",Font.PLAIN,20));
    }

    private void addChatBoxArea() {
        add(senderChatBox);
        senderChatBox.setVisible(true);

        add(chatBox);
        chatBox.setVisible(true);
    }

    private void closeButtonAction(){
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                ChooseUserFrame frame = new ChooseUserFrame();
                frame.setVisible(true);
            }
        });
    }

    private void addButtons(){
        add(btnClose);
        btnClose.setVisible(true);

        add(btnSend);
        btnSend.setVisible(true);
    }

    private void btnSendClicked() {
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                btnSendAction();
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==10) {
                    btnSendAction();
                }
            }
        });
    }

    private void btnSendAction() {
        String text = "";
        text = textField.getText();
        if (!text.equals("") && !text.equals(" ")) {
            senderChatBox.append(text + "\n");
            textField.setText("");
        }
    }

    private void defineTextField() {
        textField = new JTextField();
        textField.setForeground(Color.BLUE);
        textField.setBounds(30,600,550,30);
    }

    private void addTextFields() {
        add(textField);
        textField.setVisible(true);
    }

    private void defineButtons(){
        btnClose = new JButton("close");
        btnClose.setBounds(300,650,100,30);
        btnClose.setForeground(Color.RED);

        btnSend = new JButton("Send");
        btnSend.setBounds(590,600,70,30);
        btnSend.setForeground(Color.BLUE);
    }


    // implementing Runnable run method
    public void run() {
        while (true){
            getMessage();
        }

    }

    public static void getMessage() {
        // String message = ????
    }
}
