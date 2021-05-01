package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleInvertebres;

public class Moustique extends FamilleInvertebres {

    public Moustique(){
    	super();
        this.niveauPopulation = 350;
        this.tauxReproduction = 45;
        this.hostilite = 5;
        this.resilience = 0.1;
        this.nbTourDeplacement = 3;    
        }

    @Override
    public String toString() {
    	return "Moustique";
    }
    
	@Override
	public void capaciteEspece(Joueur[][] attaques) {}

}
