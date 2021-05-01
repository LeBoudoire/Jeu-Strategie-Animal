package src.model;
import src.ActionInvalideException;

import java.util.*;

public class Map {

    // taille de la map
	public int TAILLE = 4;

    // Map
    private ArrayList<Joueur> zone1;	
    private ArrayList<Joueur> zone2;	
    private ArrayList<Joueur> zone3;	
    private ArrayList<Joueur> zone4;	

	/** Initialiser la carte du jeu */
	public Map() {
        //this.map = [new ArrayList<Joueur>(); new ArrayList<Joueur>(); new ArrayList<Joueur>(); new ArrayList<Joueur>();];
        this.zone1 = new ArrayList<Joueur>();
        this.zone2 = new ArrayList<Joueur>();
        this.zone3 = new ArrayList<Joueur>();
        this.zone4 = new ArrayList<Joueur>();

    }


    public void initialiser(Joueur[] joueurs){
        for (int i = 0; i < joueurs.length; i++) {
            joueurs[i].afficherCaracteristiques();
            this.ajouter(joueurs[i],joueurs[i].getZone());
        }
	}

    public ArrayList<Joueur> intToZone(int i){
        switch (i){
            case 1:
                return this.zone1;

            case 2:
                return this.zone2;

            case 3:
                return this.zone3;

            case 4:
                return this.zone4;

            default:
                return this.zone1;
        }
    }

	/** Ajouter un joueur dans la zone j. */
	//@ requires j >= 1 && j <= TAILLE;
	public void ajouter(Joueur joueur, int j){
        intToZone(j).add(joueur);
    }

	/** Supprimer un joueur de la zone i. */
	//@ requires i >= 1 && i <= TAILLE;
	public void supprimer(Joueur joueur, int i){
        intToZone(i).remove(joueur);
    }

	/** Déplacer un joueur de la zone i à la zone j. */
	//@ requires i >= 1 && x <= TAILLE;
	public void deplacer(Joueur joueur, int zoneSuivante){
        if (joueur.getZone()==zoneSuivante){
            throw new ActionInvalideException("Vous êtes déjà dans cette zone !");
        }else{
        	if (!joueur.getMobilite()) {
        		//System.out.println("Vous êtes empêtré dans des toiles d'araignée ! Vous ne pouvez pas vous déplacer !");
        	} else {
        		joueur.setZoneSuivante(zoneSuivante);
        		joueur.setCompteurDeplacement(0);
        	}
        }
    }

	/** Savoir si un joueur est présent dans la zone i.
	 * @param joueur 
	 * @param i zone de la carte
	 */
	//@ requires i >= 0 && i < TAILLE;
	public boolean estPresent(Joueur joueur, int i) {
		return intToZone(i).contains(joueur);
	}

}
