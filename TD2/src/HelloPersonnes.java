
import perso.*;
public class HelloPersonnes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Personne[] Tab = new Personne[3];
		Tab[0] = new Personne("Jean", 45) ;
		Tab[1] = new Etudiant("Paul" , 22, 4.5f);
		Tab[2] = new Enseignant("Pierre", 54, 35);
		for(int i = 0; i<2 ; ++i) {
			System.out.println(Tab[i]);
		
		}
	}

}
