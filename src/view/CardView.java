package src.view;

import javax.swing.*;
import java.awt.*;

public class CardView extends JPanel {

    protected LayoutManager layout = null;
    public String id;


    public CardView(String id, LayoutManager layout) {
        this.id = id;
        this.layout = layout;
        init();
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public Card(LayoutManager layout) {
        this.layout = layout;
        init();
    }*/


    public void init() {
        if (layout != null) {
            setLayout(layout);
        }
        setOpaque(false);
    }

    public static GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight,
                                                       double weightx, double weighty, int fill) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;

        return c;
    }

}
