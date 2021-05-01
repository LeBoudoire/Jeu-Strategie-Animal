package src.view.cards;

import src.view.CardView;

import javax.swing.*;
import java.awt.*;

public class DefaultCardView extends CardView {

    static LayoutManager layout = null;
    static public String id = "defaultCard";

    public DefaultCardView() {
        super(id, layout);
    }

    public void init() {
        super.init();

        JLabel label = new JLabel("Chargement...");
        add(label);
    }

}
