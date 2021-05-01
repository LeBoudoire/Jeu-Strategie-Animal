package src.view;

import src.view.cards.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Main frame
public class AnimalView extends JFrame {

    private String title = "@nimal";
    private int width = 1200;
    private int height = 800;

    private JPanel container = new JPanel(){
        ImageIcon icon;

        {
            try {
                icon = new ImageIcon(ImageIO.read(getClass().getResource("/src/ressources_graphiques/background.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final Image backgroundImg = this.icon.getImage();

        private Image scaledImg;

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

            int x = 0;
            int y = 0;
            graphics2D.drawImage(scaledImg, x, y, this);
        }
    };
    private LayoutManager containerLayout = new CardLayout(15, 15);

    public CardLayout cardLayout;

    public DefaultCardView defaultCardView;
    public GameCardView gameCardView;
    public IntroCardView introCardView;

    public AnimalView() {
        initMainFrame();
        initCards();
        setVisible(true);
    }

    public void initMainFrame() {
        setTitle(title);
        try {
            setIconImage(ImageIO.read(getClass().getResource("/src/ressources_graphiques/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(width, height);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        UIManager.put("textText", Color.red);
        UIManager.put("Button.disabledText", Color.black);

        setContentPane(container);

        //Border padding = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //container.setBorder(padding);

        container.setLayout(containerLayout);
        cardLayout = (CardLayout) container.getLayout();
    }

    public void initCards() {
        defaultCardView = new DefaultCardView();
        gameCardView = new GameCardView();
        introCardView = new IntroCardView();

        container.add(defaultCardView, DefaultCardView.id);
        container.add(gameCardView, GameCardView.id);
        container.add(introCardView, IntroCardView.id);
    }

    public void setActiveCardView(CardView cardView) {
        cardLayout.show(container, cardView.id);
    }

}
