package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.PositionCase;
import vue.components.Bouton;

public class PanelActionChoixCarte extends PanelAction {
    private static final long serialVersionUID = 1L;

    public JPanel cardChoice;
    public JPanel selectedPanel;
    public JPanel cardPanel;

    private final Bouton valider = new Bouton("Valider");

    public PanelActionChoixCarte(Container parent) {
        super(parent);
    }

    public PanelActionChoixCarte(Container parent, String name) {
        super(parent, name);
        valider.setPreferredSize(new Dimension(300, 50));
        boutons.add(valider);
        valider.addActionListener(new ValiderCarteChoisieListener());
    }

    public PanelActionChoixCarte(Container parent, String name, String[] actions) {
        super(parent, name, actions);

    }

    @Override
    public void initComponent() {
        super.initComponent();

        // Graphics g = getGraphics();
        //
        // if (g != null) {
        // g.setColor(this.getBackground());
        // g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // }

        cardChoice = new JPanel();
        cardChoice.setLayout(new BoxLayout(cardChoice, BoxLayout.Y_AXIS));

        // positionChoisie();
        // cardChoice.add(positionChoisiePanel);

        // selectedCard();
        // cardChoice.add(cardChoisiePanel);

        cardPanel = cardsObjects();
        cardChoice.add(cardPanel);

        add(cardChoice);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (!santiago.getPlateau().getCartesDevoilees().isEmpty()) {
            removeAll();
            initComponent();
            validate();
        }
        repaint();

    }

    private class ValiderCarteChoisieListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = santiago.getPlateau().getCartesDevoilees().indexOf(santiago.getCarteChoisie());
            if (index != -1) {
                PositionCase positionCase = santiago.getPositionCaseCourante();
                if (positionCase != null) {
                    if (!santiago.poserUneCarte(index, positionCase.getX(), positionCase.getY())) {
                        JOptionPane.showMessageDialog(parent, "Carte non posée !!");
                    } else {
                        // cardPanel.removeAll();
                        // cardPanel.validate();
                        removeAll();

                        initComponent();

                        validate();

                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "Position de case non séléctionné");
                }
            } else {
                JOptionPane.showMessageDialog(parent, "Carte non séléctionné");
            }
        }
    }
}
