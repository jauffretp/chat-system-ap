package perso;
import product.Publication;

public interface IChercheur {
	public void ajouterPublication(Publication p);
	public String listerPublications();
}
