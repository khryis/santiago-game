package vue.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
        setBorder(new RoundedBorder(FG_BUTTON));
    }

    public Image getBG() {
        return background;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(NICE_GREY);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (isEnabled()) {
            g.setColor(FG_BUTTON);
        } else {
            g.setColor(NICE_GREY_DISABLE);
        }
        g.drawString(getText(), 0, 60);
    }
}
