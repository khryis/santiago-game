package vue.components;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComboBox;

import vue.IConstante;

public class BgComboBox extends JComboBox<String> implements IConstante {
    private static final long serialVersionUID = 1L;
    private final static String[] TABJOUEURS = new String[] { "3", "4", "5" };

    public BgComboBox() {
        super(TABJOUEURS);
        initComponent();
    }

    public BgComboBox(String[] tabValues) {
        super(tabValues);
        initComponent();
    }

    public void initComponent() {
        setFocusable(false);
        setFont(POLICE_15);
        setBackground(NICE_GREY);
        setForeground(FG_COLOR);
        setPreferredSize(new Dimension(100, getPreferredSize().height));
    }

    public void changeList(String[] tabValues) {
        setModel(new BgComboBox(tabValues).getModel());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
