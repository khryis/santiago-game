package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;

import vue.components.Bouton;

public class PanelActionSecheresse extends PanelAction {
    private static final long serialVersionUID = 1L;

    private final Bouton suivant = new Bouton("Phase suivante");

    public PanelActionSecheresse(Container parent) {
        super(parent);
    }

    public PanelActionSecheresse(Container parent, String name) {
        super(parent, name);
        suivant.setPreferredSize(new Dimension(300, 50));
        boutons.add(suivant);
    }

    public PanelActionSecheresse(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        add(PanelAction.secheresseInfo());
    }

}
