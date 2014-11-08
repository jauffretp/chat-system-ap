import perso.*;
import product.*;
public class HelloChercheur {

	
	public static void main (String[] args){
		IChercheur chercheur = new EnseignantChercheur("Aika",20,37);
		chercheur.ajouterPublication(new Publication("La conception objet",2003));
		chercheur.ajouterPublication(new Publication("La programmation objet",2004));
		
		 System.out.println(chercheur);
		 System.out.println("test");
		 System.out.println("Test Simon");
	}

}
