package vue.configuration;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Joueur;
import model.NiveauPartie;
import model.Santiago;
import vue.AbstractPanel;
import vue.home.PanelHome;

public class PanelConfiguration extends AbstractPanel {
    private static final long serialVersionUID = 1L;

    PanelConfigJoueurs panelJoueurs;
    PanelConfigPartie panelPartie;
    PanelConfigRetour panelRetour;

    PanelHome panelHome;// parent

    public PanelConfiguration(Container parent) {
        super(parent);
        panelHome = (PanelHome) parent;
    }

    @Override
    public void initComponent() {
        super.initComponent();
        // attribut du conteneur this (panelChoice
        setBackground(BG_TRANSPARENT);
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(homeDimension.width, homeDimension.height / 5 * 3));

        // creation des composants du panneaux
        panelJoueurs = new PanelConfigJoueurs(this);
        panelPartie = new PanelConfigPartie(this);
        panelRetour = new PanelConfigRetour(this);
        JPanel intermediaire = new JPanel();
        intermediaire.setLayout(new BoxLayout(intermediaire, BoxLayout.PAGE_AXIS));
        intermediaire.setPreferredSize(new Dimension(homeDimension.width, homeDimension.height / 5 * 3));
        intermediaire.setOpaque(false);
        // on ajoute les composants au conteneur
        intermediaire.add(panelJoueurs);
        intermediaire.add(panelPartie);
        intermediaire.add(panelRetour);
        add(intermediaire, BorderLayout.SOUTH);
        // on les initalises
        panelJoueurs.initComponent();
        panelPartie.initComponent();
        panelRetour.initComponent();

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }

    public void retourAuPanelChoice() {
        ArrayList<Joueur> listJoueurs = panelJoueurs.getListJoueurs();
        NiveauPartie niveauPartie = panelPartie.getNiveauPartie();
        if (listJoueurs != null && niveauPartie != null) {
            santiago.setListJoueurs(listJoueurs);
            santiago.setNiveauPartie(niveauPartie);
            santiago.initPartie();
        }
        panelHome.cardLayout.show(panelHome.cards, panelHome.listContent[3]);
    }

    @Override
    public Santiago getSantiago() {
        return santiago;
    }
}
