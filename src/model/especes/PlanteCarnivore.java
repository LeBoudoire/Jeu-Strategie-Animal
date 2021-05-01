package src.model.especes;

import src.model.GameModel;
import src.model.Joueur;
import src.model.familles.FamilleVegetaux;

public class PlanteCarnivore extends FamilleVegetaux {

    public PlanteCarnivore(){
    	super();
    	this.niveauPopulation = 40;
        this.tauxReproduction = 4;
        this.hostilite = 8;
        this.resilience = 2;
        this.nbTourDeplacement = 6;
    }

    @Override
    public String toString() {
    	return "PlanteCarnivore";
    }
    
	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		Joueur joueurAttaque = null;
		boolean nonTrouve = true;
		int i = 0;
		while(nonTrouve && i< GameModel.MAX_JOUEURS) {
			if (attaques[i][0].getEspece().toString() == "Crocodile") {
				joueurAttaque = attaques[i][1];
				nonTrouve = false;
			}
			i++;
		}
		if (joueurAttaque != null) {
			this.alimentation[joueurAttaque.getEspece().getId() -1] += 0.05;
		}
	}

}
