package communication;

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
    }
    
    
    public ComunicaTCPClient(String adresse)  {
        try {
            socketClient = new Socket(InetAddress.getByName(adresse), port);

            System.out.println("Client connected with : " + socketClient.getInetAddress());

            bfin = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            bfout = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            ls = new ListenSocket(bfin);
            Comunica fenetre = new Comunica(ls, bfout); 
        } 
        catch (Exception e) {
            JOptionPane errorServerJDialog = new JOptionPane();
            errorServerJDialog.showMessageDialog(null, "Please start the server first", "TCPClient : Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
