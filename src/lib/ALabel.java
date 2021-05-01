package src.lib;

import javax.swing.*;

/*
Redéfini JLabel pour rendre les champs de textes modifiables plus
facilement et plus simple à créer.
 */
public class ALabel extends JLabel {

    private String formatStr;

    public ALabel(String str, Object... x) {
        super(String.format(str, x));
        formatStr = str;
    }

    public void update(Object... x) {
        setText(String.format(formatStr, x));
    }

    static public ALabel create(JPanel panel, String str, Object... x) {
        ALabel label = new ALabel(str, x);
        panel.add(label);
        return label;
    }

    static public ALabel create(JPanel panel, int cx, int cy, String str, Object... x) {
        ALabel label = new ALabel(str, x);
        panel.add(label, "cell "+cx+" "+cy);
        return label;
    }

    static public JLabel create(JPanel panel, String str) {
        JLabel label = new JLabel(str);
        panel.add(label);
        return label;
    }

    static public JLabel create(JPanel panel, int cx, int cy, String str) {
        ALabel label = new ALabel(str);
        panel.add(label, "cell "+cx+" "+cy);
        return label;
    }
}
