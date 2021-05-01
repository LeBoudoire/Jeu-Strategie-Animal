package src.view.panels;

import src.lib.ALabel;
import src.lib.AProgressBar;

import javax.swing.*;
import java.awt.*;

public class SummaryTabPanel extends JPanel {

    public AProgressBar xpBar;
    public ALabel levelLabel;

    public ALabel foodLabel;
    public ALabel populationLabel;


    public ALabel defenseLabel;
    public ALabel reproductionLabel;
    public ALabel agilityLabel;
    public ALabel hostilityLabel;
    public ALabel resilienceLabel;
    public ALabel alimentationLabel;
    public ALabel poisonLabel;
    public ALabel mobilityLabel;

    public JLabel competencesLabel;
    public ALabel attackCompetenceLabel;
    public ALabel defenseCompetenceLabel;
    public ALabel neutralCompetenceLabel;

    public SummaryTabPanel() {
        init();
    }

    private void init() {
        GroupLayout summaryTabPanelGL = new GroupLayout(this);

        // Group 1

        xpBar = AProgressBar.create(this, "%dXP / %dXP", 0, 0);
        levelLabel = ALabel.create(this, "Niveau %d (bonus déblocables : %d)", 0, 0);

        // Group 2

        JPanel group2Panel = new JPanel();
        group2Panel.setLayout(new GridLayout(0, 1, 0, 0));
        group2Panel.setOpaque(false);

        foodLabel = ALabel.create(group2Panel, "Nourriture : %d", 0);
        populationLabel = ALabel.create(group2Panel, "Population : %d", 0);

        // Group 3

        JPanel group3Panel = new JPanel();
        group3Panel.setLayout(new GridLayout(0, 2, 0, 0));
        group3Panel.setOpaque(false);

        hostilityLabel = ALabel.create(group3Panel, "Hostilité : %d", 0);
        defenseLabel = ALabel.create(group3Panel, "Défense : %d", 0);
        reproductionLabel = ALabel.create(group3Panel, "Reproduction : %d", 0);
        agilityLabel = ALabel.create(group3Panel, "Nb tour déplacement : %d", 0);
        resilienceLabel = ALabel.create(group3Panel, "Résilience : %f", 0.0);
        poisonLabel = ALabel.create(group3Panel, "Empoisonné : %s", "Non");
        mobilityLabel = ALabel.create(group3Panel, "Mobilité : %s", "Non");

        // Group 4

        JPanel group4Panel = new JPanel();
        group4Panel.setLayout(new GridLayout(0, 1, 0, 0));
        group4Panel.setOpaque(false);

        competencesLabel = ALabel.create(group4Panel, "Compétences : ");
        attackCompetenceLabel = ALabel.create(group4Panel, "- Attaque : %d/%d %s", 0, 0, "");
        defenseCompetenceLabel = ALabel.create(group4Panel, "- Défense : %d/%d %s", 0, 0, "");
        neutralCompetenceLabel = ALabel.create(group4Panel, "- Neutre : %d/%d %s", 0, 0, "");

        // Panel

        summaryTabPanelGL.setHorizontalGroup(
                summaryTabPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(summaryTabPanelGL.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(summaryTabPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(summaryTabPanelGL.createSequentialGroup()
                                                .addComponent(xpBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(levelLabel))
                                        .addComponent(group2Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(group3Panel, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                        .addComponent(group4Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        summaryTabPanelGL.setVerticalGroup(
                summaryTabPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(summaryTabPanelGL.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(summaryTabPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(levelLabel)
                                        .addComponent(xpBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(35) //default : 18
                                .addComponent(group2Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(35)
                                .addComponent(group3Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(35)
                                .addComponent(group4Panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(259, Short.MAX_VALUE))
        );

        setLayout(summaryTabPanelGL);

    }

}
