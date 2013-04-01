package vue;

import java.util.Observable;

import javax.swing.BoxLayout;

import controller.Santiago;

public class PanelConfiguration extends AbstractPanel {
	// Atributs

	private static final long serialVersionUID = 1L;

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

	@Override
	public void initComponent() {
		if (getParent() != null) {
			homeDimension = getParent().getSize();
			santiago = ((PanelHome) getParent()).getSantiago();

			PanelConfigJoueurs panelJoueurs = new PanelConfigJoueurs();

			// attribut du conteneur this (panelChoice
			setBackground(BG_TRANSPARENT);
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

	@Override
	public Santiago getSantiago() {
		return santiago;
	}

	public boolean isInit() {
		return isInit;
	}
}
