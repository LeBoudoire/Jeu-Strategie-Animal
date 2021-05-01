package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleVegetaux;

public class Ortie extends FamilleVegetaux {

    public Ortie(){
    	super();
    	this.niveauPopulation = 100;
        this.tauxReproduction = 8;
        this.hostilite = 4;
        this.resilience = 4;
        this.nbTourDeplacement = 5;    
        }
    
    @Override
    public String toString() {
    	return "Ortie";
    }
    
	@Override
	public void capaciteEspece(Joueur[][] attaques) {}

}
