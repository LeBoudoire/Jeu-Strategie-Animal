package src.lib;

import javax.swing.*;

public class AProgressBar extends JProgressBar {

    private String formatStr;

    public AProgressBar(String str, int per) {
        super(0);
        formatStr = str;
        setStringPainted(true);
        setString(String.format(str, per));
        setValue(per);
    }

    public AProgressBar(String str, int num, int den) {
        super(0);
        formatStr = str;
        setStringPainted(true);
        setString(String.format(str, num, den));
        setValue(den > 0 ? 100*num/den : 0);
    }

    public void update(int per) {
        setString(String.format(formatStr, per));
        setValue(per);
    }

    public void update(int num, int den) {
        setString(String.format(formatStr, num, den));
        setValue(den > 0 ? 100*num/den : 0);
    }

    static public AProgressBar create(JPanel panel, String str, int per) {
        AProgressBar pb = new AProgressBar(str, per);
        panel.add(pb);
        return pb;
    }

    static public AProgressBar create(JPanel panel, String str, int num, int den) {
        AProgressBar pb = new AProgressBar(str, num, den);
        panel.add(pb);
        return pb;
    }

}
