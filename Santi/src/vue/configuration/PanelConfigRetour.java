package vue.configuration;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;

import vue.AbstractPanel;
import vue.components.BgButton;

public class PanelConfigRetour extends AbstractPanel {
    private static final long serialVersionUID = 1L;

    private BgButton retourButton;
    PanelConfiguration panelConfiguration;

    public PanelConfigRetour(Container parent) {
        super(parent);
        panelConfiguration = (PanelConfiguration) parent;
    }

    @Override
    public void initComponent() {
        super.initComponent();
        // attribut du conteneur this (panelChoice
        setBorder(BorderFactory.createLineBorder(GREEN_BORDER));
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(BG_COLOR);
        setForeground(FG_COLOR);
        setPreferredSize(new Dimension(homeDimension.width, getPreferredSize().height));

        // création des composants
        retourButton = new BgButton("Retour");
        retourButton.addActionListener(new RetourListener());
        // on ajoute les composants au conteneur
        add(retourButton);

        // initialisation des composants
        retourButton.initComponent();

        isInit = true;
    }

    public void retour() {
        panelConfiguration.retourAuPanelChoice();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    private class RetourListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof BgButton) {
                retour();
            }
        }
    }
}
