package vue;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import controller.ConfigurationListener;

public class PanelConfigJoueurs extends AbstractPanel {

	// Atributs
	private static final long serialVersionUID = 1L;

	private JComboBox nbJoueurs;
	private final BgTextField[] joueursNames = new BgTextField[5];

	@Override
	public void initComponent() {
		// Initialisation des attributs/composants
		super.initComponent();
		if (getParent() != null) {
			// création des composants
			Object[] tabJoueurs = new Object[] { " ", "3", "4", "5" };
			nbJoueurs = new JComboBox(tabJoueurs);

			for (int i = 0; i < 5; i++) {
				joueursNames[i] = new BgTextField("Joueur " + (i + 1));
			}

			// Listener
			nbJoueurs.addItemListener(new ConfigurationListener(this));

			// attribut du conteneur this (panelChoice
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(GREEN_BORDER), "Joueurs",
					TitledBorder.CENTER, TitledBorder.TOP, POLICE_30, FG_COLOR));
			setLayout(new FlowLayout());
			setSize(homeDimension.width, getPreferredSize().height);
			setBackground(BG_COLOR);
			setForeground(FG_COLOR);
			setPreferredSize(getSize());

			// attributs des composants du conteneur
			nbJoueurs.setFocusable(false);
			nbJoueurs.setFont(POLICE_15);
			nbJoueurs.setBackground(NICE_GREY);
			nbJoueurs.setForeground(FG_COLOR);
			nbJoueurs.setSize(100, nbJoueurs.getPreferredSize().height);
			nbJoueurs.setPreferredSize(nbJoueurs.getSize());

			// on ajoute les composants au conteneur
			add(nbJoueurs);
			for (int i = 0; i < joueursNames.length; i++) {
				add(joueursNames[i]);
				joueursNames[i].initComponent();
			}

			// initialisation des composantsé

			isInit = true;
		} else {
			System.out
					.println(getClass().toString()
							+ " Ajouter ce panneau a un conteneur avant de l'initialiser");
		}
	}

	public void activeNbJoueurTextField(int nbJ) {
		for (int i = 0; i < nbJ; i++) {
			joueursNames[i].setEditable(true);
			joueursNames[i].setEnabled(true);
		}
		for (int i = nbJ; i < joueursNames.length; i++) {
			joueursNames[i].setEditable(false);
			joueursNames[i].setEnabled(false);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}
