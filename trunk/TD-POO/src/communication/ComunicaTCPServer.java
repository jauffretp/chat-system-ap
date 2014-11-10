package communication;

import java.net.*;
import java.io.*;

public class ComunicaTCPServer extends Thread {

    final static int port = 9632;
    private ServerSocket socketServeur;
    private Comunica fenetreCom;
    private BufferedReader bfin;
    private BufferedWriter bfout;

    public ComunicaTCPServer() {
        try {
            socketServeur = new ServerSocket(port);
            System.out.println("Server is launched");

        } catch (Exception e) {
            System.err.println("Fail launching server");
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            String message = "";
            Socket socketClient = null;
            try {
                socketClient = socketServeur.accept();
                System.out.println("Connexion accepted by the server with : " + socketClient.getInetAddress());

                bfin = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                bfout = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                ListenSocket ls = new ListenSocket(bfin);
                Comunica fenetre = new Comunica(ls, bfout);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
