package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleHerbivores;

public class Mouton extends FamilleHerbivores {
	
	private int bonusDefense = 0;

    public Mouton(){
    	super();
        this.niveauPopulation = 100;
        this.tauxReproduction = 20;
        this.hostilite = 5;
        this.resilience = 0.5;
        this.nbTourDeplacement = 5;
        }

    @Override
    public String toString() {
    	return "Mouton";
    }
    
	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		this.bonusDefense = (int) Math.floor(0.01 * this.niveauPopulation);	
	}
	
	@Override
	public int getDefense() {
		return this.defense + this.bonusDefense;
	}

}
