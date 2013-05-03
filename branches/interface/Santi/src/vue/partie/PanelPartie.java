package vue.partie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Observable;

import model.Carte;
import model.Joueur;
import model.NiveauPartie;
import vue.AbstractPanel;
import vue.home.PanelHome;

public class PanelPartie extends AbstractPanel {

    private static final long serialVersionUID = 1L;
    PanelHome panelHome;

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

        // System.out.println(santiago);
        // FIXME à enlever, pour test
        // ---------------------------
        santiago.setNiveauPartie(NiveauPartie.FACILE);
        ArrayList<Joueur> listjoueurs = new ArrayList<>();
        listjoueurs.add(new Joueur("tull", "bleu"));
        listjoueurs.add(new Joueur("andre", "vert"));
        listjoueurs.add(new Joueur("marc", "gris"));
        santiago.setListJoueurs(listjoueurs);
        santiago.initPartie();
        santiago.devoilerCarte();

        // santiago.encherePositionCanal(new PositionSegment(0, 0, 0, 1), 3);
        // santiago.encherePositionCanal(new PositionSegment(1, 1, 1, 2), 5);
        // santiago.encherePositionCanal(new PositionSegment(3, 4, 4, 4), 4);
        //
        // PositionSegment ps = new PositionSegment(0, 0, 2, 0);
        // santiago.placerCanalDeSonChoix(ps);

        santiago.setPhaseFinie(false);
        // santiago.encherir(1);
        // santiago.encherir(2);
        // santiago.encherir(3);
        // santiago.poserUneCarte(0, 1, 1);
        // santiago.poserUneCarte(0, 5, 1);
        // santiago.poserUneCarte(0, 4, 1);
        // santiago.poserUneCarte(0, 3, 3);

        // santiago.devoilerCarte();
        santiago.setIndiceJoueurCourant(santiago.positionApresConstructeur());
        // ----------------------------

        PanelPlateau pp = new PanelPlateau(this);
        add(pp, BorderLayout.CENTER);
        pp.initComponent();

        PanelJoueurs pj = new PanelJoueurs(this);
        add(pj, BorderLayout.EAST);
        pj.initComponent();

        PanelPartieAction ppa = new PanelPartieAction(this);
        add(ppa, BorderLayout.SOUTH);
        ppa.initComponent();

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

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
        return path.toString();
    }

}
