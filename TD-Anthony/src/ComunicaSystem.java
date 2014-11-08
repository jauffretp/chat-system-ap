import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import communication.*;

public class ComunicaSystem {


	public static void main(String[] args) {
		try {
			
//			Comunica fenetre1 = new Comunica(new BufferedReader(new FileReader("A2B.txt")),new BufferedWriter(new FileWriter("B2A.txt")));
//			Comunica fenetre2 = new Comunica(new BufferedReader(new FileReader("B2A.txt")),new BufferedWriter(new FileWriter("A2B.txt")));
							
			Comunica fenetre1 = new Comunica (null, null);
			Comunica fenetre2 = new Comunica (null, null);
			ComunicaTCPServer server = new ComunicaTCPServer() ; 
			server.start(); 
			ComunicaTCPClient client1 = new ComunicaTCPClient(fenetre1, "localhost") ; 
			ComunicaTCPClient client2 = new ComunicaTCPClient(fenetre2, "localhost") ; 
		
		
		}
		catch(Exception e) {
			System.err.println("Le système a recontré une erreur : " )  ;
			e.printStackTrace();
		}


	}	


}
