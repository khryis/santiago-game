package vue.home;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import vue.AbstractPanel;
import vue.configuration.PanelConfiguration;
import vue.partie.PanelPartie;

public class PanelHome extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    // Attributs de classe
    private Image background;

    // Composant de la vue
    private PanelChoice panelChoice;
    private PanelConfiguration panelConfiguration;
    // private PanelCharger panelCharger = new PanelCharger();
    private PanelPartie panelPartie;
    // private PanelReglages panelReglages = new PanelReglages();
    public JPanel cards = new JPanel();
    public CardLayout cardLayout = new CardLayout();
    public String[] listContent = { "Demarrer", "Configuration", "Reglages", "Choix", "Charger" };
    int indice = 0;

    public PanelHome(Container parent) {
        super(parent);
        panelChoice = new PanelChoice(this);
        panelConfiguration = new PanelConfiguration(this);
        panelPartie = new PanelPartie(this);
    }

    @Override
    public void initComponent() {
        // init des composants
        super.initComponent();

        // attribut du conteneur PanelHome
        setLayout(new BorderLayout());
        setPreferredSize(homeDimension);
        setBorder(BorderFactory.createLineBorder(Color.GREEN));

        // on ajoute les composant au panel CardLayout
        cards.setLayout(cardLayout);
        cards.setPreferredSize(homeDimension);
        cards.setOpaque(false);
        cards.add(panelChoice, listContent[3]);
        cards.add(panelConfiguration, listContent[1]);
        cards.add(panelPartie, listContent[0]);

        add(cards, BorderLayout.SOUTH);

        // init des panels
        panelChoice.initComponent();
        panelConfiguration.initComponent();
        panelPartie.initComponent();

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            background = ImageIO.read(new File("img/champsDessin.jpg"));
            // Pour l'image de fond
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
