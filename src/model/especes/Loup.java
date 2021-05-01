package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleCarnivores;

public class Loup extends FamilleCarnivores {

    public Loup(){
    	super();
        this.niveauPopulation = 30;
        this.tauxReproduction = 7;
        this.hostilite = 10;
        this.resilience = 1;
        this.nbTourDeplacement = 3;
    }

    @Override
    public String toString() {
    	return "Loup";
    }

	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		Joueur joueurAttaque = null;
		boolean nonTrouve = true;
		int i = 0;
		while(nonTrouve) {
			if (attaques[i][0].getEspece().toString() == "Loup") {
				joueurAttaque = attaques[i][1];
				nonTrouve = false;
			}
			i++;
		}
		
		if (joueurAttaque != null &&
				joueurAttaque.getEspece().getNiveauPopulation() == 0) {
			this.modTauxReproduction(5);
		}
	}
}
