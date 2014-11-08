package communication;

import java.net.*;
import java.io.*;

public class ComunicaTCPServer extends Thread {

	final static int port = 9632;
	private ServerSocket socketServeur ; 
	private BufferedReader bfin ; 
	private BufferedWriter bfout ;
	private Comunica fenetreCom ; 

	public ComunicaTCPServer() {
		try {
			socketServeur = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void run() {
		while (true) {
		String message = "";
		Socket socketClient = null ;
		try {
			socketClient = socketServeur.accept();
			System.out.println("Connexion acceptée par le serveur avec : "+socketClient.getInetAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}			
	}
}
