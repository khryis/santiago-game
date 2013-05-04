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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import model.Carte;
import model.PositionCase;
import model.PositionIntersection;
import model.PositionSegment;
import vue.AbstractPanel;

@SuppressWarnings("serial")
public class PanelPlateau extends AbstractPanel {
    private Image background;
    HashMap<PositionIntersection, JButton> tabCorrespondanceSource;
    HashMap<PositionSegment, JButton> tabCorrespondanceSegment;
    HashMap<PositionCase, JButton> tabCorrespondanceCase;
    JButton[] tabSource;
    JButton[] tabSegments;
    JButton[] tabCases;

    private final JPanel panelConteneur = new JPanel();

    // TODO structure HashMap<Position, JButton> récupérer listes du modèle
    // plateau

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
        tabSource = new JButton[6];
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
        tabSegments = new JButton[31];
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
        tabCases = new JButton[48];
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

        // Création d'une table de correspondance Position->JButton
        tabCorrespondanceSource = new HashMap<>();
        tabCorrespondanceSegment = new HashMap<>();
        tabCorrespondanceCase = new HashMap<>();
        tabCorrespondanceSource.put(new PositionIntersection(2, 2, 0), tabSource[0]);
        tabCorrespondanceSource.put(new PositionIntersection(4, 2, 0), tabSource[1]);
        tabCorrespondanceSource.put(new PositionIntersection(6, 2, 0), tabSource[2]);
        tabCorrespondanceSource.put(new PositionIntersection(2, 4, 0), tabSource[3]);
        tabCorrespondanceSource.put(new PositionIntersection(4, 4, 0), tabSource[4]);
        tabCorrespondanceSource.put(new PositionIntersection(6, 4, 0), tabSource[5]);
        int x1 = 0;
        int y1 = 0;
        int x2 = 2;
        int y2 = 0;
        for (int i = 0; i < 31; i++) {
            if (i < 16) {
                if (i % 4 == 0 && i != 0) {
                    x1 = 0;
                    x2 = 2;
                    y1 += 2;
                    y2 += 2;
                } else {
                    if (i != 0) {
                        x1 += 2;
                        x2 += 2;
                    }
                }
                int indexPosition = santiago.getPlateau().getCanaux().indexOf(new PositionSegment(x1, y1, x2, y2));
                PositionSegment ps = santiago.getPlateau().getCanaux().get(indexPosition);
                tabCorrespondanceSegment.put(ps, tabSegments[i]);
                // tabCorrespondanceSegment.put(new PositionSegment(x1, y1, x2,
                // y2, true), tabSegments[i]);
                // System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 +
                // " ");
            } else {
                if (i == 16) {
                    x1 = 0;
                    y1 = 0;
                    x2 = 0;
                    y2 = 2;
                }
                if (i == 21 || i == 26) {
                    x1 = 0;
                    x2 = 0;
                    y1 += 2;
                    y2 += 2;
                } else {
                    if (i != 16) {
                        x1 += 2;
                        x2 += 2;
                    }
                }
                int indexPosition = santiago.getPlateau().getCanaux().indexOf(new PositionSegment(x1, y1, x2, y2));
                PositionSegment ps = santiago.getPlateau().getCanaux().get(indexPosition);
                tabCorrespondanceSegment.put(ps, tabSegments[i]);
                // tabCorrespondanceSegment.put(new PositionSegment(x1, y1, x2,
                // y2, true), tabSegments[i]);
                // System.out.println("blabla " + x1 + " " + y1 + " " + x2 + " "
                // + y2 + " ");
            }
        }

        x1 = 0;
        y1 = 0;
        for (int i = 0; i < 48; i++) {
            int indexPosition = santiago.getPlateau().getCases().indexOf(new PositionCase(x1, y1));
            PositionCase pc = santiago.getPlateau().getCases().get(indexPosition);
            tabCorrespondanceCase.put(pc, tabCases[i]);
            x1 += 1;
            if (x1 == 8) {
                x1 = 0;
                y1 += 1;
            }
            // System.out.println("blablablabla " + x1 + " " + y1);
        }

        // On gère maintenant le clic sur un bouton en renvoyant la position au
        // PanelPartie
        Set<PositionSegment> parcoursTab = tabCorrespondanceSegment.keySet();
        Set<PositionCase> parcoursTab2 = tabCorrespondanceCase.keySet();
        for (final PositionSegment p : parcoursTab) {
            tabCorrespondanceSegment.get(p).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    santiago.setPositionSegmentCourant(p);
                    // JOptionPane.showMessageDialog(panelConteneur,
                    // p.toString());
                }
            });
        }
        for (final PositionCase p1 : parcoursTab2) {
            tabCorrespondanceCase.get(p1).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    santiago.setPositionCaseCourante(p1);
                    // JOptionPane.showMessageDialog(panelConteneur,
                    // p1.toString()); // A
                    // Commenter
                    // pour
                    // enlever
                    // le
                    // popup
                }
            });
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            background = ImageIO.read(new File("img/plateau.jpg"));
            // Pour l'image de fond
            g.drawImage(background, panelConteneur.getLocation().x, panelConteneur.getLocation().y, panelConteneur.getWidth(), panelConteneur.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        clearPlateau();
        if (santiago.getPositionSegmentCourant() != null) {
            JButton segmentSelected = tabCorrespondanceSegment.get(santiago.getPositionSegmentCourant());
            // segmentSelected.setContentAreaFilled(true);
            segmentSelected.setBorderPainted(true);
            segmentSelected.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
        if (santiago.getPositionCaseCourante() != null) {
            JButton caseSelected = tabCorrespondanceCase.get(santiago.getPositionCaseCourante());
            // caseSelected.setContentAreaFilled(true);
            caseSelected.setBorderPainted(true);
            caseSelected.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }

        tabCorrespondanceSource.get(santiago.getPlateau().getSource()).setOpaque(true);
        tabCorrespondanceSource.get(santiago.getPlateau().getSource()).setContentAreaFilled(true);
        tabCorrespondanceSource.get(santiago.getPlateau().getSource()).setBorderPainted(true);
        tabCorrespondanceSource.get(santiago.getPlateau().getSource()).setBackground(Color.blue);
        ArrayList<Carte> listeCartesPosees = this.santiago.getPlateau().getCartesPosees();
        ArrayList<PositionSegment> listeSegments = this.santiago.getPlateau().getCanaux();
        for (int i = 0; i < listeSegments.size(); i++) {
            PositionSegment ps = listeSegments.get(i);
            if (ps.isOccupe()) {
                tabCorrespondanceSegment.get(ps).setContentAreaFilled(true);
                tabCorrespondanceSegment.get(ps).setBorderPainted(false);
                tabCorrespondanceSegment.get(ps).setBackground(Color.blue);
            }
        }
        for (int i = 0; i < listeCartesPosees.size(); i++) {
            Carte carte = listeCartesPosees.get(i);
            if (carte.getPosition().isOccupe()) {
                Image originalImage = getToolkit().getImage(PanelPartie.getPathImage(carte));
                Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_AREA_AVERAGING);
                Icon tmp = new ImageIcon(scaledImage);
                JButton b = tabCorrespondanceCase.get(carte.getPositionCase());
                if (b != null) {
                    b.setOpaque(true);
                    b.setContentAreaFilled(true);
                    b.setBorderPainted(false);
                    b.setIcon(tmp);
                }
            }
        }
    }

    public void clearPlateau() {
        for (JButton b : tabCorrespondanceCase.values()) {
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
        }
        for (JButton b : tabCorrespondanceSegment.values()) {
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
        }
    }
}
