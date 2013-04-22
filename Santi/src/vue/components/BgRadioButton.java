package vue.components;

import java.awt.Graphics;

import javax.swing.JRadioButton;

import vue.IConstante;

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
    }

    public void initComponent() {
        // if (getParent() != null) {
        // setSize(getParent().getPreferredSize());
        // } else {
        // setSize(getPreferredSize());
        // }
        setFont(POLICE_15);
        setFocusable(false);
        setRolloverEnabled(false);
        setBackground(NICE_GREY);
        setForeground(FG_COLOR);
        setEnabled(true);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
