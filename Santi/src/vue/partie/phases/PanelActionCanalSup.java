package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;

import vue.components.Bouton;

public class PanelActionCanalSup extends PanelAction {
    private static final long serialVersionUID = 1L;

    private final Bouton valider = new Bouton("Valider position");
    private final Bouton passer = new Bouton("Passer son tour");

    public PanelActionCanalSup(Container parent) {
        super(parent);
    }

    // new String[] { "valider position", "passer son tour" }
    public PanelActionCanalSup(Container parent, String name) {
        super(parent, name);
        valider.setPreferredSize(new Dimension(300, 50));
        boutons.add(valider);
        passer.setPreferredSize(new Dimension(300, 50));
        boutons.add(passer);
    }

    public PanelActionCanalSup(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        positionChoisie();

        add(positionChoisiePanel);
    }
}
