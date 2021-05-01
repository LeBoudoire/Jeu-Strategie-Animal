package src.model;
import src.ActionInvalideException;
import src.model.ArbreCompetence;
import src.model.Famille;

import java.util.*;
import java.lang.Math;

public class Joueur {

    private String nom;
    private Famille espece;
    private boolean robot;

    public enum Style { AGRESSIF, DEFENSSIF, NEUTRE};
    private Style style;
    private ArbreCompetence abr;
    private double experience;
    private int niveau;
    private int nbBonusDebloque;

    private int zoneActuelle; //pour savoir dans quelle zone il se trouve sur la carte
    private int zoneSuivante;
    private boolean mobilite = true;
    private int compteurDeplacement = 0;


    public Joueur(String nom, Famille espece) {
        this.nom = nom;
        this.espece = espece;
        this.robot = false;

        this.style = Style.AGRESSIF;
        this.abr = new ArbreCompetence(this.style);
        this.experience = 0;
        this.niveau = 0;
        this.nbBonusDebloque = 0;

        Random rnd = new Random();
        this.zoneActuelle = 1 + rnd.nextInt(4);
        this.zoneSuivante = this.zoneActuelle;

    }

    //Setters

    public void setRobot(boolean bool){
        this.robot = bool;
    }

    public void setStyle(Style style) {
        this.style = style;
        this.abr.setStyle(style);
    }

    public void setZone(int i) {
        this.zoneActuelle = i;
    }

    public void setZoneSuivante(int i) {
        this.zoneSuivante = i;
    }

    public void setMobilite(boolean mobilite) {
        this.mobilite = mobilite;
    }

    public void setCompteurDeplacement(int i) {
        this.compteurDeplacement = i;
    }

    public void modExperience(double diffXP) {
        this.experience += diffXP;
    }
    //Getters

    public String getNom(){
        return this.nom;
    }
    public Famille getEspece(){
        return this.espece;
    }

    public boolean getRobot(){
        return this.robot;
    }

    public Style getStyle(){
        return this.style;
    }

    public int getArbreIndice(int style) {
        return this.abr.getIndice(style);
    }

    public double getExperience() {
        return this.experience;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public int getNbBonusDebloque() {
        return this.nbBonusDebloque;
    }

    public int getNiveauPopulation() {
        return this.getEspece().getNiveauPopulation();
    }

    public int getZone() {
        return this.zoneActuelle;
    }

    public int getZoneSuivante() {
        return this.zoneSuivante;
    }
    public boolean getMobilite() {
        return this.mobilite;
    }

    public int getCompteurDeplacement() {
        return this.compteurDeplacement;
    }



    //Actions du jeu


    /* Gain d'XP */
    public void gainXP(int XP){

        this.modExperience(XP);
        if (this.experience/100 >= 1){
            this.niveau += this.experience/100;
            this.nbBonusDebloque += this.experience/100;
            this.experience = this.experience % 100;
        }

        //System.out.println("nbBonusDebloque : " + this.nbBonusDebloque + ", niveau : " + this.niveau + ", experience : " + this.experience);
    }

    /* Amélioration des caractéristiques grâce à l'arbre de compétence */
    public void amelioration(){
        if (this.nbBonusDebloque > 0){

            // On avance dans l'arbre de notre style de jeu.
            this.abr.avancer();
            // Activation du bonus.
            if (this.abr.bonusObtenu().getCaracteristique()== ArbreCompetence.Caracteristique.NIVEAUPOP){
                this.espece.modPopulation(this.abr.bonusObtenu().getValeur() * this.espece.getNiveauPopulation() / 100);
            } else if (this.abr.bonusObtenu().getCaracteristique() == ArbreCompetence.Caracteristique.TAUXREPRODUCTION){
                this.espece.modTauxReproduction(this.abr.bonusObtenu().getValeur() * this.espece.getTauxReproduction() / 100);
            } else if (this.abr.bonusObtenu().getCaracteristique() == ArbreCompetence.Caracteristique.HOSTILITE){
                this.espece.modHostilite(this.abr.bonusObtenu().getValeur() * this.espece.getHostilite() / 100);
            } else if (this.abr.bonusObtenu().getCaracteristique() == ArbreCompetence.Caracteristique.RESILIENCE){
                this.espece.modResilience(this.abr.bonusObtenu().getValeur() * this.espece.getResilience() / 100);
            } else if (this.abr.bonusObtenu().getCaracteristique() == ArbreCompetence.Caracteristique.NBTOURDEPLACEMENT){
                this.espece.modNbTourDeplacement(this.abr.bonusObtenu().getValeur());
            }
            this.nbBonusDebloque -= 1;
        } else {
            //System.out.println(" Veuillez attendre le prochain niveau !");
        }
    }

    public void reproduction() {
        this.getEspece().reproduction();
    }

    public int attaquer(Joueur joueurEnDefense) {
        //System.out.println(this.getNom() + " tente d'attaquer " + joueurEnDefense.getNom());
        if (this==joueurEnDefense){
            throw new ActionInvalideException("On ne s'attaque pas soi-même!");

        } else if (this.getZone()==joueurEnDefense.getZone()){

            return attaquerBis(joueurEnDefense);
            //System.out.println("Fin de l'attaque");

        } else {
            throw new ActionInvalideException("Les joueurs ne sont pas dans la même zone. "
                    + joueurEnDefense.getNom() + " est dans la zone " + joueurEnDefense.getZone());
        }
    }

    /* Attaquer une espèce adverse */
    /* Si on est empoisoné on a 1/3 chance de rater l'attaque */
    public int attaquerBis(Joueur joueurEnDefense) {
        int jouer = 0;
        int populationMangeable = Math.min(toIntAlea(
                (this.espece.getHostilite() - joueurEnDefense.getEspece().getDefense()) /
                        joueurEnDefense.getEspece().getResilience()),
                joueurEnDefense.getNiveauPopulation());

        int populationMangee = 0;

        if (this.espece.getEmpoisonne()) {
            Random random = new Random();
            jouer = random.nextInt(3);
        }

        if (jouer != 2) { // jouer = 2

            if (populationMangeable > 0) {
                populationMangee = (int) Math.floor(populationMangeable*
                        this.espece.getAlimentation()[joueurEnDefense.getEspece().getId()-1]);

                this.espece.modNourriture((int) Math.floor(populationMangee *
                        joueurEnDefense.getEspece().getResilience()));
                joueurEnDefense.getEspece().modPopulation(-populationMangee);

                //On met à jour les XPs.
                if (this.style == Style.AGRESSIF){
                    this.gainXP(10*populationMangee);
                }
                if (joueurEnDefense.getStyle() == Style.DEFENSSIF){
                    joueurEnDefense.gainXP((int)Math.floor(10 * populationMangee * joueurEnDefense.getEspece().getResilience()));
                }

                //System.out.println("Attaque réussie : la population mangée est de " + populationMangee);
                return populationMangee;

            } else {
                //System.out.println("Attaque ratée : la résilience de " + joueurEnDefense.getNom() + " est trop forte !");
                return -1; // -1 -> résilience trop forte
            }
        } else {
            //System.out.println("Attaque ratée : Vous avez été empoisonné !");
            return -2; // -2 -> empoisonné
        }

    }

    public String afficherCaracteristiques() {
        Famille espece = this.getEspece();
        return "\n" + "Caractéristiques de " + this.getNom() + ":\n\t Experience : " + this.getExperience() +
                ", \n\t Coordonnées : Zone " + this.getZone() +
                ", \n\t Niveaux de population : " + espece.getNiveauPopulation() +
                ", \n\t Taux de reproduction : " + espece.getTauxReproduction() +
                ", \n\t Hostilité : " + espece.getHostilite() +
                ", \n\t Resilience : " + espece.getResilience() +
                ", \n\t Nourriture : " + espece.getNourriture() +
                ", \n\t Agilite : " + espece.getNbTourDeplacement() ;
    }


    /* ... */
    private int toIntAlea(double nb) {
        int ret;
        int partieEntiere = (int) Math.floor(nb);
        double nbAlea = Math.random();
        if (nb - partieEntiere <= nbAlea) {
            ret = partieEntiere;
        } else {
            ret = partieEntiere + 1;
        }
        return ret;
    }
}
