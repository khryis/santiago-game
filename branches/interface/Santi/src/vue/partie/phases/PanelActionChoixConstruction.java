package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vue.components.Bouton;

public class PanelActionChoixConstruction extends PanelAction {
    private static final long serialVersionUID = 1L;

    private final Bouton choisirProposition = new Bouton("Choisir Proposition");
    private final Bouton choisirAutre = new Bouton("Choisir une autre construction");
    private final Bouton passer = new Bouton("Passer son tour");

    public PanelActionChoixConstruction(Container parent) {
        super(parent);
    }

    // new String[] { "choisir proposition", "choisir une autre construction",
    // "passer son tour" }
    public PanelActionChoixConstruction(Container parent, String name) {
        super(parent, name);
        choisirProposition.setPreferredSize(new Dimension(300, 50));
        boutons.add(choisirProposition);
        choisirAutre.setPreferredSize(new Dimension(300, 50));
        boutons.add(choisirAutre);
        passer.setPreferredSize(new Dimension(300, 50));
        boutons.add(passer);
    }

    public PanelActionChoixConstruction(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        JPanel choixPanel = new JPanel();
        choixPanel.setLayout(new BoxLayout(choixPanel, BoxLayout.Y_AXIS));

        positionChoisie();
        choixPanel.add(positionChoisiePanel);

        JPanel propositionsPanel = propositions();
        choixPanel.add(propositionsPanel);

        add(choixPanel);
    }
}
