package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleHerbivores;

public class Lapin extends FamilleHerbivores {

    public Lapin(){
    	super();
        this.niveauPopulation = 40;
        this.tauxReproduction = 50;
        this.hostilite = 2;
        this.resilience = 0.3;
        this.nbTourDeplacement = 1;
        }
    
    @Override
    public String toString() {
    	return "Lapin";
    }

	@Override
	public void capaciteEspece(Joueur[][] attaques) {}
	
	@Override
	public int getNourriture() {
		int retour = 0;
		if (this.nourriture >= 10) {
    		retour = this.nourriture;   		
    	} else if (this.nourriture >= 5) {
    		retour = 10;    		
    	} else if (this.nourriture >= 3) {
    		retour = 5;
    	} else if (this.nourriture > 1){
    		retour = 3;
       	} else {
    		retour = 1;
    	}
		return retour;
	}

}
