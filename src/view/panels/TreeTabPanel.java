package src.view.panels;

import src.lib.AButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TreeTabPanel extends JPanel {

    private LayoutManager layout = new GridLayout(9, 3, 0, 0);
    public AButton styleOffensiveBtn, styleDefensiveBtn, styleNeutralBtn;
    public AButton progressOffensive, progressDefensive, progressNeutral;
    private JLabel[][] nodeLabels = new JLabel[3][7];

    public TreeTabPanel() {
        init();
    }

    private void init() {
        setLayout(layout);

        styleOffensiveBtn = new AButton("Arbre offensif");
        styleOffensiveBtn.setEnabled(false);
        add(styleOffensiveBtn);

        styleDefensiveBtn = new AButton("Arbre défensif");
        add(styleDefensiveBtn);

        styleNeutralBtn = new AButton("Arbre neutre");
        add(styleNeutralBtn);

        for (int i = 0; i < 7; i++) {
            for (int style = 0; style < 3; style++) {
                nodeLabels[style][i] = new JLabel();
                try {
                    nodeLabels[style][i].setIcon(getIcon(style, i, false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nodeLabels[style][i].setHorizontalAlignment(SwingConstants.CENTER);
                add(nodeLabels[style][i]);
            }
        }

        progressOffensive = new AButton("Améliorer");
        add(progressOffensive);

        progressDefensive = new AButton("Améliorer");
        progressDefensive.setEnabled(false);
        add(progressDefensive);

        progressNeutral = new AButton("Améliorer");
        progressNeutral.setEnabled(false);
        add(progressNeutral);
    }

    private ImageIcon getIcon(int style, int i, boolean active) throws IOException {
        String endText = (active) ? "" : "_dis";
        if (i == 0) {
            return new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/arbre/"+style+"_head"+endText+".png")));
        }
        else if (i == 6) {
            return new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/arbre/"+style+"_tail"+endText+".png")));
        }
        return new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/arbre/"+style+"_body"+endText+".png")));
    }

    public void set(int style, int i) {
        for (int j = 0; j < i; j++) {
            try {
                ImageIcon icon = getIcon(style, j, true);
                nodeLabels[style][j].setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int j = i; j < 7; j++) {
            try {
                ImageIcon icon = getIcon(style, j, false);
                nodeLabels[style][j].setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addButtonListener(ActionListener buttonListener) {
        styleOffensiveBtn.addActionListener(buttonListener);
        styleDefensiveBtn.addActionListener(buttonListener);
        styleNeutralBtn.addActionListener(buttonListener);
        progressOffensive.addActionListener(buttonListener);
        progressDefensive.addActionListener(buttonListener);
        progressNeutral.addActionListener(buttonListener);
    }

}
