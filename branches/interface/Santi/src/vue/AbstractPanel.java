package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Santiago;

public abstract class AbstractPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	// Attribut de classe
	public final static Font POLICE_30 = new Font("Helvetica", Font.TYPE1_FONT,
			30);
	public final static Font POLICE_60 = new Font("Helvetica", Font.TYPE1_FONT,
			60);
	public final static Color BG_TRANSPARENT = new Color(0, 0, 0, 0);
	public final static Color FG_COLOR = new Color(102, 204, 255);
	public final static Color BG_COLOR = new Color(204, 255, 255, 50);

	// Attributs récurrent d'une instance Panel
	protected Santiago santiago;
	protected Dimension homeDimension;
	protected boolean isInit;

	// Contructeur général
	public AbstractPanel() {
		super();
		santiago = null;
		homeDimension = null;
		isInit = false;
	}

	public Santiago getSantiago() {
		return santiago;
	}

	public Dimension getDimension() {
		return homeDimension;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	// Méthode dans laquelle on ajoute tout les composants voulu au JPanel
	public abstract void initComponent();

	// Méthode qui est lancé lorsque le model effectue un Notify()
	@Override
	public abstract void update(Observable arg0, Object arg1);

}
