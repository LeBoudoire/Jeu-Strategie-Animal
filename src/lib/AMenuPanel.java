package src.lib;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AMenuPanel extends JPanel {

    private Image scaledImg, backgroundImg;

    public AMenuPanel() {
        super();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/menu.png")));
            backgroundImg = icon.getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaledImg = backgroundImg.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return backgroundImg == null ? new Dimension(200, 200) : new Dimension(
                backgroundImg.getWidth(this), backgroundImg.getHeight(this));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.drawImage(scaledImg, 0, 0, this);
    }

}
