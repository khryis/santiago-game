package vue.partie;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.JPanel;

import vue.AbstractPanel;
import vue.partie.phases.PanelAction;
import vue.partie.phases.PanelActionCanalSup;
import vue.partie.phases.PanelActionChoixCarte;
import vue.partie.phases.PanelActionChoixConstruction;
import vue.partie.phases.PanelActionDiaDePaga;
import vue.partie.phases.PanelActionEnchere;
import vue.partie.phases.PanelActionSecheresse;
import vue.partie.phases.PanelActionSoudoiement;

public class PanelPartieAction extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    private final PanelPartie panelPartie;

    private final PanelAction phaseEnchere;
    private final PanelAction phaseChoixCarte;
    private final PanelAction phaseSoudoiement;
    private final PanelAction phaseChoixConstruction;
    private final PanelAction phaseCanalSup;
    private final PanelAction phaseSecheresse;
    private final PanelAction phaseDiaDePaga;

    public JPanel phases = new JPanel();
    public CardLayout cardLayout = new CardLayout();
    public String[] listPhases = { "PHASE D'ENCHERE", "PHASE DE CHOIX DES CARTES", "PHASE DE SOUDOIEMENT", "PHASE DE CHOIX D'UNE CONSTRUCTION", "PHASE DE CANAL SUPPLEMENTAIRE", "PHASE DE SECHERESSE",
            "DIA_DE_PAGA" };
    int indice = 0;

    public PanelPartieAction(Container parent) {
        super(parent);
        panelPartie = (PanelPartie) parent;
        phaseEnchere = new PanelActionEnchere(this, listPhases[0]);
        phaseChoixCarte = new PanelActionChoixCarte(this, listPhases[1]);
        phaseSoudoiement = new PanelActionSoudoiement(this, listPhases[2]);
        phaseChoixConstruction = new PanelActionChoixConstruction(this, listPhases[3]);
        phaseCanalSup = new PanelActionCanalSup(this, listPhases[4]);
        phaseSecheresse = new PanelActionSecheresse(this, listPhases[5]);
        phaseDiaDePaga = new PanelActionDiaDePaga(this, listPhases[6]);
        indice = 0;
    }

    @Override
    public void initComponent() {
        super.initComponent();

        setPreferredSize(new Dimension(homeDimension.width / 3 * 2, homeDimension.height - 600));
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
        phaseChoixCarte.initComponent();
        phaseSoudoiement.initComponent();
        phaseChoixConstruction.initComponent();
        phaseCanalSup.initComponent();
        phaseSecheresse.initComponent();
        phaseDiaDePaga.initComponent();

        // FIXME a enlever après les test
        indice = 0;
        santiago.repercuterModification();
        //

        cardLayout.show(phases, listPhases[indice]);

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        System.out.println("update PanelPartieAction");
        if (santiago.isPhaseFinie()) {
            santiago.setPhaseFinie(false);
            incrementerCardLayout();
            cardLayout.show(phases, listPhases[indice]);
        }
    }

    private void incrementerCardLayout() {
        indice++;
        if (indice == listPhases.length) {
            indice = 0;
        }
    }

}