package perso;


import java.util.ArrayList;

import product.Publication;

public class EnseignantChercheur extends Enseignant implements IChercheur {

	private ArrayList <Publication>publications ;
	public EnseignantChercheur(String nom, int age, int heures) {
		super(nom, age, heures);
		this.publications = new ArrayList<Publication>();
	}

	public void ajouterPublication(Publication p){
		if(publications.size()<10){
			publications.add(p);
			
		}else{
			System.out.println("Il y a déjà 10 Publications");
		}

	}

	@Override
	public String listerPublications() {
		String listing = "";
		for(Publication pub : this.publications){
			listing = listing +", "+ pub;
		}
		return listing;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ ", publications: " + listerPublications();
	}
	
	
}
