package src.model.familles;

import src.model.Famille;

abstract public class FamilleCarnivores extends Famille {

    public FamilleCarnivores(){
        this.id = 1;
        this.alimentation = new double[]{0.05, 1, 0.25, 0.5};
        //this.nourriture = 9;

        this.niveauPopulation = 20;
        this.tauxReproduction = 6;
        this.hostilite = 10;
        this.resilience = 1;
        this.nbTourDeplacement = 1;
    }

	@Override
	public void capaciteFamille() {};

}
