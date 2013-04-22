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

    public Bouton(String str) {
        super(str);
        try {
            img = ImageIO.read(new File("img/fondNormal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(100, getPreferredSize().height));
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
        g2d.setPaint(gp);
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        g2d.setColor(IConstante.FG_BUTTON_FONCE);

        // Objet permettant de connaître les propriétés d'une police, dont la
        // taille
        FontMetrics fm = g2d.getFontMetrics();
        // Hauteur de la police d'écriture
        int height = fm.getHeight();
        // Largeur totale de la chaîne passée en paramètre
        int width = fm.stringWidth(getText());

        // On calcule alors la position du texte, et le tour est joué
        g2d.drawString(getText(), this.getWidth() / 2 - (width / 2), (this.getHeight() / 2) + (height / 4));
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Inutile d'utiliser cette méthode ici
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Nous changeons le fond de notre image pour le jaune lors du survol,
        // avec le fichier fondBoutonHover.png
        try {
            img = ImageIO.read(new File("img/fondHover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Nous changeons le fond de notre image pour le vert lorsque nous
        // quittons le bouton, avec le fichier fondBouton.png
        try {
            img = ImageIO.read(new File("img/fondNormal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Nous changeons le fond de notre image pour le jaune lors du clic
        // gauche, avec le fichier fondBoutonClic.png
        try {
            img = ImageIO.read(new File("img/fondAppuye.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Nous changeons le fond de notre image pour l'orange lorsque nous
        // relâchons le clic avec le fichier fondBoutonHover.png si la souris
        // est toujours sur le bouton
        if ((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0 && event.getX() < this.getWidth())) {
            try {
                img = ImageIO.read(new File("img/fondHover.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Si on se trouve à l'extérieur, on dessine le fond par défaut
        else {
            try {
                img = ImageIO.read(new File("img/fondNormal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}