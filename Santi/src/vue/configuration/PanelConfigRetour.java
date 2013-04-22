package vue.configuration;

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

    @Override
    public void initComponent() {
        super.initComponent();
        if (getParent() != null) {
            // création des composants
            retourButton = new BgButton("Retour");

            // Listener
            retourButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof BgButton) {
                        BgButton b = (BgButton) e.getSource();
                        if (b.getText().compareTo("Retour") == 0) {
                            retour();
                        }
                    }
                }
            });

            // attribut du conteneur this (panelChoice
            setBorder(BorderFactory.createLineBorder(GREEN_BORDER));
            setLayout(new FlowLayout(FlowLayout.RIGHT));
            setSize(homeDimension.width, getPreferredSize().height);
            setBackground(BG_COLOR);
            setForeground(FG_COLOR);
            setPreferredSize(getSize());
            setVisible(true);

            // on ajoute les composants au conteneur
            add(retourButton);
            validate();

            // initialisation des composantsé
            retourButton.initComponent();

            isInit = true;
        } else {
            System.out.println(getClass().toString() + " Ajouter ce panneau a un conteneur avant de l'initialiser");
        }
    }

    public void retour() {
        ((PanelConfiguration) getParent()).retourPanelConfig();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
