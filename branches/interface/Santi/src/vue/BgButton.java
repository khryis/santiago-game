package vue;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BgButton extends JButton implements IConstante {
	private static final long serialVersionUID = 1L;
	private Image background;

	public BgButton(String name) {
		super(name);
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
		setFont(POLICE_60);
		setFocusable(false);
		setRolloverEnabled(false);
		setBackground(NICE_GREY);
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
