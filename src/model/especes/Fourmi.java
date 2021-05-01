package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleInvertebres;

public class Fourmi extends FamilleInvertebres {
	
	private boolean deuxiemeVie = true;

    public Fourmi(){
    	super();
        this.niveauPopulation = 300;
        this.tauxReproduction = 20;
        this.hostilite = 5;
        this.resilience = 0.4;
        this.nbTourDeplacement = 4;    
        }

    @Override
    public String toString() {
    	return "Fourmi";
    }
    
	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		if (this.deuxiemeVie && this.getNiveauPopulation() == 0) {
			this.deuxiemeVie = false;
			this.setPopulation(100);
		}
	}

}
