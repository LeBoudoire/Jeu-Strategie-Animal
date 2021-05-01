package src;

import src.controller.AnimalController;
import src.model.AnimalModel;
import src.view.AnimalView;

public class Client {

    public static void main(String[] args) {
        AnimalView view = new AnimalView();
        AnimalModel model = new AnimalModel();

        new AnimalController(view, model);
    }

}
