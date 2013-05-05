package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.PositionSegment;
import vue.components.Bouton;

public class PanelActionChoixConstruction extends PanelAction {
    private static final long serialVersionUID = 1L;

    JPanel choixPanel = new JPanel();

    private final Bouton choisirProposition = new Bouton("Choisir Proposition");
    private final Bouton choisirAutre = new Bouton("Choisir une autre construction");

    public PanelActionChoixConstruction(Container parent) {
        super(parent);
    }

    // new String[] { "choisir proposition", "choisir une autre construction",
    // "passer son tour" }
    public PanelActionChoixConstruction(Container parent, String name) {
        super(parent, name);
        choisirProposition.setPreferredSize(new Dimension(300, 50));
        boutons.add(choisirProposition);
        choisirProposition.addActionListener(new ChoixProposition());

        choisirAutre.setPreferredSize(new Dimension(300, 50));
        boutons.add(choisirAutre);
        choisirAutre.addActionListener(new ChoixProposition());
    }

    public PanelActionChoixConstruction(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        choixPanel = new JPanel();
        choixPanel.setLayout(new BoxLayout(choixPanel, BoxLayout.Y_AXIS));

        // positionChoisie();
        // choixPanel.add(positionChoisiePanel);

        propositions();
        choixPanel.add(propositionsPanel);

        add(choixPanel);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (!santiago.getEnchereContructeur().isEmpty()) {
            removeAll();
            initComponent();
            validate();
        }
        if (!santiago.constructeurPeutEncherir() && santiago.getEnchereContructeur().isEmpty()) {
            santiago.setPhaseFinie(true);
        }
        if (!santiago.constructeurPeutEncherir()) {
            choisirAutre.setEnabled(false);
        } else {
            choisirAutre.setEnabled(true);
        }
        if (santiago.getEnchereContructeur().isEmpty()) {
            choisirProposition.setEnabled(false);
        } else {
            choisirProposition.setEnabled(true);
        }
    }

    private class ChoixProposition implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Bouton bouton = (Bouton) e.getSource();
            PositionSegment canal = santiago.getPositionSegmentCourant();
            if (bouton.getText().compareToIgnoreCase("Choisir Proposition") == 0) {
                if (canal != null) {
                    if (!santiago.placerCanalChoisi(canal)) {
                        JOptionPane.showMessageDialog(parent, "Pas possible de placer ce canal");
                    }
                }
            }
            if (bouton.getText().compareToIgnoreCase("Choisir une autre construction") == 0) {
                if (canal != null) {
                    if (!santiago.placerCanalDeSonChoix(canal)) {
                        JOptionPane.showMessageDialog(parent, "Pas possible");
                    }
                }
            }
        }
    }
}
