package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vue.components.Bouton;

public class PanelActionSoudoiement extends PanelAction {
    private static final long serialVersionUID = 1L;
    private final Bouton soudoyer = new Bouton("Soudoyer");
    private final Bouton soutenir = new Bouton("Soutenir soudoyeur");
    private final Bouton passer = new Bouton("Passer");

    public PanelActionSoudoiement(Container parent) {
        super(parent);
    }

    public PanelActionSoudoiement(Container parent, String name) {
        super(parent, name);
        soudoyer.setPreferredSize(new Dimension(300, 50));
        boutons.add(soudoyer);

        soutenir.setPreferredSize(new Dimension(300, 50));
        boutons.add(soutenir);

        passer.setPreferredSize(new Dimension(300, 50));
        boutons.add(passer);
    }

    public PanelActionSoudoiement(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        JPanel soudoiementPanel = new JPanel();
        soudoiementPanel.setLayout(new BoxLayout(soudoiementPanel, BoxLayout.Y_AXIS));

        enchereObjects();
        soudoiementPanel.add(betPanel);

        positionChoisie();
        soudoiementPanel.add(positionChoisiePanel);

        JPanel propositionsPanel = propositions();
        soudoiementPanel.add(propositionsPanel);

        add(soudoiementPanel);
    }
}
