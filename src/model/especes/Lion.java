package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleCarnivores;

public class Lion extends FamilleCarnivores {

    public Lion(){
    	super();
        this.niveauPopulation = 20;
        this.tauxReproduction = 6;
        this.hostilite = 14;
        this.resilience = 2;
        this.nbTourDeplacement = 3;
    }
    
    @Override
    public String toString() {
    	return "Lion";
    }

	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		Joueur joueurAttaque = null;
		boolean nonTrouve = true;
		int i = 0;
		while(nonTrouve) {
			if (attaques[i][0].getEspece().toString() == "Lion") {
				joueurAttaque = attaques[i][1];
				nonTrouve = false;
			}
			i++;
		}
		
		if (joueurAttaque != null &&
				joueurAttaque.getEspece().getNiveauPopulation() == 0) {
			this.modHostilite(5);
		}
	}

}
