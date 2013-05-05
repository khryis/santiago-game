package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vue.components.Bouton;

public class PanelActionEnchere extends PanelAction {
    private static final long serialVersionUID = 1L;

    JPanel enchere = new JPanel();

    private final Bouton encherir = new Bouton("Encherir");
    private final Bouton passer = new Bouton("Passer son tour");

    public PanelActionEnchere(Container parent) {
        super(parent);
    }

    public PanelActionEnchere(Container parent, String name) {
        super(parent, name);
        encherir.setPreferredSize(new Dimension(300, 50));
        boutons.add(encherir);
        encherir.addActionListener(new EncherirListener());

        passer.setPreferredSize(new Dimension(300, 50));
        boutons.add(passer);
        passer.addActionListener(new EncherirListener());
    }

    public PanelActionEnchere(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        enchere = new JPanel();
        enchere.setLayout(new BoxLayout(enchere, BoxLayout.Y_AXIS));

        enchereObjects();
        enchere.add(betPanel);

        cardPanel = cardsObjects();
        enchere.add(cardPanel);

        add(enchere);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (santiago.isConfigured()) {
            removeAll();
            initComponent();
            validate();
        }
        enchere.repaint();
        encherir.setEnabled(true);
        encherir.setEnabled(true);
        repaint();
    }

    private class EncherirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (((Bouton) e.getSource()).getText().compareToIgnoreCase("Encherir") == 0) {
                if (!santiago.encherir(Integer.valueOf(betText.getText()))) {
                    JOptionPane.showMessageDialog(parent, "Déjà une enchère similaire !");
                }
            } else if (((Bouton) e.getSource()).getText().compareToIgnoreCase("Passer son tour") == 0) {
                if (!santiago.encherir(0)) {
                    JOptionPane.showMessageDialog(parent, "Problème la !!");
                }
            }
        }
    }
}
