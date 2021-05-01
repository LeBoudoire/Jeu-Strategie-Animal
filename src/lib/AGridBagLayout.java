package src.lib;

import java.awt.*;

public class AGridBagLayout {

    static public GridBagLayout createGridBagLayout(int[] cwi, int[] rhe, double[] cwe, double[] rwe) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = cwi;
        gbl.rowHeights = rhe;
        gbl.columnWeights = cwe;
        gbl.rowWeights = rwe;
        return gbl;
    }

}
