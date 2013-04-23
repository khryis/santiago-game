package vue.home;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JPanel;

import vue.AbstractPanel;
import vue.components.BgButton;

public class PanelChoice extends AbstractPanel {
    private static final long serialVersionUID = 1L;

    // composants du JPanel
    BgButton boutonDemarrer;
    BgButton boutonConfiguration;
    BgButton boutonReglage;
    PanelHome panelHome;
    JPanel cards;

    public PanelChoice(Container parent) {
        super(parent);
        panelHome = (PanelHome) parent;
    }

    @Override
    public void initComponent() {
        // Initialisation des attributs/composants
        super.initComponent();

        // attribut du conteneur this panelChoice
        setBackground(BG_TRANSPARENT);
        setOpaque(false);
        setLayout(new BorderLayout());
        // setLayout(new FlowLayout());
        setPreferredSize(new Dimension(homeDimension.width, homeDimension.height / 2));

        // init composants du panel
        boutonDemarrer = new BgButton("Demarrer");
        boutonConfiguration = new BgButton("Configurer");
        boutonReglage = new BgButton("Réglages");
        JPanel intermediaire = new JPanel();
        intermediaire.setLayout(new FlowLayout());
        intermediaire.setPreferredSize(new Dimension(homeDimension.width, homeDimension.height / 2));
        intermediaire.setOpaque(false);

        // Listener
        boutonDemarrer.addActionListener(new BoutonDemarrerListener());
        boutonConfiguration.addActionListener(new BoutonConfigurationListener());
        boutonReglage.addActionListener(new BoutonReglageListener());

        // on ajoute les composants au conteneur
        intermediaire.add(boutonDemarrer);
        intermediaire.add(boutonConfiguration);
        intermediaire.add(boutonReglage);
        add(intermediaire, BorderLayout.SOUTH);

        boutonDemarrer.initComponent();
        boutonConfiguration.initComponent();
        boutonReglage.initComponent();

        boutonDemarrer.setEnabled(false);

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (santiago.isConfigured()) {
            boutonDemarrer.setEnabled(true);
        }
    }

    private class BoutonDemarrerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panelHome.cardLayout.show(panelHome.cards, panelHome.listContent[0]);
        }
    }

    private class BoutonConfigurationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panelHome.cardLayout.show(panelHome.cards, panelHome.listContent[1]);
        }
    }

    private class BoutonReglageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // cards.cardLayout.show(cards, cards.listContent[2]);
        }
    }
}
