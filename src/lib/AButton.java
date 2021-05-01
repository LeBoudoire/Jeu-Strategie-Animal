package src.lib;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AButton extends JButton {

    public AButton(String str){
        super(str);
        try {
            setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/bouton/icon.png"))));
            setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/bouton/rollover.png"))));
            setDisabledIcon(new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/bouton/disabled.png"))));
            setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/bouton/pressed.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setForeground(Color.BLACK);
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        setBorder(null);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

}
