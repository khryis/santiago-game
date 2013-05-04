package vue.components;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Carte;
import vue.partie.PanelPartie;

public class CardButton extends JButton {
    private static final long serialVersionUID = 1L;
    private final Carte carte;

    public CardButton(Carte carte) {
        this.carte = carte;
        // Image originalImage =
        // getToolkit().getImage(PanelPartie.getPathImage(carte));
        // Image scaledImage = originalImage.getScaledInstance(80, 80,
        // Image.SCALE_AREA_AVERAGING);
        //
        Icon tmp = new ImageIcon(PanelPartie.getPathImage(carte));
        setIcon(tmp);
        setMaximumSize(new Dimension(getIcon().getIconWidth(), getIcon().getIconHeight()));
        setMinimumSize(new Dimension(getIcon().getIconWidth(), getIcon().getIconHeight()));
        setRolloverEnabled(false);
    }

    public Carte getCarte() {
        return carte;
    }
}
