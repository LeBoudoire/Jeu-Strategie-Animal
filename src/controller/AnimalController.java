package src.controller;

import src.model.AnimalModel;
import src.model.GameModel;
import src.model.Joueur;
import src.view.AnimalView;
import src.view.cards.GameCardView;
import src.view.cards.IntroCardView;

public class AnimalController {

    public AnimalView animalView;
    public AnimalModel animalModel;

    public AnimalController(AnimalView animalView, AnimalModel model) {
        this.animalView = animalView;
        this.animalModel = animalModel;


        IntroCardView introCardView = animalView.introCardView;
        new IntroController(this, introCardView, animalModel);

        animalView.setActiveCardView(introCardView);
    }

    public void setGameController(Joueur[] joueurs) {
        GameCardView gameCardView = animalView.gameCardView;

        animalView.setActiveCardView(gameCardView);

        GameModel gameModel = new GameModel(joueurs);
        new GameController(this, gameCardView, gameModel);
    }

}
