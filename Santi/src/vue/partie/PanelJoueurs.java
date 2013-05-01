package vue.partie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import vue.AbstractPanel;

public class PanelJoueurs extends AbstractPanel {
    private static final long serialVersionUID = 1L;

    public PanelJoueurs(Container parent) {
        super(parent);
    }

    // Méthode dans laquelle on ajoute tous les composants voulu au JPanel
    @Override
    public void initComponent() {
        // init des composants
        super.initComponent();

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN_BORDER), "Liste des joueurs", TitledBorder.CENTER,
                TitledBorder.TOP, POLICE_30, NICE_GREY));
        // attribut du conteneur PanelJoueurs
        Dimension dim = new Dimension(homeDimension.width - 800, (homeDimension.height / 3) * 2);
        setPreferredSize(dim);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        int nbJoueurs = santiago.getListJoueurs().size();
        for (int i = 0; i < nbJoueurs; i++) {
            JPanel joueur = new JPanel();
            joueur.setLayout(new BoxLayout(joueur, BoxLayout.X_AXIS));
            joueur.setMaximumSize(new Dimension(dim.width, dim.height / nbJoueurs));
            joueur.setMinimumSize(new Dimension(dim.width, dim.height / nbJoueurs));

            JPanel info = new JPanel();
            info.setMaximumSize(new Dimension(300, 70));
            info.setMinimumSize(new Dimension(50, 0));
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

            JLabel nom = new JLabel("Nom : " + santiago.getListJoueurs().get(i).getNom());
            JLabel solde = new JLabel("Solde : " + Integer.toString(santiago.getListJoueurs().get(i).getSolde()) + " pesos");
            JLabel constructeur = new JLabel();
            JLabel parole = new JLabel();
            if (santiago.getListJoueurs().get(i).isConstructeur()) {
                constructeur = new JLabel("Il est le constructeur de canal");
            }
            if (i == santiago.getIndiceJoueurCourant()) {
                parole = new JLabel("Il a la parole");
                joueur.setBorder(BorderFactory.createLineBorder(Color.orange));
            }

            info.add(nom);
            info.add(solde);
            info.add(constructeur);
            info.add(parole);

            JPanel couleur = new JPanel();
            couleur.setMaximumSize(new Dimension(50, 70));
            couleur.setMinimumSize(new Dimension(20, 20));
            Color c = Color.DARK_GRAY;
            switch ((santiago.getListJoueurs().get(i).getCouleur())) {
            case "blanc":
                c = Color.WHITE;
                break;
            case "noir":
                c = Color.BLACK;
                break;
            case "bleu":
                c = Color.BLUE;
                break;
            case "violet":
                c = Color.MAGENTA;
                break;
            case "gris":
                c = Color.GRAY;
                break;
            default:
                c = Color.DARK_GRAY;
                break;
            }
            couleur.setBackground(c);

            joueur.add(couleur);
            joueur.add(Box.createRigidArea(new Dimension(10, 0)));
            joueur.add(info);

            add(joueur);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        removeAll();
        initComponent();
        validate();
    }
}
