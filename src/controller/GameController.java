package src.controller;

import src.*;
import src.model.*;
import src.view.cards.GameCardView;
import src.view.panels.SummaryTabPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

    private GameCardView view;
    private AnimalController animalController;
    private GameModel gameModel;

    public GameController(AnimalController animalController, GameCardView gameCardView, GameModel gameModel) {
        this.view = gameCardView;
        this.animalController = animalController;
        this.gameModel = gameModel;

        view.addButtonListener(new ButtonListener());

        this.init();
    }

    private void init() {
        tourSuivant();
    }

    private void tourSuivant() {
        view.setTourNumber(gameModel.getTour());
        actionSuivante();
    }

    private void joueurSuivant() {
        Joueur joueur = gameModel.getJoueurEnCours();

        view.setPlayerName(joueur.getNom() + " (" + joueur.getEspece().toString() + ")");
        updateGameCard();

        if (joueur.getRobot()) {
            gameModel.execJoueurRobot();
            actionSuivante();
        }
    }



    private void actionSuivante() {
        if (gameModel.isDernierJoueur()) {
            finTour();

            if (gameModel.isPartieTermine()) {
                finPartie();
            }
            else {
                gameModel.execTourSuivant();
                tourSuivant();
            }

        }
        else {
            gameModel.execJoueurSuivant();
            joueurSuivant();
        }
    }


    private void finTour() {
        List<Joueur> joueursEliminesNew = gameModel.execFinTour();
        List<String> joueursEliminesNewStr = new ArrayList<>();
        for (Joueur joueurElimine : joueursEliminesNew) {
            joueursEliminesNewStr.add(joueurElimine.getNom() + " (" + joueurElimine.getEspece().toString() + ")");
        }
        if (!joueursEliminesNew.isEmpty()) {
            view.printInfo(animalController.animalView, String.join("\n", joueursEliminesNewStr), "Eliminations");
        }
        else {
            view.printInfo(animalController.animalView, "Aucun joueur n'a été éliminé.", "Eliminations");
        }
    }

    private void finPartie() {
        List<Joueur> joueursGagnants = gameModel.execFinPartie();
        List<String> joueursGagnantsStr = new ArrayList<>();
        for (Joueur joueurGagnant : joueursGagnants) {
            joueursGagnantsStr.add(joueurGagnant.getNom() + " (" + joueurGagnant.getEspece().toString() + ")");
        }
        view.printInfo(animalController.animalView, "Joueur(s) gagnant(s) : \n\n" + joueursGagnantsStr, "Fin de la partie !");
    }


    private void updateGameCard() {
        updateMapPanel();
        updatePlayerPanel();
        updateInfosPanel();
        updateActionPanel();
    }

    private void updateMapPanel() {
        List<String> nwSpecies = new ArrayList<String>();
        List<String> neSpecies = new ArrayList<String>();
        List<String> swSpecies = new ArrayList<String>();
        List<String> seSpecies = new ArrayList<String>();

        for (Joueur joueur: gameModel.getJoueursEnVie()) {
            switch (joueur.getZone()) {
                case 1:
                    nwSpecies.add(joueur.getEspece().toString().toLowerCase());
                    break;
                case 2:
                    neSpecies.add(joueur.getEspece().toString().toLowerCase());
                    break;
                case 3:
                    swSpecies.add(joueur.getEspece().toString().toLowerCase());
                    break;
                case 4:
                    seSpecies.add(joueur.getEspece().toString().toLowerCase());
                    break;
            }
        }

        view.mapPanel.setPositionSpecies("nw", nwSpecies.toArray(new String[0]));
        view.mapPanel.setPositionSpecies("ne", neSpecies.toArray(new String[0]));
        view.mapPanel.setPositionSpecies("sw", swSpecies.toArray(new String[0]));
        view.mapPanel.setPositionSpecies("se", seSpecies.toArray(new String[0]));
    }

    private void updatePlayerPanel() {
        Joueur joueur = gameModel.getJoueurEnCours();

        SummaryTabPanel summaryTab = view.playerPanel.summaryTab;
        Famille espece = joueur.getEspece();

        summaryTab.xpBar.update((int) joueur.getExperience(), 100);
        summaryTab.levelLabel.update(joueur.getNiveau(), joueur.getNbBonusDebloque());

        summaryTab.foodLabel.update(espece.getNourriture());
        summaryTab.populationLabel.update(joueur.getNiveauPopulation());

        summaryTab.hostilityLabel.update(espece.getHostilite());
        summaryTab.defenseLabel.update(espece.getDefense());
        summaryTab.reproductionLabel.update(espece.getTauxReproduction());
        summaryTab.agilityLabel.update(espece.getNbTourDeplacement());
        summaryTab.resilienceLabel.update(espece.getResilience());
        summaryTab.poisonLabel.update(espece.getEmpoisonne() ? "Oui" : "Non");
        summaryTab.mobilityLabel.update(joueur.getMobilite() ? "Oui" : "Non");

        //summaryTab.alimentationLabel.update(espece.getAlimentation());

        summaryTab.attackCompetenceLabel.update(joueur.getArbreIndice(1), 7, (joueur.getStyle() == Joueur.Style.AGRESSIF) ? "(sélectionné)" : "");
        summaryTab.defenseCompetenceLabel.update(joueur.getArbreIndice(2), 7, (joueur.getStyle() == Joueur.Style.DEFENSSIF) ? "(sélectionné)" : "");
        summaryTab.neutralCompetenceLabel.update(joueur.getArbreIndice(3), 7, (joueur.getStyle() == Joueur.Style.NEUTRE) ? "(sélectionné)" : "");

        view.playerPanel.treeTab.styleOffensiveBtn.setEnabled(joueur.getStyle() != Joueur.Style.AGRESSIF);
        view.playerPanel.treeTab.styleDefensiveBtn.setEnabled(joueur.getStyle() != Joueur.Style.DEFENSSIF);
        view.playerPanel.treeTab.styleNeutralBtn.setEnabled(joueur.getStyle() != Joueur.Style.NEUTRE);
        view.playerPanel.treeTab.progressOffensive.setEnabled(joueur.getStyle() == Joueur.Style.AGRESSIF);
        view.playerPanel.treeTab.progressDefensive.setEnabled(joueur.getStyle() == Joueur.Style.DEFENSSIF);
        view.playerPanel.treeTab.progressNeutral.setEnabled(joueur.getStyle() == Joueur.Style.NEUTRE);

        view.playerPanel.treeTab.set(0, joueur.getArbreIndice(1));
        view.playerPanel.treeTab.set(1, joueur.getArbreIndice(2));
        view.playerPanel.treeTab.set(2, joueur.getArbreIndice(3));
    }

    private void updateInfosPanel() {
        String[] joueursStr = new String[gameModel.getNbJoueursEnVie()];
        int[] joueursInt = new int[gameModel.getNbJoueursEnVie()];
        int j = 0;
        for (Joueur joueur: gameModel.getJoueursEnVie()) {
            joueursStr[j] = joueur.getNom() + " (" + joueur.getEspece().toString() + ")";
            joueursInt[j] = joueur.getEspece().getNiveauPopulation();
            j++;
        }
        view.infosPanel.setRemainingSpecies(joueursStr, joueursInt);
    }

    private void updateActionPanel() {

    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Joueur joueurEnCours = gameModel.getJoueurEnCours();
            int joueurEnCoursID = gameModel.getJoueurEnCoursID();

            // Arbre de compétences
            if(e.getSource().equals(view.actionPanel.treeButton)) {
                view.playerPanel.tabs.setSelectedIndex(1);
            }

            // Attaque
            else if (e.getSource().equals(view.actionPanel.attackButton)) {

                // Affichage de la liste des joueurs à attaquer
                boolean[] joueursElimines = gameModel.getJoueursElimines();
                List<String> joueursStr = new ArrayList<String>();
                for (int i = 0; i < GameModel.MAX_JOUEURS; i++) {
                    if (i != joueurEnCoursID && !joueursElimines[i] && joueurEnCours.getZone() == gameModel.getJoueur(i).getZone()) {
                        joueursStr.add("J" + (i+1) +" : " + gameModel.getJoueur(i).getNom() + " (" + gameModel.getJoueur(i).getEspece().toString() +")");
                    }
                }
                if (joueursStr.isEmpty()) {
                    view.printInfo(animalController.animalView, "Pas d'autres espèces dans votre zone !", "Impossible d'attaquer");
                    return;
                }
                String rep = view.actionPanel.printDialog(animalController.animalView, joueursStr.toArray(new String[0]));

                if (rep == null) {
                    return;
                }

                // Récupération de la réponse
                Pattern pattern = Pattern.compile("J([0-9]+) : .*");
                Matcher matcher = pattern.matcher(rep);
                if (matcher.find()) {
                    int joueurAttaqueID = Integer.parseInt(matcher.group(1)) - 1;
                    Joueur joueurAttaque = gameModel.getJoueur(joueurAttaqueID);
                    int resultatAttaque = joueurEnCours.attaquer(joueurAttaque);

                    switch (resultatAttaque) {
                        case -1:
                            view.printInfo(animalController.animalView, "Attaque ratée : la résilience de " + joueurAttaque.getEspece().toString() + " est trop forte", "Résultat de l'attaque");
                            break;
                        case -2:
                            view.printInfo(animalController.animalView, "Attaque ratée : vous avez été empoisonné", "Résultat de l'attaque");
                            break;
                        default:
                            view.printInfo(animalController.animalView, "Population mangée : " + resultatAttaque, "Résultat de l'attaque");
                            break;
                    }

                    gameModel.setAttaque(joueurAttaque);

                    actionSuivante();
                }
            }

            // Déplacement
            else if (e.getSource().equals(view.actionPanel.moveButton)) {
                String[] zones = new String[]{"Nord-Ouest", "Nord-Est", "Sud-Ouest", "Sud-Est"};
                String[] zonesStr = new String[3];
                for (int i = 0; i < gameModel.getMap().TAILLE; i++) {
                    if (i != joueurEnCours.getZone()-1) {
                        int zonesStrI = (i < joueurEnCours.getZone()) ? i : i-1;
                        zonesStr[zonesStrI] = "Z" + (i+1) +" : " + zones[i];
                    }
                }
                String rep = view.actionPanel.printDialog(animalController.animalView, zonesStr);

                if (rep == null) {
                    return;
                }

                Pattern pattern = Pattern.compile("Z([0-9]) : .*");
                Matcher matcher = pattern.matcher(rep);
                if (matcher.find()) {
                    int zone = Integer.parseInt(matcher.group(1));

                    //System.out.println(joueurEnCours.getZone() + " " + zone);
                    gameModel.getMap().deplacer(joueurEnCours, zone);

                    actionSuivante();
                }
            }

            else if (e.getSource().equals(view.actionPanel.skipButton)) {
                actionSuivante();
            }

            else if (e.getSource().equals(view.playerPanel.treeTab.styleOffensiveBtn)) {
                joueurEnCours.setStyle(Joueur.Style.AGRESSIF);
                updatePlayerPanel();
            }

            else if (e.getSource().equals(view.playerPanel.treeTab.styleDefensiveBtn)) {
                joueurEnCours.setStyle(Joueur.Style.DEFENSSIF);
                updatePlayerPanel();
            }

            else if (e.getSource().equals(view.playerPanel.treeTab.styleNeutralBtn)) {
                joueurEnCours.setStyle(Joueur.Style.NEUTRE);
                updatePlayerPanel();
            }

            else if (e.getSource().equals(view.playerPanel.treeTab.progressOffensive)) {
                if (joueurEnCours.getNbBonusDebloque() == 0) {
                    view.printInfo(animalController.animalView, "Pas assez de niveaux !", "Impossible d'améliorer");
                    return;
                }
                joueurEnCours.amelioration();
                updatePlayerPanel();
            }

            else if (e.getSource().equals(view.playerPanel.treeTab.progressDefensive)) {
                if (joueurEnCours.getNbBonusDebloque() == 0) {
                    view.printInfo(animalController.animalView, "Pas assez de niveaux !", "Impossible d'améliorer");
                    return;
                }
                joueurEnCours.amelioration();
                updatePlayerPanel();
            }

            else if (e.getSource().equals(view.playerPanel.treeTab.progressNeutral)) {
                if (joueurEnCours.getNbBonusDebloque() == 0) {
                    view.printInfo(animalController.animalView, "Pas assez de niveaux !", "Impossible d'améliorer");
                    return;
                }
                joueurEnCours.amelioration();
                updatePlayerPanel();
            }
        }
    }
}
