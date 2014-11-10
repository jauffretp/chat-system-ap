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
	
	public ComunicaTCPClient(String adresse)throws IOException {
		try {
			socketClient = new Socket(InetAddress.getByName(adresse), port);
						
			System.out.println("Client connected with : "+socketClient.getInetAddress());
 
			bfin  =  new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			bfout =  new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                        
                         Comunica fenetre = new Comunica(bfin,bfout) ; 			
		} 
                
                catch (Exception e) {
			e.printStackTrace();
		}
	}
}
