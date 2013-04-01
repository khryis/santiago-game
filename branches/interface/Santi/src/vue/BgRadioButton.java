package vue;

import javax.swing.JRadioButton;

public class BgRadioButton extends JRadioButton implements IConstante {
	private static final long serialVersionUID = 1L;

	public BgRadioButton() {
		super();
	}

	public BgRadioButton(String text) {
		super(text);
	}

	public BgRadioButton(String text, boolean selected) {
		super(text, selected);
		// TODO Auto-generated constructor stub
	}

	public void initComponent() {
		if (getParent() != null) {
			setSize(getParent().getPreferredSize());
		} else {
			setSize(getPreferredSize());
		}
		setFont(POLICE_15);
		setFocusable(false);
		setBackground(BG_TRANSPARENT);
		setForeground(FG_COLOR);
		setEnabled(true);
	}

}
