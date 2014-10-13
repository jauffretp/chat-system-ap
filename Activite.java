package insa.poo;

import java.util.*;

public class Activite {

	private String nom;
	private Personne pers1;
	private Personne pers2;
	private ArrayList <TChrono> ListeTacheChron;
	private ArrayList <TLibre> ListeTacheLibre;

	public Activite (String nom, Personne pers1, Personne pers2)  { // une personne peut etre null si une activité ne concerne qu'une personne 
		this.nom=nom;
		this.pers1=pers1;		 
		this.pers2=pers2;
		this.ListeTacheChron= new ArrayList <TChrono>() ; // liste vide créée
		this.ListeTacheLibre= new ArrayList <TLibre>() ;		
	}


	// fonctions d'affichage //////////////////////////////////////	

	public void AfficherNomActivite() {
		System.out.println(this.nom) ; 
	}

	public void AfficherTaches() {	   
		
       System.out.println("L'activite se nomme " + nom + " et les différentes tâches sont") ;
		
		if (!(ListeTacheChron.size() == 0)) {
		System.out.println(" ") ;
		System.out.println("Taches chronométrées : ") ;
		for(Object TacheC : ListeTacheChron){	
			((TChrono) TacheC).AfficherTache() ; 
		} 
		} 
		
		if (!(ListeTacheLibre.size() == 0)) {
		System.out.println(" ") ;
		System.out.println("Taches libres : ") ;
		for(Object TacheL : ListeTacheLibre){			
			((TLibre) TacheL).AfficherTache() ; 
		} 
		} 
		System.out.println(" ") ;		
	}

	public void AfficherPersonnesImpliquees() {
		System.out.println("L'activité "+ this.nom +" implique : ") ;
		if (pers1 != null) { pers1.Afficher_Nom() ; }
		if (pers2 != null) { pers2.Afficher_Nom() ; }
	}

	///////////////////////////////////////////////////



	// gestion des taches pour une activite ////////////////	


	//////////// Taches chronométrées /////////////////////////

	public void AddTacheChron (TChrono T) throws ExisteDeja,PersonneIncorrecte{			
		//  on vérifie que la tache qu on va ajouter n'existe pas
		for(Object TacheC : ListeTacheChron){			
			if (TacheC==T){
				throw new ExisteDeja("La tache est deja ajoutée");
			}	
		} 

		// On vérifie que la tache qui sera ajoutée concerne bien la bonne personne
		if ( (T.Retourne_Personne() == this.pers1) || (T.Retourne_Personne() == this.pers2)){
			this.ListeTacheChron.add(T) ; 	
		}
		else{
			throw new PersonneIncorrecte();
		}
	}

	public void AfficherTachesEnRetard(){					
		System.out.println("Pour l'activité " + this.nom + ", les taches en retard sont : ") ; 
		for(TChrono TacheAct : ListeTacheChron){
			GregorianCalendar date_now = new GregorianCalendar() ; 
		    //  si la tache n'a pas été faite et que l'échéance est dépassée comparée à aujourd'hui 
			// OU  la tache a été réalisée : si le bonus est de -2 alors la tache a été  réalisée en retard 			
			if ((TacheAct.T_En_Retard(date_now) && !(TacheAct.fait)) || ((TacheAct.fait) && TacheAct.bonus == -2)){
				TacheAct.AfficherTache() ; 
				if (TacheAct.fait) System.out.println(" (Tache réalisée) " ) ; 
				else System.out.println(" (Tache non réalisée) " ) ; 
			}
		}
		System.out.println(" ") ;
	}

	public void AfficherTachesRealisees(){					
		for(Object TacheAct : ListeTacheChron){
			if (((TChrono) TacheAct).TacheFaite()){
				((TChrono) TacheAct).AfficherTache() ; 
			}
		}
		System.out.println(" ") ;
	}
	//////////////////////////////////////////


	////////// Taches libres ///////////////////

	public void AddTacheLibre (TLibre T) throws ExisteDeja,PersonneIncorrecte{			
		//  on vérifie que la tache qu on va ajouter n'existe pas
		if (this.ListeTacheLibre.contains(T)){               //pour chercher une tache contains est équivalent au for
			throw new ExisteDeja("La tache est deja dans ListeTachelibre");
		}			

		// On vérifie que la tache qui sera ajoutée concerne bien la bonne personne
		if ( (T.Retourne_Personne() == this.pers1) || (T.Retourne_Personne() == this.pers2)){
			this.ListeTacheLibre.add(T) ; 	
		}
		else{
			throw new PersonneIncorrecte();			
		}

	}
	


	//////////////////////////////////////////////////////////

	
	

}



