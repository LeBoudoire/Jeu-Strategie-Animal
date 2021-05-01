package src.model.familles;

import src.model.Famille;

abstract public class FamilleHerbivores extends Famille {
	
	private int bonusReproduction = 0;

    public FamilleHerbivores(){
        this.id = 2;
        this.alimentation = new double[]{0.25, 0.05, 0.5, 1};
        //this.nourriture = 9;

        this.niveauPopulation = 100;
        this.tauxReproduction = 20;
        this.hostilite = 5;
        this.resilience = 0.5;
        this.nbTourDeplacement = 3;
    }
    

	@Override
	public void capaciteFamille() {
		this.bonusReproduction = (int) Math.floor(0.1 * this.niveauPopulation);
	}
	
	
	@Override
	public void reproduction() {
		this.tauxReproduction += this.bonusReproduction;
		super.reproduction();
		this.tauxReproduction -= this.bonusReproduction;
	}


}
