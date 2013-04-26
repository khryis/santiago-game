package vue.partie;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import vue.AbstractPanel;

public class PanelPartieAction extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    private PanelPartie panelPartie;

    private PanelAction phaseEnchere;
    private PanelAction phaseChoixCarte;
    private PanelAction phaseSoudoiement;
    private PanelAction phaseChoixConstruction;
    private PanelAction phaseCanalSup;
    private PanelAction phaseSecheresse;
    private PanelAction phaseDiaDePaga;

    public JPanel phases = new JPanel();
    public CardLayout cardLayout = new CardLayout();
    public String[] listPhases = { "ENCHERE", "CHOIX_CARTE", "SOUDOIEMENT", "CHOIX_CONSTRUCTION", "CANAL_SUP", "SECHERESSE", "DIA_DE_PAGA",
            "HOME" };
    int indice = 0;

    public PanelPartieAction(Container parent) {
        super(parent);
        panelPartie = (PanelPartie) parent;
        phaseEnchere = new PanelAction(this);
        phaseChoixCarte = new PanelAction(this);
        phaseSoudoiement = new PanelAction(this);
        phaseChoixConstruction = new PanelAction(this);
        phaseCanalSup = new PanelAction(this);
        phaseSecheresse = new PanelAction(this);
        phaseDiaDePaga = new PanelAction(this);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        setPreferredSize(new Dimension(homeDimension.width / 3 * 2, homeDimension.height / 3));
        // setBackground(BG_TRANSPARENT);
        setOpaque(false);
        setLayout(new BorderLayout());

        // on ajoute les composant au panel CardLayout
        phases.setLayout(cardLayout);
        phases.setPreferredSize(homeDimension);
        phases.setOpaque(false);
        phases.add(phaseEnchere, listPhases[0]);
        phases.add(phaseChoixCarte, listPhases[1]);
        phases.add(phaseSoudoiement, listPhases[2]);
        phases.add(phaseChoixConstruction, listPhases[3]);
        phases.add(phaseCanalSup, listPhases[4]);
        phases.add(phaseSecheresse, listPhases[5]);
        phases.add(phaseDiaDePaga, listPhases[6]);

        add(phases, BorderLayout.CENTER);

        phaseEnchere.initComponent();
        phaseEnchere.add(phaseEnchere.enchereObjects());
        phaseEnchere.setPreferredSize(homeDimension);
        phaseEnchere.setBorder(BorderFactory.createLineBorder(Color.blue));
        cardLayout.show(phases, listPhases[0]);

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
