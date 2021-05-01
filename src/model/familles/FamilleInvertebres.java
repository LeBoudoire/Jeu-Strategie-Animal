package src.model.familles;

import src.model.Famille;

abstract public class FamilleInvertebres extends Famille {

    private int bonusHostilite = 0;

	public FamilleInvertebres(){
        this.id = 3;
        this.alimentation = new double[]{1, 0.5, 0.05, 0.25};
        //this.nourriture = 9;
        
        this.niveauPopulation = 450;
        this.tauxReproduction = 40;
        this.hostilite = 6;
        this.resilience = 0.1;
        this.nbTourDeplacement = 2;
    }

	@Override
	public void capaciteFamille() {
		this.bonusHostilite = (int) Math.floor(0.005 * this.niveauPopulation);
	}
	
	@Override
	public int getHostilite() {
		return this.hostilite + this.bonusHostilite ;
	}


}

