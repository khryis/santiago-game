package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

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
    }

    public PanelActionChoixCarte(Container parent, String name, String[] actions) {
        super(parent, name, actions);

    }

    @Override
    public void initComponent() {
        super.initComponent();

        cardChoice = new JPanel();
        cardChoice.setLayout(new BoxLayout(cardChoice, BoxLayout.Y_AXIS));

        positionChoisie();
        cardChoice.add(positionChoisiePanel);

        selectedCard();
        cardChoice.add(cardChoisiePanel);

        cardPanel = cardsObjects();
        cardChoice.add(cardPanel);

        add(cardChoice);
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }
}
