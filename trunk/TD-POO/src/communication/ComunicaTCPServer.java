package communication;

import listeners.ListenSocket;
import windows.Comunica;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import windows.WindowServer;

public class ComunicaTCPServer {

    final static int port = 9633;
    private static ServerSocket socketServeur;
    private final Socket socketClient;
    private Comunica com;
    private BufferedReader bfin;
    private BufferedWriter bfout;
    private ListenSocket ls;

    public static void main(String[] args) {

        try {
            socketServeur = new ServerSocket(port);
            System.out.println("Server is launched");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossible to launch the server : the port is already in use. \n"
                    + "The application is going to close...", "TCPServer : Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        try {
            WindowServer comServ = new WindowServer();
            while (true) {
                Socket socketClientAccepted = socketServeur.accept();
                ComunicaTCPServer t = new ComunicaTCPServer(socketClientAccepted);
                t.launch();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossible to accept the client",
                    "TCPServer : Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ComunicaTCPServer(Socket socketClientAccepted) {
        this.socketClient = socketClientAccepted;
    }

    public void launch() {
        String message = "";
        try {
            System.out.println("Connexion accepted by the server with : " + socketClient.getInetAddress());

            bfin = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            bfout = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            ls = new ListenSocket(bfin);
            ls.start();
            com = new Comunica("Server : Communication with Client", ls, bfout, socketClient);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problem getting InputStream", "TCPServer : Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
