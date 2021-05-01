package src.model.especes;

import src.model.Joueur;
import src.model.familles.FamilleHerbivores;

public class Bison extends FamilleHerbivores {
	
	private double saveAlimentation[];

    public Bison(){
    	super();
        this.niveauPopulation = 20;
        this.tauxReproduction = 4;
        this.hostilite = 8;
        this.resilience = 5;
        this.nbTourDeplacement = 5;
        this.saveAlimentation = this.alimentation;
        }

    @Override
    public String toString() {
    	return "Bison";
    }
    
	@Override
	public void capaciteEspece(Joueur[][] attaques) {
		
		this.setAlimentation(this.saveAlimentation);
		
		for (Joueur[] attaque : attaques) {
			if (attaque[1] != null && 
					attaque[1].getEspece().toString() == "Bison")  {
				this.alimentation[attaque[0].getEspece().getId() -1] = 1.5;
			}
		}
		
	}

}
