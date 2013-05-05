package vue.partie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;

import model.Carte;
import vue.AbstractPanel;
import vue.home.PanelHome;

public class PanelPartie extends AbstractPanel {

    private static final long serialVersionUID = 1L;
    PanelHome panelHome;

    PanelPlateau pp;
    PanelJoueurs pj;
    PanelPartieAction ppa;

    public PanelPartie(Container parent) {
        super(parent);
        panelHome = (PanelHome) parent;
    }

    @Override
    public void initComponent() {
        super.initComponent();

        setPreferredSize(homeDimension);
        setBackground(NICE_GREY);
        setLayout(new BorderLayout());

        pp = new PanelPlateau(this);
        add(pp, BorderLayout.CENTER);
        pp.initComponent();

        pj = new PanelJoueurs(this);
        add(pj, BorderLayout.EAST);
        pj.initComponent();

        ppa = new PanelPartieAction(this);
        add(ppa, BorderLayout.SOUTH);
        ppa.initComponent();

        isInit = true;
        santiago.repercuterModification();
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }

    public static String getPathImage(Carte carte) {
        StringBuilder path = new StringBuilder("img/cartes/");
        switch (carte.getType()) {
        case PATATE:
            path.append("patate");
            break;
        case CANNE_A_SUCRE:
            path.append("canne");
            break;
        case HARICOT:
            path.append("haricot");
            break;
        case BANANE:
            path.append("banane");
            break;
        case PIMENT:
            path.append("Piment");
            break;
        default:
            break;
        }
        if (!carte.isDeserte()) {
            if (carte.getNbMarqueurMax() == 2) {// 2M, 2MSans et 1M1MSans
                if (carte.getNbMarqueurActuel() == 0) {
                    path.append("2MSans.jpg");
                } else if (carte.getNbMarqueurActuel() == 1) {
                    path.append("1M1MSans.jpg");
                } else if (carte.getNbMarqueurActuel() == 2) {
                    path.append("2M.jpg");
                }

            } else if (carte.getNbMarqueurMax() == 1) {// 1M, 1MSans
                if (carte.getNbMarqueurActuel() == 0) {
                    path.append("1MSans.jpg");
                } else if (carte.getNbMarqueurActuel() == 1) {
                    path.append("1M.jpg");
                }
            }
        } else {
            path = new StringBuilder("img/cartes/desert.jpg");
        }
        return path.toString();
    }

    public void changeBoardSelected() {
        pp.paintSelected();
    }

    public void paintPropositions() {
        pp.paintPropositions();
    }
}
