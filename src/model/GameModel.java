package src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameModel {

    public static final int MAX_JOUEURS = 12;
    public static final int MAX_TOUR = 100;
    public static final String[] SPECIES_LIST = new String[]{"sequoia", "ortie", "plantecarnivore", "mouton", "bison", "lapin", "loup", "lion", "crocodile", "fourmi", "moustique", "araignee"};

    private Map map;

    private Joueur[] joueurs;
    private boolean[] joueursElimines = new boolean[MAX_JOUEURS];

    private int tour;
    private int joueurEnCourdID;
    private boolean fin;

    private Joueur[][] attaques = new Joueur[MAX_JOUEURS][2];

    public GameModel(Joueur[] joueurs) {
        this.joueurs = joueurs;
        Arrays.fill(this.joueursElimines, false);

        this.fin = false;
        this.tour = 1;
        this.joueurEnCourdID = -1;

        this.map = new Map();
        this.map.initialiser(this.joueurs);
    }

    public void setJoueurSuivantID() {
        for (int i = joueurEnCourdID+1; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                joueurEnCourdID = i;
                return;
            }
        }
        for (int i = 0; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                joueurEnCourdID = i;
                return;
            }
        }
    }

    public boolean isDernierJoueur() {
        for (int i = joueurEnCourdID+1; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                return false;
            }
        }
        return true;
    }

    public Joueur getJoueurEnCours() {
        return joueurs[joueurEnCourdID];
    }

    public Joueur getJoueur(int id) { return joueurs[id]; }

    public int getJoueurEnCoursID() { return joueurEnCourdID; }

    public int getNbJoueursEnVie() {
        int nbJoueursEnVie = 0;
        for (int i = 0; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                nbJoueursEnVie++;
            }
        }
        return nbJoueursEnVie;
    }

    public List<Joueur> getJoueursEnVie() {
        List<Joueur> joueursEnVie = new ArrayList<>();
        for (int i = 0; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                joueursEnVie.add(joueurs[i]);
            }
        }
        return joueursEnVie;
    }

    public boolean[] getJoueursElimines() { return joueursElimines; }

    public Map getMap() { return map; }

    public List<Joueur> execFinTour() {
        for (int i = 0; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                Joueur j = this.joueurs[i];

                deplacer(this.map, j);
                j.setMobilite(true);
                j.getEspece().setEmpoisonne(false);
                j.getEspece().capaciteFamille();
                j.getEspece().capaciteEspece(attaques);
                j.getEspece().blessures(j, attaques);
                if (j.getStyle() == Joueur.Style.NEUTRE) {
                    j.gainXP(30);
                }

                if (j.getNiveauPopulation() > 0) {
                    j.reproduction();
                }
            }
        }

        List<Joueur> joueursEliminesNew = new ArrayList<>();
        for (int i = 0; i < MAX_JOUEURS; i++) {
            if (this.joueurs[i].getNiveauPopulation() <= 0 && !this.joueursElimines[i]){
                this.joueursElimines[i] = true;
                this.joueurs[i].getEspece().setPopulation(0);
                joueursEliminesNew.add(this.joueurs[i]);
            }
        }

        return joueursEliminesNew;
    }

    public List<Joueur> execFinPartie() {
        List<Joueur> joueursGagnants = new ArrayList<>();
        for (int i = 0; i < MAX_JOUEURS; i++) {
            if (!joueursElimines[i]) {
                joueursGagnants.add(joueurs[i]);
            }
        }
        return joueursGagnants;
    }

    public void execTourSuivant() {
        this.tour++;
        this.joueurEnCourdID = -1;
    }

    public void execJoueurSuivant() {
        setJoueurSuivantID();
        setAttaque(null);
    }

    public void execJoueurRobot() {
        Joueur joueur = getJoueurEnCours();
        Random rnd = new Random();

        int choix = (!estSeul(joueurs)) ? rnd.nextInt(2) : 1;
        switch (choix) {
            case 0: // attaque
                int joueurAAttaquer;
                do {
                    joueurAAttaquer = rnd.nextInt(joueurs.length);
                } while (joueurAAttaquer == joueurEnCourdID || joueurs[joueurAAttaquer].getZone() != joueur.getZone());

                Joueur joueurAttaque = joueurs[joueurAAttaquer];
                joueur.attaquer(joueurAttaque);

                setAttaque(joueurAttaque);

                break;
            case 1: // déplacement
                int zoneSuivante;
                do {
                    zoneSuivante = 1 + rnd.nextInt(3);
                } while(zoneSuivante == joueur.getZone());

                map.deplacer(joueur, zoneSuivante);

                setAttaque(null);

                break;
        }
    }

    public int getTour() {
        return tour;
    }

    public void setAttaque(Joueur joueurAttaque) {
        this.attaques[getJoueurEnCoursID()][0] = getJoueurEnCours();
        this.attaques[getJoueurEnCoursID()][1] = joueurAttaque;
    }

    public boolean isPartieTermine() {
        return tour == MAX_TOUR || getNbJoueursEnVie() <= 1;
    }

    public void deplacer(Map map, Joueur joueur) {
        joueur.setCompteurDeplacement(joueur.getCompteurDeplacement() + 1);

        if (joueur.getCompteurDeplacement() == joueur.getEspece().getNbTourDeplacement()) {
            map.supprimer(joueur, joueur.getZone());
            map.ajouter(joueur, joueur.getZoneSuivante());
            //System.out.println("Déplacement réussi. De la zone " + joueur.getZone() + " vers la zone " + joueur.getZoneSuivante() );
            joueur.setZone(joueur.getZoneSuivante());
            joueur.setCompteurDeplacement(0);
        }
    }

    public boolean estSeul(Joueur[] joueurs) {
        for (Joueur joueur: joueurs) {
            if (joueur.getZone() == getJoueurEnCours().getZone() && joueur != getJoueurEnCours()) {
                return false;
            }
        }
        return true;
    }
}
