package src.view.panels;

import src.lib.ALabel;
import src.lib.AMenuPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfosPanel extends AMenuPanel {

    private String title = "Informations partie";

    JPanel remainingSpeciesPanel = new JPanel();
    public JLabel remainingSpeciesLabel;

    public InfosPanel() {
        super();
        init();
    }

    private void init() {
        GroupLayout infosPanelGL = new GroupLayout(this);

        remainingSpeciesLabel = new JLabel("Espèces restantes :");

        remainingSpeciesPanel.setLayout(new GridLayout(4, 0, 50, 0));
        remainingSpeciesPanel.setOpaque(false);

        infosPanelGL.setHorizontalGroup(
                infosPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(infosPanelGL.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(infosPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(remainingSpeciesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(remainingSpeciesLabel))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infosPanelGL.setVerticalGroup(
                infosPanelGL.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(infosPanelGL.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addComponent(remainingSpeciesLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(remainingSpeciesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        setLayout(infosPanelGL);
        setOpaque(false);

        Border lineBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createTitledBorder(lineBorder, title);
        setBorder(border);
    }

    public void setRemainingSpecies(String[] speciesNames, int[] speciesValues) {
        remainingSpeciesPanel.removeAll();
        remainingSpeciesPanel.revalidate();
        remainingSpeciesPanel.repaint();

        int n = Math.min(speciesNames.length, speciesValues.length);
        for (int i = 0; i < n; i++) {
            ALabel.create(remainingSpeciesPanel, String.format("%s (%d)", speciesNames[i], speciesValues[i]));
        }
    }

}
