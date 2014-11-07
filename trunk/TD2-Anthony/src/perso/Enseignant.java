package perso ; 

/** 
 * Class Enseignant 
 */

public class Enseignant extends Personne {

    private int heures ;  

    public Enseignant(String nom, int age, int heures) { 
	super(nom,age) ; 
        this.heures = heures ;  
    }

    public int getHeures() {
	return heures ; 
    }

    public void setHeures(int val) {
	this.heures = val ; 
    }
  
    public String toString() {
	return (super.toString() +" He works " + getHeures() + " hours a week." ) ;
   } 

}