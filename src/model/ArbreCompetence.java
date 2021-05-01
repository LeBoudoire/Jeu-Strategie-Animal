package src.model;


import src.model.Joueur;

public class ArbreCompetence {

    public enum Caracteristique { ALIMENTATION, NIVEAUPOP, TAUXREPRODUCTION, HOSTILITE, RESILIENCE, NBTOURDEPLACEMENT};

    public class Cellule { /* On augmente de valeur caracteristique*/
        Caracteristique caracteristique;
        int valeur;

        public Cellule(int valeur, Caracteristique c){
            this.caracteristique = c;
            this.valeur = valeur;
        }

        public Caracteristique getCaracteristique(){
            return this.caracteristique;
        }

        public int  getValeur(){
            return this.valeur;
        }

    }



    private Joueur.Style style;
           
    /* Sous-Arbres */
    public Cellule[] arbreAgressif = {new Cellule(-1,Caracteristique.NBTOURDEPLACEMENT), new Cellule(5,Caracteristique.HOSTILITE), new Cellule(-2,Caracteristique.NBTOURDEPLACEMENT),new Cellule(10,Caracteristique.HOSTILITE), new Cellule(15,Caracteristique.HOSTILITE), new Cellule(20,Caracteristique.TAUXREPRODUCTION),new Cellule(50,Caracteristique.HOSTILITE)};
    private int indiceA = 0;
    public Cellule[] arbreDefenssif = {new Cellule(5,Caracteristique.NIVEAUPOP), new Cellule(10,Caracteristique.TAUXREPRODUCTION), new Cellule(5,Caracteristique.RESILIENCE),new Cellule(10,Caracteristique.RESILIENCE), new Cellule(10,Caracteristique.TAUXREPRODUCTION), new Cellule(20,Caracteristique.RESILIENCE),new Cellule(50,Caracteristique.RESILIENCE)};
    private int indiceD = 0;	
    public Cellule[] arbreNeutre = {new Cellule(5,Caracteristique.NIVEAUPOP), new Cellule(5,Caracteristique.HOSTILITE), new Cellule(5,Caracteristique.RESILIENCE),new Cellule(15,Caracteristique.TAUXREPRODUCTION), new Cellule(15,Caracteristique.HOSTILITE), new Cellule(15,Caracteristique.RESILIENCE),new Cellule(50,Caracteristique.TAUXREPRODUCTION)};
    private int indiceN = 0;	

	/** Initialiser l'arbre de compétence*/
	public ArbreCompetence(Joueur.Style style) {
        this.style = style;
    }

    public int getIndice(int indice) {
	    switch (indice) {
            case 1:
                return indiceA;
            case 2:
                return indiceD;
            case 3:
                return indiceN;
            default:
                return 0;
        }
    }

	/** Changer de style de jeu*/
    public void setStyle(Joueur.Style nouveauStyle){
        this.style = nouveauStyle;
    }

	/** Initialiser l'arbre de compétence*/
    public void avancer(){
        if (this.style == Joueur.Style.AGRESSIF){
            this.indiceA += 1;
        } else if (this.style == Joueur.Style.DEFENSSIF){
            this.indiceD += 1;
        } else if (this.style == Joueur.Style.NEUTRE){
            this.indiceN += 1;
        }
    }

	/** Renvoie la cellule où se trouve le joueur*/
    public Cellule bonusObtenu(){
        if (this.style == Joueur.Style.AGRESSIF){
            return arbreAgressif[indiceA-1];
        } else if (this.style == Joueur.Style.DEFENSSIF){
            return arbreDefenssif[indiceD-1];
        } else if (this.style == Joueur.Style.NEUTRE){
            return arbreNeutre[indiceN-1];
        } else {
            return arbreAgressif[indiceA-1];
        }
    }



}
