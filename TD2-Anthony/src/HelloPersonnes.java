/**
 * 
 */

import perso.* ; 

public class HelloPersonnes {

	/**
	 * @param args : Command line 
	 */
	
	
	public static void main(String[] args) {
		Personne[] TabPersonne = new Personne[3] ; // polymorphism (in this tab we can have Personne, Etudiant or Enseignant) 
		TabPersonne[0] = new Personne("Anthony", 20) ;
		TabPersonne[1] = new Etudiant("Arthur", 21, 16.0f) ; // dynamic binding
		TabPersonne[2] = new Enseignant("Arthur", 21, 35) ;  // dynamic binding 
		
		for(int i = 0 ; i < TabPersonne.length ; i++){
			System.out.println(TabPersonne[i].toString()) ; // overide done in subclasses for toString methods  
															// we redefine partially toString method for Enseignant and Etudiant
													//       (we use super.toString() , and totally for Personne)
		}

	}

}
