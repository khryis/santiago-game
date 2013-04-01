package vue;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JButton;

import controller.PanelChoiceListener;

public class PanelChoice extends AbstractPanel {
	private static final long serialVersionUID = 1L;
	// Atributs
	public final static Font POLICE = new Font("Helvetica", Font.TYPE1_FONT, 60);

	// composants du JPanel
	private JButton boutonDemarrer;
	private JButton boutonConfiguration;
	private JButton boutonReglage;

	@Override
	public void initComponent() {
		// Initialisation des attributs/composants
		if (getParent() != null) {
			homeDimension = getParent().getSize();
			santiago = ((PanelHome) getParent()).getSantiago();

			boutonDemarrer = new BgButton("Demarrer", "transparent.png");
			boutonConfiguration = new BgButton("Configurer", "transparent.png");
			boutonReglage = new BgButton("Réglages", "transparent.png");

			// Listener
			boutonDemarrer
					.addMouseListener(new PanelChoiceListener(getParent()));
			boutonConfiguration.addMouseListener(new PanelChoiceListener(
					getParent()));
			boutonReglage
					.addMouseListener(new PanelChoiceListener(getParent()));

			// attribut du conteneur this (panelChoice
			setBackground(BG_TRANSPARENT);
			setOpaque(false);
			setLayout(new FlowLayout());
			setSize(homeDimension.width, homeDimension.height / 2);
			setPreferredSize(getSize());

			// on ajoute les composants au conteneur
			add(boutonDemarrer);
			add(boutonConfiguration);
			add(boutonReglage);

			boutonDemarrer.setEnabled(false);

			isInit = true;
		} else {
			System.out
					.println("Ajouter ce panneau a un conteneur avant de l'initialiser");
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO update panelChoice
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
