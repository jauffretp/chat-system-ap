/**
 * Classe Personne
 */

package perso;
public class Personne
{
    private String nom ;
    private int age ;

    public Personne(String nom, int age){
	this.nom = nom ;
	this.age = age;
    }

    public String getNom(){return nom;} 

    public void setNom(String val){
	this.nom = val;
    }

    public int getAge(){return age;} 

    public void setAge(int val){
	this.age = val;
    }

    public String toString(){
	return "Nom : " + this.getNom() + " Age : " + this.getAge() ;
    }

  

}
