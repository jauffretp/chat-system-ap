
import perso.* ; 

/** 
 * Class HolaPersonnes 
 */

public class HolaPersonnes {

    /** 
     * Main Method, entry point to the HolaPersonnes Class
     * @param args input parameters from command line  
     */

    public static void main (String[] args){
	System.out.println("HolaPersonne is starting...") ;
        Personne person1 = new Personne("Anthony", 20) ; 
        Enseignant teacher = new Enseignant("Ernesto", 30, 35) ;
	   Etudiant student = new Etudiant("Pascal", 20, ((float) 7.0)) ;
	   System.out.println(person1) ; 
       System.out.println(teacher) ;         
       System.out.println(student) ;  
    }
}
  