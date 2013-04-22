package vue.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

import vue.AbstractPanel;
import vue.configuration.PanelConfiguration;

public class PanelHome extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    // Attributs de classe
    private Image background;

    // Composant de la vue
    private PanelChoice panelChoice;
    private PanelConfiguration panelConfiguration;

    public PanelHome() {
        super();
    }

    @Override
    public void initComponent() {
        // init des composants
        super.initComponent();
        if (getParent() != null) {

            // init les Composants du panneau
            panelChoice = new PanelChoice();
            panelConfiguration = new PanelConfiguration();

            // Listener

            // attribut du conteneur PanelHome
            setLayout(new BorderLayout());
            setSize(homeDimension);
            setPreferredSize(getSize());
            // setVisible(true);
            setBorder(BorderFactory.createLineBorder(Color.GREEN));
            // on ajoute les composant au conteneur
            add(panelChoice, BorderLayout.SOUTH);
            // validate();
            // add(panelConfiguration, BorderLayout.SOUTH);

            panelChoice.initComponent();

            // panelChoice.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            // panelConfiguration.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        } else {
            System.out.println(getClass().toString() + " Ajouter ce panneau a un conteneur avant de l'initialiser");
        }
    }

    public void configurer() {
        panelChoice.setVisible(false);
        revalidate();
        repaint();
        add(panelConfiguration, BorderLayout.SOUTH);
        if (!panelConfiguration.isInit()) {
            panelConfiguration.initComponent();
        }
        panelConfiguration.validate();
        panelConfiguration.setVisible(true);
    }

    public void retourPanelConfig() {
        panelConfiguration.setVisible(false);
        revalidate();
        repaint();
        add(panelChoice, BorderLayout.SOUTH);
        if (!panelChoice.isInit()) {
            panelChoice.initComponent();
        }
        if (santiago.isConfigured()) {
            panelChoice.boutonDemarrer.setEnabled(true);
        }
        panelChoice.validate();
        panelChoice.setVisible(true);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        try {
            background = ImageIO.read(new File("img/champsDessin.jpg"));
            // g.drawImage(img, 0, 0, this);
            // Pour une image de fond
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
