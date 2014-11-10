package perso;

public class Personne {
	

	private String nom;
	private int age;
	
	public Personne(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "nom=" + nom + ", age=" + age ;
	}
	
	
}
