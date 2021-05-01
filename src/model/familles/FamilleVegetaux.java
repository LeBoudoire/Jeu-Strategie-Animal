package src.model.familles;

import src.model.Famille;

abstract public class FamilleVegetaux extends Famille {

    private static final int NourritureQuotidienne = 1;

	public FamilleVegetaux(){
        this.id = 4;
        this.alimentation = new double[]{0.5, 0.25, 1, 0.05};
        //this.nourriture = 9;
        
        this.niveauPopulation = 12;
        this.tauxReproduction = 2;
        this.hostilite = 3;
        this.resilience = 8;
        this.nbTourDeplacement = 4;
    }

	@Override
	public void capaciteFamille() {
		this.nourriture += FamilleVegetaux.NourritureQuotidienne;
	}
		
    
}
