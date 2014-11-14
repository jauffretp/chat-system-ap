package perso;

import perso.*;


public class HelloPersonnes {

	public static void main (String[] args){
		Personne p = new Personne("Dupont",20);
		Etudiant stud = new Etudiant("Gr�vin",21,15.0f);
		Enseignant teach = new Enseignant("Lolo",200,35);
		
		System.out.println(p);
		System.out.println(stud);
		System.out.println(teach);
		
		Personne Peopleboard [] = new Personne [3];
		
		Peopleboard[0]= new Personne("Kirito",20);
		Peopleboard[1]=new Etudiant("Asuna",20,13);
		Peopleboard[2]=new Enseignant("Sinon",20,50);
		
		for(Personne p1 : Peopleboard){
			System.out.println(p1);
		}
	}
}
