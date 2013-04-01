package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.Santiago;

public class PanelConfigJoueurs extends JPanel implements Observer {

	// Atributs
	public final static Font POLICE = new Font("Helvetica", Font.TYPE1_FONT, 60);
	private static final long serialVersionUID = 1L;
	private Santiago santiago;
	public Dimension homeDimension;
	private boolean isInit;

	public final static Color FG_COLOR = new Color(102, 204, 255);
	public final static Color BG_COLOR = new Color(204, 255, 255, 50);

	public PanelConfigJoueurs() {
		super();
		santiago = null;
		isInit = false;
		homeDimension = null;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

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
					TitledBorder.CENTER, TitledBorder.TOP, PanelHome.POLICE,
					FG_COLOR));
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setSize(homeDimension.width, nbJoueurs.getPreferredSize().height);
			setOpaque(false);
			setBackground(BG_COLOR);
			setForeground(FG_COLOR);
			setPreferredSize(getSize());

			// attributs des composants du conteneur
			nbJoueurs.setOpaque(false);
			nbJoueurs.setFocusable(false);
			nbJoueurs.setBackground(new Color(204, 255, 255, 200));
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
}
