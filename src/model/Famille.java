package src.model;

abstract public class Famille {

    protected int id; // Chaque famille à un identifiant.

    protected int niveauPopulation; //son nombre d’individus; 
    protected int tauxReproduction; //les nouveaux individus apparaissant chaque tour;
    protected double alimentation[];  //son eﬃcacité pour attaquer chaque famille;
    protected int hostilite; // le nombre d’individus maximum d’une autre espèce prédatés par tour;
    protected double resilience; //le nombre d’individus protégés lors d’une attaque;
    protected int nourriture = 9; //se gagnant lors des attaques elle sert à se reproduire, initialisation à 9;
    protected int nbTourDeplacement; //sa capacité à se déplacer rapidement. (nombre de tour pour effectuer un déplacement (de 1 à 4))
    protected int defense = 0; // défense, servant à contrer les attaques
    
    private boolean empoisonne = false;

    // double[][] initialiserCaracteristiques();

    //Setters
	    public void setPopulation(int nouvellePopulation) {           
	    	this.niveauPopulation = nouvellePopulation;
	    }
	    
	    public void setTauxReproduction(int nouveauTaux) {
	    	this.tauxReproduction = nouveauTaux;
	    }
	   
	    public void setHostilite(int nouvelleHostilite) {
	    	this.hostilite = nouvelleHostilite;
	    }

	    public void setResilience(double nouvelleResilience) {
	    	this.resilience = nouvelleResilience;
	    } 

	    public void setNourriture(int nouvelleNourriture) {
	    	this.nourriture = nouvelleNourriture;
	    }

	    public void setNbTourDeplacement(int nbTourDeplacement) {
	    	if (nbTourDeplacement > 0) {
	    		this.nbTourDeplacement = nbTourDeplacement;
	    	}
	    }

	    public void setAlimentation(double[] nouvelleAlimentation) {
	    	this.alimentation = nouvelleAlimentation;
	    }
	    
	    public void setEmpoisonne(boolean poison) {
	    	this.empoisonne = poison;
	    }
	    
	    public void setDefense(int defense) {
		    this.defense = defense;
		}

	//Getters
	    public int getId() {    
	    	return this.id;
	    }

	    public int getNiveauPopulation() {
	    	return this.niveauPopulation;
	    }

	    public int getTauxReproduction() {
	    	return this.tauxReproduction;
	    }

	    public int getHostilite() {
	    	return this.hostilite;
	    }

	    public double getResilience() {
	    	return this.resilience;
	    }

	    public int getNourriture() {
	    	return this.nourriture;
	    }

	    public int getNbTourDeplacement() {
	    	return this.nbTourDeplacement;
	    }
	    
	    public double[] getAlimentation() {
			return this.alimentation;
		}
	    
	    public boolean getEmpoisonne() {
	    	return this.empoisonne;
	    }
	    
	    public int getDefense() {
	    	return this.defense;
	    }

	//Modifiers
	    public void modPopulation(int population) {           
	    	this.niveauPopulation += population;
	    }

	    public void modTauxReproduction(int taux) {
	    	this.tauxReproduction += taux;
	    }
	 
	    public void modHostilite(int hostilite) {
	    	this.hostilite += hostilite;
	    }
	    
	    public void modResilience(double resilience) {
	    	this.resilience += resilience;
	    } 

	    public void modNourriture(int nourriture) {
	    	this.nourriture += nourriture;
	    }

	    public void modNbTourDeplacement(int nbTourDeplacement) {
	    	if (nbTourDeplacement < this.nbTourDeplacement) {
	    		this.nbTourDeplacement += nbTourDeplacement;
	    	}
	    }
	    
	    public void modDefense(int defense) {
			this.defense += defense;
		 }

	//Methodes 
	    
	    public void reproduction() {       // Incrémentation/Décrémentation de la population au début du tour
    	
    	int nouveauxIndividus;
    	int nourritureConsommee;
    	int nourriture = this.getNourriture();
    	if (nourriture >= 10) {
    		nouveauxIndividus = (int) Math.floor(this.getTauxReproduction() * 1.5);
    		nourritureConsommee = 10;
    		
    	} else if (nourriture >= 5) {
    		nouveauxIndividus = this.getTauxReproduction();
    		nourritureConsommee = 5;
    		
    	} else if (nourriture >= 3) {
    		nouveauxIndividus = (int) Math.floor(this.getTauxReproduction() / 2);
    		nourritureConsommee = 3;
    		
    	} else if (nourriture > 1){
    		nouveauxIndividus = 0;
    		nourritureConsommee = 1;
    		
    	} else {
    		nouveauxIndividus = (int) -Math.floor(this.getTauxReproduction() / 2);
    		nourritureConsommee = 0;
    	}
    	
    	this.modNourriture(-nourritureConsommee);
    	this.modPopulation(nouveauxIndividus);
    }
	    
	    
	    
	    abstract public void capaciteFamille();
	    
	    abstract public void capaciteEspece(Joueur[][] attaques);
	    
	    public void blessures(Joueur joueurAttaque, Joueur[][] attaques) {
	    	if (joueurAttaque.getEspece().toString() == "Ortie") {
	    		int degats = (int) Math.floor(0.001 * joueurAttaque.getEspece().getNiveauPopulation() /
	    				this.getResilience());  
	    		this.modPopulation(degats);
	    		
	    	}
	    	for (Joueur[] attaque : attaques) {
	    		if (attaque[0].getEspece().toString() == this.toString()) {
	    			if (attaque[1] != null &&
	    					attaque[1].getEspece().toString() == "Araignee") {
	    				attaque[0].setMobilite(false);
	    			} else if (attaque[1] != null &&
	    					attaque[1].getEspece().toString() == "Moustique") {
	    				this.empoisonne = true;
	    			}
	    		}
	    	}
	    }
}
