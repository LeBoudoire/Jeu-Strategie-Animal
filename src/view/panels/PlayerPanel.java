package src.view.panels;

import src.lib.AMenuPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerPanel extends AMenuPanel {

    private String title = "%s";
    private LayoutManager layout = new CardLayout();

    public JTabbedPane tabs;
    public SummaryTabPanel summaryTab;
    public TreeTabPanel treeTab;
    public JPanel mutationsTab;

    public PlayerPanel() {
        super();
        init();
    }

    private void init() {
        setLayout(layout);
        setOpaque(false);

        createBorders();
        createTabs();

        createSummaryTab();
    }

    private void createBorders() {
        Border lineBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createTitledBorder(lineBorder, String.format(title, "Joueur en cours"));
        setBorder(border);
    }

    private void createTabs() {
        UIManager.put("TabbedPane.contentOpaque", false);
        tabs = new JTabbedPane();

        summaryTab = new SummaryTabPanel();
        summaryTab.setOpaque(false);
        treeTab = new TreeTabPanel();
        treeTab.setOpaque(false);
        //mutationsTab = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //mutationsTab.setOpaque(false);

        tabs.add("Résumé", summaryTab);
        tabs.add("Arbre", treeTab);
        //tabs.add("Mutations", mutationsTab);

        add(tabs);
    }

    public void setPlayerName(String str) {
        Border lineBorder = BorderFactory.createEmptyBorder();
        Border border = BorderFactory.createTitledBorder(lineBorder, String.format(title, str));
        setBorder(border);
    }

    public void addButtonListener(ActionListener buttonListener) {
        treeTab.addButtonListener(buttonListener);
    }

    private void createSummaryTab() {


    }



}
