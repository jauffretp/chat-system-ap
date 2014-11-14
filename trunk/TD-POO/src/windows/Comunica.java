package windows;

import listeners.ListenSocket;
import listeners.JButtonRefreshListener;
import listeners.JButtonSendListener;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class Comunica extends JFrame implements WindowListener {

    private final Socket socketClient;
    private final ListenSocket ls;
    private final BufferedWriter writer;
    private JButton bReceive;
    private JButton bSend;
    private JLabel lmessrec;
    private JLabel lmesssend;
    private JTextArea textRec;
    private JTextArea textToSend;
    private JScrollPane zoneScrolableSend;
    private JScrollPane zoneScrolableRec;
    private final String messageReceived;

    public Comunica(String title, ListenSocket ls, BufferedWriter writer, Socket socketClient) {
        super(title);
        this.socketClient = socketClient;
        this.writer = writer;
        this.ls = ls;
        this.messageReceived = "";
        initComponents();
        this.setSize(500, 600); // resize the windows
        this.setLocationRelativeTo(null); // put the windows in the middle of the screen 
    }

    /**
     * Initializes the Fenetre components
     */
    private void initComponents() {

        // the labels
        lmessrec = new JLabel("Received message : ");
        lmesssend = new JLabel("Message to send : ");

        // 2 new text fields
        textToSend = new JTextArea(50, 50);
        zoneScrolableSend = new JScrollPane(textToSend);
        //
        textRec = new JTextArea(50, 50);
        textRec.setEditable(false);
        zoneScrolableRec = new JScrollPane(textRec);

        // buttons : creation and association with ActionListener
        bReceive = new JButton("Refresh");
        bReceive.addActionListener(new JButtonRefreshListener(ls));
        ls.setArea(textRec);
        //socketClient
        bSend = new JButton("Send");
        bSend.addActionListener(new JButtonSendListener(writer, textToSend));

        // configures the JFrame layout using a border layout
        this.setLayout(new GridLayout(3, 2));

        // places the components in the layout
        this.getContentPane().add(lmesssend);
        this.getContentPane().add(zoneScrolableSend);
        this.getContentPane().add(bSend);
        this.getContentPane().add(bReceive);
        this.getContentPane().add(lmessrec);
        this.getContentPane().add(zoneScrolableRec);

        addActionforEnterKeyReleased();
        this.addWindowListener(this);

        // packs the fenetre: size is calculated
        // regarding the added components
        this.pack();

        // the JFrame is visible now
        this.setVisible(true);
    }

    /**
     * Add an action when the key "Enter" is released
     */
    public void addActionforEnterKeyReleased() {
        textToSend.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    bSend.doClick();
                }
            }
        });
    }

    public void close() {
        try {
            ls.setStop();
            socketClient.close();
            ls.interrupt();
            this.dispose();
            // System.exit(0);
        } catch (IOException ex) {
            JOptionPane errorJDialog = new JOptionPane();
            errorJDialog.showMessageDialog(null, "Impossible to close", "Comunica : Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Window Listener implementation      
    @Override
    // when we click on the close button, the system exits     
    public void windowClosing(WindowEvent e) {
        close();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
