package vue.partie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import vue.AbstractPanel;

public class PanelJoueurs extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	public PanelJoueurs(Container parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	// Méthode dans laquelle on ajoute tous les composants voulu au JPanel
    @Override
    public void initComponent() {
        // init des composants
        super.initComponent();

        // attribut du conteneur PanelJoueurs
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN_BORDER), "Liste des joueurs", TitledBorder.CENTER,
                TitledBorder.TOP, POLICE_30, NICE_GREY));
        Dimension dim = new Dimension(homeDimension.width / 4 * 2, (homeDimension.height / 4) * 3);
        setPreferredSize(dim);
        
        JPanel joueur = new JPanel();
        for (int i = 0; i < santiago.getListJoueurs().size(); i ++) {
        	setLayout(new GridLayout(1, 2));
        	setBorder(BorderFactory.createLineBorder(Color.GREEN));
        	setPreferredSize(new Dimension(dim.width, dim.height / santiago.getListJoueurs().size()));
        	JPanel info = new JPanel();
        	JLabel nom = new JLabel(santiago.getListJoueurs().get(i).getNom());
        	JLabel constructeur = new JLabel(" ");
        	if (santiago.getListJoueurs().get(i).isConstructeur()) {
        		constructeur = new JLabel("Constructeur de canaux");
        	}
        	JLabel solde = new JLabel(Integer.toString(santiago.getListJoueurs().get(i).getSolde()));
        	info.add(nom);
        	info.add(constructeur);
        	info.add(solde);
        	
        	JPanel couleur = new JPanel();
        	couleur.setPreferredSize(new Dimension(20,20));
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
        	joueur.add(info);
        }
    }
	
}
