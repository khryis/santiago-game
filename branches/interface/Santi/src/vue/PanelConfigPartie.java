package vue;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

public class PanelConfigPartie extends AbstractPanel {
	private static final long serialVersionUID = 1L;

	private ButtonGroup groupNiveau;
	BgRadioButton radioB1;
	BgRadioButton radioB2;
	BgRadioButton radioB3;

	@Override
	public void initComponent() {
		super.initComponent();
		if (getParent() != null) {
			// création des composants
			groupNiveau = new ButtonGroup();
			radioB1 = new BgRadioButton("Sans Palmier", false);
			radioB2 = new BgRadioButton("Avec Palmier", false);
			radioB3 = new BgRadioButton("Avec Palmier, Source sur les bords",
					false);
			groupNiveau.add(radioB1);
			groupNiveau.add(radioB2);
			groupNiveau.add(radioB3);

			// Listener

			// attribut du conteneur this (panelChoice
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(GREEN_BORDER),
					"Niveau de la partie", TitledBorder.CENTER,
					TitledBorder.TOP, POLICE_30, FG_COLOR));
			setLayout(new FlowLayout());
			setSize(homeDimension.width, getPreferredSize().height);
			setBackground(BG_COLOR);
			setForeground(FG_COLOR);
			setPreferredSize(getSize());

			// attributs des composants du conteneur

			// on ajoute les composants au conteneur
			add(radioB1);
			add(radioB2);
			add(radioB3);

			// initialisation des composantsé
			radioB1.initComponent();
			radioB2.initComponent();
			radioB3.initComponent();

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
