package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vue.components.Bouton;

public class PanelActionEnchere extends PanelAction {
    private static final long serialVersionUID = 1L;

    public JPanel cardPanel;

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
    }

    public PanelActionEnchere(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        JPanel enchere = new JPanel();
        enchere.setLayout(new BoxLayout(enchere, BoxLayout.Y_AXIS));

        enchereObjects();
        enchere.add(betPanel);

        cardPanel = cardsObjects();
        enchere.add(cardPanel);

        add(enchere);
    }

    private class EncherirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }
}
