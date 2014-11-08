package product;

public class Publication {

	public Publication(String titre, int annee) {
		super();
		this.titre = titre;
		this.annee = annee;
	}
	private String titre;
	private int annee;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	@Override
	public String toString() {
		return "Publication [titre=" + titre + ", annee=" + annee + "]";
	}

}
