package vue.home;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import vue.AbstractPanel;
import vue.components.BgButton;
import controller.PanelChoiceListener;

public class PanelChoice extends AbstractPanel {
    private static final long serialVersionUID = 1L;

    // composants du JPanel
    JButton boutonDemarrer;
    JButton boutonConfiguration;
    JButton boutonReglage;

    @Override
    public void initComponent() {
        // Initialisation des attributs/composants
        super.initComponent();
        if (getParent() != null) {

            // init composants du panel
            boutonDemarrer = new BgButton("Demarrer", "transparent.png");
            boutonConfiguration = new BgButton("Configurer", "transparent.png");
            boutonReglage = new BgButton("Réglages", "transparent.png");

            // Listener
            boutonDemarrer.addMouseListener(new PanelChoiceListener(getParent()));
            boutonConfiguration.addMouseListener(new PanelChoiceListener(getParent()));
            boutonReglage.addMouseListener(new PanelChoiceListener(getParent()));

            // attribut du conteneur this panelChoice
            setBackground(BG_TRANSPARENT);
            setOpaque(false);
            setLayout(new FlowLayout());
            setSize(homeDimension.width, homeDimension.height / 2);
            setPreferredSize(getSize());
            setVisible(true);

            // on ajoute les composants au conteneur
            add(boutonDemarrer);
            add(boutonConfiguration);
            add(boutonReglage);
            validate();

            ((BgButton) boutonDemarrer).initComponent();
            ((BgButton) boutonConfiguration).initComponent();
            ((BgButton) boutonReglage).initComponent();
            boutonDemarrer.setEnabled(false);

            isInit = true;
        } else {
            System.out.println("Ajouter ce panneau a un conteneur avant de l'initialiser");
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO update panelChoice
    }
}
