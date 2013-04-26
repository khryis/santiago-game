package vue.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import vue.IConstante;

public class Bouton extends JButton implements MouseListener {
    private static final long serialVersionUID = 1L;
    private Image img;
    private Image imgNormal;
    private Image imgHover;
    private Image imgAppuye;

    public Bouton(String str) {
        super(str);
        try {
            img = ImageIO.read(new File("img/fondNormal.png"));
            imgNormal = ImageIO.read(new File("img/fondNormal.png"));
            imgHover = ImageIO.read(new File("img/fondHover.png"));
            imgAppuye = ImageIO.read(new File("img/fondAppuye.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(100, getPreferredSize().height));
        addMouseListener(this);
    }

    public Bouton(String str, String pathNormal, String pathHover, String pathAppuye) {
        super(str);
        try {
            img = ImageIO.read(new File(pathNormal));
            imgNormal = ImageIO.read(new File(pathNormal));
            imgHover = ImageIO.read(new File(pathHover));
            imgAppuye = ImageIO.read(new File(pathAppuye));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(100, getPreferredSize().height));
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
        g2d.setPaint(gp);
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        g2d.setColor(IConstante.FG_BUTTON_FONCE);

        // Objet permettant de connaître les propriétés d'une police, dont la
        // taille
        FontMetrics fm = g2d.getFontMetrics();
        // Hauteur de la police d'écriture
        int height = fm.getHeight();
        // Largeur totale de la chaîne passée en paramètre
        int width = fm.stringWidth(getText());

        // On calcule alors la position du texte, et le tour est joué
        g2d.drawString(getText(), getWidth() / 2 - width / 2, getHeight() / 2 + height / 4);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Inutile d'utiliser cette méthode ici
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        img = imgHover;
    }

    @Override
    public void mouseExited(MouseEvent event) {
        img = imgNormal;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        img = imgAppuye;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (event.getY() > 0 && event.getY() < getHeight() && event.getX() > 0 && event.getX() < getWidth()) {
            img = imgHover;
        } else {
            img = imgNormal;
        }
    }
}