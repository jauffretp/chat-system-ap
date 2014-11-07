package perso ; 

/** 
 * Class Etudiant 
 */

public class Etudiant extends Personne {

    private float note ;  

    public Etudiant(String nom, int age, float note) { 
	super(nom,age) ; 
        this.note = note ; 
    }

    public float getNote() {
	return note ; 
    }

    public void setNote(float val) {
	this.note = val ; 
    }

    public String toString() {
	return (super.toString() + " His last grade is " + getNote() + " on 20.0.") ; 
   }   

}