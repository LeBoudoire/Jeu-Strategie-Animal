package src.controller;

import src.model.AnimalModel;
import src.model.Famille;
import src.model.GameModel;
import src.model.Joueur;
import src.view.cards.GameCardView;
import src.view.cards.IntroCardView;
import src.model.especes.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroController {

    private IntroCardView view;
    private AnimalController animalController;
    private AnimalModel model;
    //model

    public IntroController(AnimalController animalController, IntroCardView introCardView, AnimalModel animalModel) {
        this.view = introCardView;
        this.animalController = animalController;
        this.model = animalModel;

        view.addButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.startButton)) {
                Joueur joueurs[] = new Joueur[GameModel.MAX_JOUEURS];

                for (int i = 0; i < GameModel.MAX_JOUEURS; i++) {
                    String playerNameField = view.configPanel.playerNameFields[i].getText();
                    String joueurNom = (!playerNameField.isEmpty()) ? playerNameField : "Robot #"+(i+1);
                    String joueurEspece = GameModel.SPECIES_LIST[i];
                    joueurs[i] = new Joueur(joueurNom, initialiserEspece(joueurEspece));
                    if (playerNameField.isEmpty()) {
                        joueurs[i].setRobot(true);
                    }
                }

                animalController.setGameController(joueurs);
            }
        }
    }

    public static Famille initialiserEspece(String string) {
        switch (string) {
            case "sequoia" :
                return new Sequoia();
            case "ortie" :
                return new Ortie();
            case "plantecarnivore" :
                return new PlanteCarnivore();
            case "mouton" :
                return new Mouton();
            case "bison" :
                return new Bison();
            case "lapin" :
                return new Lapin();
            case "loup" :
                return new Loup();
            case "lion" :
                return new Lion();
            case "crocodile" :
                return new Crocodile();
            case "fourmi" :
                return new Fourmi();
            case "moustique" :
                return new Moustique();
            case "araignee" :
                return new Araignee();
            default :
                return new Loup();
        }
    }

}
