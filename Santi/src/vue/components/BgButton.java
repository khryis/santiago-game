package vue.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import vue.IConstante;

public class BgButton extends JButton implements IConstante {
    private static final long serialVersionUID = 1L;
    private Image background;

    public BgButton(String name) {
        super(name);
    }

    public BgButton(String name, String pathImage) {
        super(name);
        background = new ImageIcon(pathImage).getImage();
    }

    public void initComponent() {
        setFont(POLICE_60);
        setFocusable(false);
        setRolloverEnabled(false);
    }

    public Image getBG() {
        return background;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(NICE_GREY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(FG_BUTTON);
        g.drawString(getText(), 0, 55);
    }
}
