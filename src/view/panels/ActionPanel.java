package src.view.panels;

import src.lib.AButton;
import src.lib.AMenuPanel;
import src.view.AnimalView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionPanel extends AMenuPanel {

    private String title = "Tour %d";

    private JPanel topPanel = new JPanel();
    private JPanel subPanel = new JPanel();

    public JButton treeButton, attackButton, moveButton, skipButton;

    public ActionPanel() {
        super();
        init();
    }

    private void init() {
        GroupLayout actionPanelGL = new GroupLayout(this);

        treeButton = new AButton("Améliorer l'arbre");
        topPanel.add(treeButton);
        topPanel.setLayout(new FlowLayout());
        topPanel.setOpaque(false);

        attackButton = new AButton("Attaquer");
        subPanel.add(attackButton);
        moveButton = new AButton("Se déplacer");
        subPanel.add(moveButton);
        skipButton = new AButton("Passer");
        subPanel.add(skipButton);
        subPanel.setLayout(new FlowLayout());
        subPanel.setOpaque(false);

        actionPanelGL.setHorizontalGroup(
                actionPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(actionPanelGL.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(actionPanelGL.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(subPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(121, Short.MAX_VALUE))
        );
        actionPanelGL.setVerticalGroup(
                actionPanelGL.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(actionPanelGL.createSequentialGroup()
                                .addContainerGap(81, Short.MAX_VALUE)
                                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(subPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        setLayout(actionPanelGL);
        setOpaque(false);

        Border lineBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createTitledBorder(lineBorder, String.format(title, 0));
        setBorder(border);
    }



    public void setTourNumber(int n) {
        Border lineBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createTitledBorder(lineBorder, String.format(title, n));
        setBorder(border);
    }

    public void addButtonListener(ActionListener buttonListener) {
        treeButton.addActionListener(buttonListener);
        attackButton.addActionListener(buttonListener);
        moveButton.addActionListener(buttonListener);
        skipButton.addActionListener(buttonListener);
    }

    public String printDialog(JFrame frame, String[] options) {
        return (String)JOptionPane.showInputDialog(frame, "Quel joueur voulez-vous attaquer ?", "Attaque", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
}
