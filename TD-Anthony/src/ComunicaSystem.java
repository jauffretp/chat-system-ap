import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class ComunicaSystem {

	
	public static void main(String[] args) {
		FileReader a2bReader ;
		FileReader b2aReader ;
		FileWriter a2bWriter ;
		FileWriter b2aWriter ;
		
		try {
			a2bReader = new FileReader("A2B.txt");
			a2bWriter = new FileWriter("A2B.txt");			
			b2aReader = new FileReader("B2A.txt");
			b2aWriter = new FileWriter("B2A.txt");
			
			Comunica fenetre1 = new Comunica (new BufferedReader(a2bReader), new BufferedWriter(b2aWriter));
			Comunica fenetre2 = new Comunica (new BufferedReader(b2aReader), new BufferedWriter(a2bWriter));
				
		}
		catch(Exception e) {
			System.err.println("Le système a recontré une erreur : " )  ;
			e.printStackTrace();
		}
		
	
	}	
	
	
}
