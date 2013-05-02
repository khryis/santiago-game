package vue.partie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;

import model.Position;

import vue.AbstractPanel;

@SuppressWarnings("serial")
public class PanelPlateau extends AbstractPanel{
	private Image background;
	HashMap<Position, JButton> tabCorrespondance;
	JButton[] tabSource;
	JButton[] tabSegments;
	JButton[] tabCases;
	
	private final JPanel panelConteneur = new JPanel();
	//TODO structure HashMap<Position, JButton> récupérer listes du modèle plateau

    public PanelPlateau(Container parent) {
        super(parent);
        add(panelConteneur);
    }

    @Override
    public void initComponent() {
        super.initComponent();
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        ((FlowLayout) getLayout()).setVgap(0);
        ((FlowLayout) getLayout()).setHgap(0);

        panelConteneur.setPreferredSize(new Dimension(800, 600));
        panelConteneur.setMaximumSize(new Dimension(800, 600));
        panelConteneur.setMinimumSize(new Dimension(800, 600));
        panelConteneur.setOpaque(false);
        panelConteneur.setLayout(null);
        int posX;
        int posY;

        // Création de la source
        JButton[] tabSource = new JButton[6];
        for (int i = 0; i < 6; i++) {
            final JButton jb = new JButton();
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false); // A DECOMMENTER POUR LAPERO
            tabSource[i] = jb;
            panelConteneur.add(jb);
        }
        posX = 206;
        posY = 198;
        for (int i = 0; i < 6; i++) {
            if (i % 3 == 0 && i != 0) {
                posX = 208;
                posY += 188;
            } else if (i != 0)
                posX += 182;
            tabSource[i].setBounds(posX, posY, 20, 20);
        }
        // Création des segments
        JButton[] tabSegments = new JButton[31];
        for (int i = 0; i < 31; i++) {
            JButton jb = new JButton();
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false); // A DECOMMENTER POUR LAPERO
            tabSegments[i] = jb;
            panelConteneur.add(jb);
        }

        posX = 30;
        posY = 23;
        // Positionnement des segements
        // Segments horizontaux de 0 à 15 (total de 16)
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0 && i != 0) {
                posX = 30;
                posY += 184;
            } else if (i != 0)
                posX += 183;
            tabSegments[i].setBounds(posX, posY, 183, 12);
            panelConteneur.add(tabSegments[i]);
        }
        posX = 30;
        posY = 31;
        // Segements verticaux de 16 à 30 (total de 15)
        for (int i = 16; i < 31; i++) {
            if (i == 21 || i == 26) {
                posX = 30;
                posY += 186;
            } else if (i != 16)
                posX += 181;
            tabSegments[i].setBounds(posX, posY, 10, 186);
            panelConteneur.add(tabSegments[i]);
        }

        // Création des cases
        JButton[] tabCases = new JButton[48];
        for (int i = 0; i < 48; i++) {
            JButton jb = new JButton();
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false); // A DECOMMENTER POUR LAPERO
            tabCases[i] = jb;
            panelConteneur.add(jb);
        }
        posX = 38;
        posY = 31;
        for (int i = 0; i < 48; i++) {
            if (i % 2 == 0 && i != 0 && i % 8 != 0 && i % 16 != 0) {
                posX += 97;
            } else {
                if (i % 8 == 0 && i != 0 && i % 16 != 0) {
                    posX = 38;
                    posY += 87;
                } else {
                    if (i % 16 == 0 && i != 0) {
                        posX = 38;
                        posY += 100;
                    } else if (i != 0)
                        posX += 86;
                }
            }
            tabCases[i].setBounds(posX, posY, 86, 86);
        }

		//Création d'une table de correspondance Position->JButton
		tabCorrespondance = new HashMap<Position, JButton>();
		for(int i=0; i<85; i++){
			
		}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            background = ImageIO.read(new File("img/plateau.jpg"));
            // Pour l'image de fond
            g.drawImage(background, panelConteneur.getLocation().x, panelConteneur.getLocation().y, panelConteneur.getWidth(),
                    panelConteneur.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        PanelPlateau pp = new PanelPlateau(frame);
        frame.setContentPane(pp);
        pp.initComponent();

        frame.pack();
        frame.setVisible(true);
    }
}
