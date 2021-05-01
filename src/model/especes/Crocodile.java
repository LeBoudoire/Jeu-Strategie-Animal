package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleCarnivores;

public class Crocodile extends FamilleCarnivores {

    public Crocodile(){
    	super();
        this.niveauPopulation = 10;
        this.tauxReproduction = 4;
        this.hostilite = 14;
        this.resilience = 5;
        this.nbTourDeplacement = 4;
        }
    
    @Override
    public String toString() {
    	return "Crocodile";
    }

	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		Joueur joueurAttaque = null;
		boolean nonTrouve = true;
		int i = 0;
		while(nonTrouve) {
			if (attaques[i][0].getEspece().toString() == "Crocodile") {
				joueurAttaque = attaques[i][1];
				nonTrouve = false;
			}
			i++;
		}
		
		if (joueurAttaque != null &&
				joueurAttaque.getEspece().getNiveauPopulation() == 0) {
			this.modDefense(3);
		}
	}

}
