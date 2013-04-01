package vue;

import javax.swing.JTextField;

public class BgTextField extends JTextField implements IConstante {
	private static final long serialVersionUID = 1L;

	public BgTextField() {
		super();
	}

	public BgTextField(String contenu) {
		super(contenu, 10);
	}

	public BgTextField(String contenu, int taille) {
		super(contenu, taille);
	}

	public void initComponent() {
		if (getParent() != null) {
			setSize(getParent().getPreferredSize());
		} else {
			setSize(getPreferredSize());
		}
		setFont(POLICE_15);
		setBackground(NICE_GREY);
		setForeground(FG_COLOR);
		setEditable(false);
		setEnabled(false);
	}

}
