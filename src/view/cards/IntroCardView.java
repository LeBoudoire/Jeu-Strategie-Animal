package src.view.cards;

import src.view.CardView;
import src.view.panels.ConfigPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class IntroCardView extends CardView {

    static LayoutManager layout = new FlowLayout();
    static public String id = "introCard";

    public ConfigPanel configPanel;

    public JButton startButton;

    public IntroCardView() {
        super(id, layout);
    }

    public void init() {
        super.init();

        JLabel bvnLabel = new JLabel("Bienvenue dans @nimal !");
        add(bvnLabel);

        configPanel = new ConfigPanel();
        add(configPanel);

        startButton = new JButton("Commencer");
        add(startButton);


    }

    public void addButtonListener(ActionListener buttonListener) {
        startButton.addActionListener(buttonListener);
    }



}
