package vue.partie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;

import model.Carte;
import model.PositionCase;
import model.PositionSegment;
import vue.AbstractPanel;
import vue.home.PanelHome;

public class PanelPartie extends AbstractPanel {

    private static final long serialVersionUID = 1L;
    PanelHome panelHome;
    static PositionCase posCaseCourante;
    static PositionSegment posSegCourant;

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

        PanelPartieAction ppa = new PanelPartieAction(this);
        add(ppa, BorderLayout.SOUTH);
        ppa.initComponent();

        PanelJoueurs pj = new PanelJoueurs(this);
        add(pj, BorderLayout.EAST);
        pj.initComponent();

        PanelPlateau pp = new PanelPlateau(this);
        add(pp, BorderLayout.CENTER);
        pp.initComponent();

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
