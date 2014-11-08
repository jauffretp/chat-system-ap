package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.*;
import java.io.*;

public class ComunicaTCPClient{

	private final static int port = 9632;
	private Socket socketClient ;  
	private BufferedReader bfin ; 
	private BufferedWriter bfout ;
	private Comunica fenetreCom ; 
	
	public ComunicaTCPClient(Comunica fenetreCom, String adresse)throws IOException {
		try {
			socketClient = new Socket(InetAddress.getByName(adresse), port);
			System.out.println("Connexion avec : "+socketClient.getInetAddress());
 
			bfin  =  new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			bfout =  new BufferedWriter(new BufferedWriter( new OutputStreamWriter(socketClient.getOutputStream())));
	        
			this.fenetreCom = fenetreCom ; 
			this.fenetreCom.setReader(bfin) ; 
			this.fenetreCom.setWriter(bfout) ; 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
