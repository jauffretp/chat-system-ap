package perso;

public class Enseignant extends Personne {
	
	public Enseignant(String nom, int age, int heures) {
		super(nom, age);
		this.heures = heures;
	}

	int heures;

	public int getHeures() {
		return heures;
	}

	public void setHeures(int heures) {
		this.heures = heures;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+", heures="+this.heures;
	}


}
