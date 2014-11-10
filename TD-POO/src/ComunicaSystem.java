import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import communication.*;

public class ComunicaSystem {


	public static void main(String[] args) {
		try {
                    
			// with files : 
//			Comunica fenetre1 = new Comunica(new BufferedReader(new FileReader("A2B.txt")),new BufferedWriter(new FileWriter("B2A.txt")));
//			Comunica fenetre2 = new Comunica(new BufferedReader(new FileReader("B2A.txt")),new BufferedWriter(new FileWriter("A2B.txt")));
		
                        // TCP Connection             
			ComunicaTCPServer server = new ComunicaTCPServer() ; 
			server.start(); 
			ComunicaTCPClient client = new ComunicaTCPClient("localhost") ;
                        
		}
                
		catch(Exception e) {
			System.err.println("There is an error in the main funtion " )  ;
			e.printStackTrace();
		}


	}	


}
