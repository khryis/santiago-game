package vue;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;

import controller.ConfigurationListener;

public class PanelConfigRetour extends AbstractPanel {
	private static final long serialVersionUID = 1L;

	private BgButton retourButton;

	@Override
	public void initComponent() {
		super.initComponent();
		if (getParent() != null) {
			// création des composants
			retourButton = new BgButton("Retour");

			// Listener
			retourButton.addMouseListener(new ConfigurationListener(this));

			// attribut du conteneur this (panelChoice
			setBorder(BorderFactory.createLineBorder(GREEN_BORDER));
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			setSize(homeDimension.width, getPreferredSize().height);
			setBackground(BG_COLOR);
			setForeground(FG_COLOR);
			setPreferredSize(getSize());

			// on ajoute les composants au conteneur
			add(retourButton);

			// initialisation des composantsé

			isInit = true;
		} else {
			System.out
					.println(getClass().toString()
							+ " Ajouter ce panneau a un conteneur avant de l'initialiser");
		}
	}

	public void retour() {
		((PanelHome) getParent().getParent()).retourPanelConfig();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
