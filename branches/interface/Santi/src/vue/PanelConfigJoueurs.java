package vue;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

public class PanelConfigJoueurs extends AbstractPanel {

	// Atributs
	private static final long serialVersionUID = 1L;

	@Override
	public void initComponent() {
		// Initialisation des attributs/composants
		if (getParent() != null) {
			homeDimension = getParent().getSize();
			santiago = ((PanelConfiguration) getParent()).getSantiago();

			// création des composants
			Object[] tabJoueurs = new Object[] { 3, 4, 5 };
			JComboBox nbJoueurs = new JComboBox(tabJoueurs);

			// Listener

			// attribut du conteneur this (panelChoice
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.GREEN), "Joueurs",
					TitledBorder.CENTER, TitledBorder.TOP,
					AbstractPanel.POLICE_30, FG_COLOR));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setSize(homeDimension.width, nbJoueurs.getPreferredSize().height);
			setOpaque(false);
			setBackground(BG_COLOR);
			setForeground(FG_COLOR);
			setPreferredSize(getSize());

			// attributs des composants du conteneur
			nbJoueurs.setOpaque(false);
			nbJoueurs.setFocusable(false);
			nbJoueurs.setBackground(BG_COLOR);
			nbJoueurs.setForeground(FG_COLOR);

			// on ajoute les composants au conteneur
			add(nbJoueurs);

			isInit = true;
		} else {
			System.out
					.println(getClass().toString()
							+ " Ajouter ce panneau a un conteneur avant de l'initialiser");
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}
