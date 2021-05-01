package src.view.panels;

import src.lib.ATextPane;
import src.model.GameModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class MapPanel extends JPanel {

    private String title = "MapPanel";
    private LayoutManager layout = new GridLayout(2, 2, 0, 0);

    private Image scaledImg, mapImg;
    Dictionary speciesImg;
    private int horizontalPadding = 15;
    private int verticalPadding = 20;

    private String[] nwSpecies, neSpecies, swSpecies, seSpecies;
    private JPanel nwPanel, nePanel, swPanel, sePanel;
    private boolean hasChanged = false;

    private Dimension size = getSize();

    ATextPane nwLabel, neLabel, swLabel, seLabel;

    public MapPanel() {
        init();
        try {
            mapImg = ImageIO.read(getClass().getResource("/src/ressources_graphiques/map.png"));

            speciesImg = new Hashtable();
            for (int i = 0; i < GameModel.MAX_JOUEURS; i++) {
                ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/"+ GameModel.SPECIES_LIST[i] +".png")));
                Image img = icon.getImage();
                speciesImg.put(GameModel.SPECIES_LIST[i], img);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        setLayout(layout);
        setOpaque(false);

        nwSpecies = new String[]{"test"};

        nwPanel = new JPanel();
        nwPanel.setOpaque(false);
        nePanel = new JPanel();
        nePanel.setOpaque(false);
        swPanel = new JPanel();
        swPanel.setOpaque(false);
        sePanel = new JPanel();
        sePanel.setOpaque(false);


        add(nwPanel);
        add(nePanel);
        add(swPanel);
        add(sePanel);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaledImg = mapImg.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return mapImg == null ? new Dimension(200, 200) : new Dimension(
                mapImg.getWidth(this), mapImg.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();

        int x = 0;
        int y = 0;
        graphics2D.drawImage(scaledImg, x, y, this);

        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setColor(Color.white);
        graphics2D.drawLine(getWidth()/2, verticalPadding+1, getWidth()/2, getHeight()-verticalPadding-2);
        graphics2D.drawLine(horizontalPadding+1, getHeight()/2, getWidth()-horizontalPadding-2, getHeight()/2);

        if(size != null && !size.equals(getSize())) {
            size = getSize();

            update(nwPanel, nwSpecies);
            update(nePanel, neSpecies);
            update(swPanel, swSpecies);
            update(sePanel, seSpecies);
        }

        graphics2D.dispose();
    }

    // pos = "sw" || "nw" || "se" || "se"
    public void setPositionSpecies(String pos, String[] newSpecies) {
        JPanel panel;
        String[] posSpecies;

        switch (pos) {
            case "nw":
                nwSpecies = newSpecies;
                posSpecies = nwSpecies;
                panel = nwPanel;
                break;
            case "sw":
                swSpecies = newSpecies;
                posSpecies = swSpecies;
                panel = swPanel;
                break;
            case "ne":
                neSpecies = newSpecies;
                posSpecies = neSpecies;
                panel = nePanel;
                break;
            case "se":
                seSpecies = newSpecies;
                posSpecies = seSpecies;
                panel = sePanel;
                break;
            default:
                return;
        }

        hasChanged = true;
        update(panel, posSpecies);
    }

    private void update(JPanel panel, String[] species) {
        panel.removeAll();

        if (species != null) {
            int rows = (species.length < 2) ? 1 : 2;
            int columns = (int) Math.ceil(species.length / 2.0);
            panel.setLayout(new GridLayout(rows, columns, 10, 10));

            for (String specie: species) {
                Image img = (Image) speciesImg.get(specie);

                int maxPanelImgSize = Math.min(panel.getWidth()/columns, panel.getHeight()/rows);
                int imgSize = Math.min(Math.min(400, maxPanelImgSize), 100); // images carrées

                Image resizedImg = img.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImg);

                JLabel imgLabel = new JLabel(resizedIcon);

                imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imgLabel.setVerticalAlignment(SwingConstants.CENTER);

                panel.add(imgLabel);
            }
        }

        panel.revalidate();
        panel.repaint();

        hasChanged = false;
    }

}
