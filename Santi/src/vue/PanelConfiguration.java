package vue;

import java.util.Observable;

import javax.swing.BoxLayout;

import model.Santiago;

public class PanelConfiguration extends AbstractPanel {
	// Atributs
	private static final long serialVersionUID = 1L;

	// Composants
	PanelConfigJoueurs panelJoueurs;
	PanelConfigPartie panelPartie;
	PanelConfigRetour panelRetour;

	@Override
	public void initComponent() {
		super.initComponent();
		if (getParent() != null) {

			// init des composants du panneaux
			panelJoueurs = new PanelConfigJoueurs();
			panelPartie = new PanelConfigPartie();
			panelRetour = new PanelConfigRetour();

			// attribut du conteneur this (panelChoice
			setBackground(BG_TRANSPARENT);
			setOpaque(false);
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			setSize(homeDimension.width, (homeDimension.height / 5) * 3);
			setPreferredSize(getSize());

			// on ajoute les composants au conteneur
			add(panelJoueurs);
			add(panelPartie);
			add(panelRetour);

			// attributs des composants
			panelJoueurs.initComponent();
			panelPartie.initComponent();
			panelRetour.initComponent();

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

	@Override
	public Santiago getSantiago() {
		return santiago;
	}
}
