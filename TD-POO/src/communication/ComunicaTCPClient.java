package communication;

import listeners.ListenSocket;
import windows.Comunica;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ComunicaTCPClient {

    private final static int port = 9633;
    private Socket socketClient;
    private BufferedReader bfin;
    private BufferedWriter bfout;
    private Comunica Com;
    private ListenSocket ls;

    public static void main(String[] args) {
        ComunicaTCPClient client = new ComunicaTCPClient("localhost");
        client.launch();
    }

    public ComunicaTCPClient(String adresse) {
        try {
            socketClient = new Socket(InetAddress.getByName(adresse), port);

            System.out.println("Client connected with : " + socketClient.getInetAddress());

            bfin = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            bfout = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please start the server first", "TCPClient : Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1) ; 
        }
    }

    public void launch() {
        ls = new ListenSocket(bfin);
        ls.start();
        Comunica fenetre = new Comunica("Client", ls, bfout, socketClient);
    }
}
