package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class PanelHome extends AbstractPanel {
	private static final long serialVersionUID = 1L;
	// Attributs de classe
	private Image background;

	// Composant de la vue
	private PanelChoice panelChoice;
	private PanelConfiguration panelConfiguration;

	@Override
	public void initComponent() {
		// init des composants
		homeDimension = getParent().getSize();
		background = new ImageIcon("img/champsDessin.jpg").getImage();
		panelChoice = new PanelChoice();
		panelConfiguration = new PanelConfiguration();

		// Listener

		// attribut du conteneur PanelHome
		setLayout(new BorderLayout());
		setSize(homeDimension);

		// on ajoute les composant au conteneur
		add(panelChoice, BorderLayout.SOUTH);
		// add(panelConfiguration, BorderLayout.SOUTH);

		panelChoice.initComponent();

		panelChoice.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		panelConfiguration.setBorder(BorderFactory
				.createLineBorder(Color.GREEN));
	}

	public void configurer() {
		remove(panelChoice);
		revalidate();
		repaint();
		add(panelConfiguration, BorderLayout.SOUTH);
		if (!panelConfiguration.isInit()) {
			panelConfiguration.initComponent();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}
