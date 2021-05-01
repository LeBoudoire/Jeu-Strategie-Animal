package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleVegetaux;

public class Sequoia extends FamilleVegetaux {
	
	private int tourDefense = 5;

    public Sequoia(){
    	super();
        this.niveauPopulation = 12;
        this.tauxReproduction = 2;
        this.hostilite = 3;
        this.resilience = 8;
        this.nbTourDeplacement = 6;
    }
    
    @Override
    public String toString() {
    	return "Sequoia";
    }

	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		this.tourDefense--;
		if (this.tourDefense == 0) {
			this.modDefense(1);
			this.tourDefense = 4;
		}
	}

}
