package vue.home;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import vue.AbstractPanel;
import vue.configuration.PanelConfiguration;
import vue.partie.PanelPartie;

public class PanelHome extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    // Attributs de classe
    private Image background;

    // Composant de la vue
    final PanelChoice panelChoice;
    final PanelConfiguration panelConfiguration;
    // private PanelCharger panelCharger = new PanelCharger();
    PanelPartie panelPartie;
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
        // FIXME peut pas init tant qu'on a pas configurer des partie
        // panelPartie.initComponent();

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // FIXME a décommenter
        if (santiago.isConfigured()) {
            panelChoice.boutonDemarrer.setEnabled(true);
            if (!panelPartie.isInit()) {
                panelPartie.initComponent();
            }
        } else {
            panelChoice.boutonDemarrer.setEnabled(false);
        }
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

    public void retourPartie() {
        remove(panelPartie);
        panelPartie = new PanelPartie(this);
        cards.add(panelPartie, listContent[0]);
        panelChoice.boutonDemarrer.setEnabled(false);
        cardLayout.show(cards, listContent[3]);
    }
}
