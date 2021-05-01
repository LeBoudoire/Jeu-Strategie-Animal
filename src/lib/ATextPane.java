package src.lib;

import javax.swing.*;

public class ATextPane extends JTextPane {

    private String formatStr;

    public ATextPane(String str, Object... x) {
        super();
        setText(String.format(str, x));
        setEditable(false);
        formatStr = str;
    }

    public void update(Object... x) {
        setText(String.format(formatStr, x));
    }

    static public ATextPane create(JPanel panel, String str, Object... x) {
        ATextPane textPane = new ATextPane(str, x);
        panel.add(textPane);
        return textPane;
    }

}
