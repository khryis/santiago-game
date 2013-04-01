package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controller.Santiago;

public class PanelConfiguration extends JPanel implements Observer {
	// Atributs
	public final static Font POLICE = new Font("Helvetica", Font.TYPE1_FONT, 60);
	private static final long serialVersionUID = 1L;
	private Santiago santiago;
	public Dimension homeDimension;
	private boolean isInit;

	public final static Color FG_COLOR = new Color(102, 204, 255);

	// Composants

	public PanelConfiguration() {
		super();
		santiago = null;
		isInit = false;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public void initComponent() {
		if (getParent() != null) {
			homeDimension = getParent().getSize();
			santiago = ((PanelHome) getParent()).getSantiago();

			PanelConfigJoueurs panelJoueurs = new PanelConfigJoueurs();

			// attribut du conteneur this (panelChoice
			setBackground(new Color(0, 0, 0, 0));
			setOpaque(false);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setSize(homeDimension.width, (homeDimension.height / 3) * 2);
			setPreferredSize(getSize());

			// on ajoute les composants au conteneur
			add(panelJoueurs);

			// attributs des composants
			panelJoueurs.initComponent();

			isInit = true;
		} else {
			System.out
					.println(getClass().toString()
							+ " Ajouter ce panneau a un conteneur avant de l'initialiser");
		}
	}

	public Santiago getSantiago() {
		return santiago;
	}

	public boolean isInit() {
		return isInit;
	}
}
