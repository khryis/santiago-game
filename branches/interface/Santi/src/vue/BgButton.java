package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BgButton extends JButton {
	private static final long serialVersionUID = 1L;
	private final Image background;
	public final static Font POLICE = new Font("Helvetica", Font.TYPE1_FONT, 60);

	public final static Color BG_BUTTON = new Color(204, 255, 255, 50);
	public final static Color FG_BUTTON = new Color(102, 204, 255);

	public BgButton(String pathImage) {
		super();
		background = new ImageIcon(pathImage).getImage();
		initComponent();
	}

	public BgButton(String name, String pathImage) {
		super(name);
		background = new ImageIcon(pathImage).getImage();
		initComponent();
	}

	public void initComponent() {
		if (getParent() != null) {
			setSize(getParent().getPreferredSize());
		} else {
			setSize(getPreferredSize());
		}
		setFont(POLICE);
		setFocusable(false);
		setRolloverEnabled(false);
		setBackground(BG_BUTTON);
		setForeground(FG_BUTTON);
	}

	public Image getBG() {
		return background;
	}

	/*
	 * @Override public void paintComponent(Graphics g) {
	 * super.paintComponents(g); // g.drawImage(background, 0, 0,
	 * getPreferredSize().width, // getPreferredSize().height, null);
	 * g.drawString(getText(), 0, 55); setBackground(BG_BUTTON); }
	 */
}
