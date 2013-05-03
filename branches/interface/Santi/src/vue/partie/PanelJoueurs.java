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

import model.Joueur;
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

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN_BORDER), "Liste des joueurs", TitledBorder.CENTER, TitledBorder.TOP, POLICE_30, NICE_GREY));
        // attribut du conteneur PanelJoueurs
        Dimension dim = new Dimension(homeDimension.width - 800, (homeDimension.height / 3) * 2);
        setPreferredSize(dim);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        int nbJoueurs = santiago.getListJoueurs().size();
        for (int i = 0; i < nbJoueurs; i++) {
            JPanel panelJoueur = new JPanel();
            panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
            panelJoueur.setMaximumSize(new Dimension(dim.width, dim.height / nbJoueurs));
            panelJoueur.setMinimumSize(new Dimension(dim.width, dim.height / nbJoueurs));

            JPanel info = new JPanel();
            info.setMaximumSize(new Dimension(300, 100));
            info.setMinimumSize(new Dimension(50, 0));
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

            Joueur joueur = santiago.getListJoueurs().get(i);
            JLabel nom = new JLabel("Nom : " + joueur.getNom());
            JLabel solde = new JLabel("Solde : " + Integer.toString(joueur.getSolde()) + " pesos");
            JLabel nbMarqueurs = new JLabel("Marqueurs Dipos : " + joueur.getNbMarqueurDispos());
            JLabel tuyauSup = new JLabel("Tuyau Sup : " + (joueur.hasTuyauSup() ? "Disponible" : "Plus Dispos"));

            JLabel enchereCarte = new JLabel();
            JLabel enchereConstructeur = new JLabel();
            JLabel constructeur = new JLabel();
            JLabel parole = new JLabel();
            if (joueur.getEnchereCarte() > 0) {
                constructeur = new JLabel("Enchere : " + joueur.getEnchereCarte());
            }
            if (joueur.getEnchereConstructeur() > 0) {
                constructeur = new JLabel("Soudoiement : " + joueur.getEnchereConstructeur());
            }
            if (joueur.isConstructeur()) {
                constructeur = new JLabel("Il est le constructeur de canal");
            }
            if (i == santiago.getIndiceJoueurCourant()) {
                parole = new JLabel("Il a la parole");
                panelJoueur.setBorder(BorderFactory.createLineBorder(Color.orange));
            }

            info.add(nom);
            info.add(solde);
            info.add(nbMarqueurs);
            info.add(tuyauSup);
            info.add(enchereCarte);
            info.add(enchereConstructeur);
            info.add(constructeur);
            info.add(parole);

            JPanel couleur = new JPanel();
            couleur.setMaximumSize(new Dimension(50, 100));
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

            panelJoueur.add(couleur);
            panelJoueur.add(Box.createRigidArea(new Dimension(10, 0)));
            panelJoueur.add(info);

            add(panelJoueur);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        removeAll();
        // TODO Appeler un paintComponent correct au lieu de refaire un init du
        // panel, très moche.. mais bon ça marche ^^
        initComponent();
        validate();
    }
}
