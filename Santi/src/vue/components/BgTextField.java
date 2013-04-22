package vue.components;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JTextField;

import vue.IConstante;

public class BgTextField extends JTextField implements IConstante {
    private static final long serialVersionUID = 1L;

    public BgTextField() {
        super();
    }

    public BgTextField(String contenu) {
        super(contenu, 10);
        initComponent();
    }

    public BgTextField(String contenu, int taille) {
        super(contenu, taille);
        initComponent();
    }

    public void initComponent() {
        setPreferredSize(new Dimension(100, getPreferredSize().height));
        setBackground(NICE_GREY);
        setForeground(FG_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(NICE_GREY_TRANSPARENT);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(FG_BUTTON);
        g.drawString(getText(), 0, 0);
    }
}
