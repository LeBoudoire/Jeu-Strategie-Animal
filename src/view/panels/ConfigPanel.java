package src.view.panels;

import src.lib.AMenuPanel;
import src.model.GameModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ConfigPanel extends AMenuPanel {

    private String title = "Configuration de la partie";

    public JTextField[] playerNameFields;

    public ConfigPanel() {
        super();
        init();

        Border lineBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createTitledBorder(lineBorder, title);
        setBorder(border);
    }

    private void init() {
        setLayout(new GridLayout(0, 2, 10, 20));
        setOpaque(false);

        playerNameFields = new JTextField[GameModel.MAX_JOUEURS];

        for (int i = 0; i < GameModel.MAX_JOUEURS; i++) {
            JLabel speciesName = new JLabel(GameModel.SPECIES_LIST[i]);
            speciesName.setHorizontalAlignment(SwingConstants.RIGHT);
            playerNameFields[i] = new JTextField(20);
            add(speciesName);
            add(playerNameFields[i]);
        }
    }

}
