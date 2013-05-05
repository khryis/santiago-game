package vue.partie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
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
            JLabel parole = new JLabel();
            if (joueur.getEnchereCarte() > 0) {
                enchereCarte = new JLabel("Enchere : " + joueur.getEnchereCarte());
            }
            if (joueur.getEnchereConstructeur() > 0) {
                enchereConstructeur = new JLabel("Soudoiement : " + joueur.getEnchereConstructeur());
            }
            if (i == santiago.getIndiceJoueurCourant()) {
                parole = new JLabel("Il a la parole");
                panelJoueur.setBorder(BorderFactory.createLineBorder(Color.orange));
            }
            //
            // if (!santiago.getTabEnchere().isEmpty()) {
            // if (joueur.equals(santiago.enchereMax())) {
            // parole = new JLabel("Il a la parole");
            // panelJoueur.setBorder(BorderFactory.createLineBorder(Color.orange));
            // santiago.setIndiceJoueurCourant(i);
            // }
            // }

            info.add(nom);
            info.add(solde);
            info.add(nbMarqueurs);
            info.add(tuyauSup);
            info.add(enchereCarte);
            info.add(enchereConstructeur);
            info.add(parole);

            final String imgPath;
            switch ((santiago.getListJoueurs().get(i).getCouleur())) {
            case "vert":
                // imgPath = Color.WHITE;
                imgPath = "img/Vert.jpg";
                break;
            case "jaune":
                // imgPath = Color.BLACK;
                imgPath = "img/Jaune.jpg";
                break;
            case "bleu":
                // imgPath = Color.BLUE;
                imgPath = "img/Bleu.jpg";
                break;
            case "violet":
                // imgPath = Color.MAGENTA;
                imgPath = "img/Violet.jpg";
                break;
            case "rouge":
                // imgPath = Color.GRAY;
                imgPath = "img/Rouge.jpg";
                break;
            default:
                // imgPath = Color.DARK_GRAY;
                imgPath = "img/Bleu.jpg";
                break;
            }

            JPanel image = new JPanel() {
                private static final long serialVersionUID = 1L;

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    try {
                        Image background = ImageIO.read(new File(imgPath));
                        // Pour l'image de fond
                        g.drawImage(background, 0, 0, 80, 80, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            image.setMaximumSize(new Dimension(80, 80));
            image.setMinimumSize(new Dimension(80, 80));

            JPanel constructeur = new JPanel() {
                private static final long serialVersionUID = 1L;

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    try {
                        Image background = ImageIO.read(new File("img/constructeur.jpg"));
                        // Pour l'image de fond
                        g.drawImage(background, 0, 0, 80, 80, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            constructeur.setMaximumSize(new Dimension(80, 80));
            constructeur.setMinimumSize(new Dimension(80, 80));

            panelJoueur.add(image);
            panelJoueur.add(Box.createRigidArea(new Dimension(10, 0)));
            panelJoueur.add(info);
            panelJoueur.add(Box.createRigidArea(new Dimension(10, 0)));
            if (joueur.isConstructeur()) {
                panelJoueur.add(constructeur);
            }

            add(panelJoueur);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Appeler un paintComponent correct au lieu de refaire un init du
        // panel, très moche.. mais bon ça marche ^^
        removeAll();
        initComponent();
        validate();
    }
}
