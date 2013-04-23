package vue.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.AbstractBorder;

class RoundedBorder extends AbstractBorder {
    private static final long serialVersionUID = 1L;
    private Color color;

    public RoundedBorder(Color color) {
        super();
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        int arc = 20;
        g2.drawRoundRect(x, y, width - 1, height - 1, arc, arc);
    }
}