package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JOptionPane;

import model.PositionSegment;
import vue.components.Bouton;

public class PanelActionCanalSup extends PanelAction {
    private static final long serialVersionUID = 1L;

    private final Bouton valider = new Bouton("Poser un canal supplémentaire");
    private final Bouton passer = new Bouton("Passer son tour");

    public PanelActionCanalSup(Container parent) {
        super(parent);
    }

    // new String[] { "valider position", "passer son tour" }
    public PanelActionCanalSup(Container parent, String name) {
        super(parent, name);
        valider.setPreferredSize(new Dimension(300, 50));
        boutons.add(valider);
        valider.addActionListener(new PoserIrrigation());

        passer.setPreferredSize(new Dimension(300, 50));
        boutons.add(passer);
        passer.addActionListener(new PoserIrrigation());
    }

    public PanelActionCanalSup(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        // positionChoisie();

        add(positionChoisiePanel);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (!santiago.getEnchereContructeur().isEmpty()) {
            removeAll();
            initComponent();
            validate();
        }
        if (santiago.getPositionSegmentCourant() != null) {
            positionValeur.setText(santiago.getPositionSegmentCourant().toString());
        }
        if (santiago.joueurPlaying().hasTuyauSup()) {
            valider.setEnabled(true);
        }
    }

    private class PoserIrrigation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Bouton bouton = (Bouton) e.getSource();
            PositionSegment canal = santiago.getPositionSegmentCourant();
            if (bouton.getText().compareToIgnoreCase("Poser un canal supplémentaire") == 0) {
                if (canal != null) {
                    if (santiago.joueurPlaying().hasTuyauSup()) {
                        if (!santiago.irrigationSupplementaire(canal)) {
                            JOptionPane.showMessageDialog(parent, "Canal supplémentaire n'as pu être posé !!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(parent, "Vous n'avez plus de canaux supplémentaire a poser !!");
                        valider.setEnabled(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "Séléctionnez un segment");
                }
            } else if (bouton.getText().compareToIgnoreCase("Passer son tour") == 0) {
                santiago.incrementerJoueurCourantCanalSup(false);
            }
        }

    }
}
