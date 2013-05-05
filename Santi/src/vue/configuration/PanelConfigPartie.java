package vue.configuration;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

import model.NiveauPartie;
import vue.AbstractPanel;
import vue.components.BgRadioButton;

public class PanelConfigPartie extends AbstractPanel {
    private static final long serialVersionUID = 1L;

    private ButtonGroup groupNiveau;
    private BgRadioButton radioB1;
    private BgRadioButton radioB2;
    private BgRadioButton radioB3;
    PanelConfiguration panelConfiguration;

    public PanelConfigPartie(Container parent) {
        super(parent);
        panelConfiguration = (PanelConfiguration) parent;
    }

    @Override
    public void initComponent() {
        super.initComponent();
        // attribut du conteneur this (panelChoice
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN_BORDER), "Niveau de la partie", TitledBorder.CENTER, TitledBorder.TOP, POLICE_30, FG_COLOR));
        setLayout(new FlowLayout());
        setBackground(BG_COLOR);
        setForeground(FG_COLOR);
        setPreferredSize(new Dimension(homeDimension.width, getPreferredSize().height));

        // création des composants
        groupNiveau = new ButtonGroup();
        radioB1 = new BgRadioButton("Sans Palmier", false);
        radioB2 = new BgRadioButton("Avec Palmier", false);
        radioB3 = new BgRadioButton("Avec Palmier, Source sur les bords", false);
        groupNiveau.add(radioB1);
        groupNiveau.add(radioB2);
        groupNiveau.add(radioB3);
        // Listener

        // on ajoute les composants au conteneur
        add(radioB1);
        add(radioB2);
        add(radioB3);
        // initialisation des composantsé
        radioB1.initComponent();
        radioB2.initComponent();
        radioB3.initComponent();

        radioB2.setEnabled(false);
        radioB3.setEnabled(false);

        isInit = true;
    }

    public NiveauPartie getNiveauPartie() {
        if (radioB1.isSelected()) {
            return NiveauPartie.FACILE;
        } else if (radioB2.isSelected()) {
            return NiveauPartie.MOYEN;
        } else if (radioB3.isSelected()) {
            return NiveauPartie.DIFFICILE;
        }
        return null;
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }

}
