package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleInvertebres;

public class Araignee extends FamilleInvertebres {

    public Araignee(){
    	super();
        this.niveauPopulation = 150;
        this.tauxReproduction = 8;
        this.hostilite = 8;
        this.resilience = 0.3;
        this.nbTourDeplacement = 4;
    }
    
    @Override
    public String toString() {
    	return "Araignee";
    }
	@Override
	public void capaciteEspece(Joueur[][] attaques) {}

}
