package src.view.cards;

import src.lib.AGridBagLayout;
import src.view.CardView;
import src.view.panels.ActionPanel;
import src.view.panels.InfosPanel;
import src.view.panels.PlayerPanel;
import src.view.panels.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameCardView extends CardView {

    static LayoutManager layout = AGridBagLayout.createGridBagLayout(
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2000, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 2000, 0},
            new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
    );
    static public String id = "gameCard";

    public ActionPanel actionPanel;
    public InfosPanel infosPanel;
    public PlayerPanel playerPanel;
    public MapPanel mapPanel;

    public GameCardView() {
        super(id, layout);
    }

    public void init() {
        super.init();

        actionPanel = new ActionPanel();
        infosPanel = new InfosPanel();
        playerPanel = new PlayerPanel();
        mapPanel = new MapPanel();

        GridBagConstraints gbc_map = new GridBagConstraints();
        gbc_map.weighty = 1.0;
        gbc_map.weightx = 1.0;
        gbc_map.fill = GridBagConstraints.BOTH;
        gbc_map.gridwidth = 9;
        gbc_map.gridheight = 9;
        gbc_map.anchor = GridBagConstraints.NORTHWEST;
        gbc_map.insets = new Insets(0, 0, 5, 5);
        gbc_map.gridx = 0;
        gbc_map.gridy = 0;
        add(mapPanel, gbc_map);

        GridBagConstraints gbc_joueur = new GridBagConstraints();
        gbc_joueur.fill = GridBagConstraints.BOTH;
        gbc_joueur.weighty = 1.0;
        gbc_joueur.gridheight = 9;
        gbc_joueur.anchor = GridBagConstraints.NORTHWEST;
        gbc_joueur.insets = new Insets(0, 0, 5, 0);
        gbc_joueur.gridx = 9;
        gbc_joueur.gridy = 0;
        add(playerPanel, gbc_joueur);

        GridBagConstraints gbc_infos = new GridBagConstraints();
        gbc_infos.insets = new Insets(0, 0, 0, 5);
        gbc_infos.weightx = 1.0;
        gbc_infos.fill = GridBagConstraints.BOTH;
        gbc_infos.gridwidth = 9;
        gbc_infos.anchor = GridBagConstraints.SOUTHWEST;
        gbc_infos.gridx = 0;
        gbc_infos.gridy = 9;
        add(infosPanel, gbc_infos);

        GridBagConstraints gbc_actions = new GridBagConstraints();
        gbc_actions.anchor = GridBagConstraints.SOUTHEAST;
        gbc_actions.fill = GridBagConstraints.BOTH;
        gbc_actions.gridx = 9;
        gbc_actions.gridy = 9;
        add(actionPanel, gbc_actions);
    }

    public void setTourNumber(int n) {
        actionPanel.setTourNumber(n);
    }

    public void setPlayerName(String str) {
        playerPanel.setPlayerName(str);
    }

    public void printInfo(JFrame frame, String msg, String title) {
        JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.PLAIN_MESSAGE);
    }

    public void addButtonListener(ActionListener buttonListener) {
        actionPanel.addButtonListener(buttonListener);
        playerPanel.addButtonListener(buttonListener);
    }


}
