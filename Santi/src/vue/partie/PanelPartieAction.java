package vue.partie;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.JOptionPane;
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

    private final PanelAction phaseEnchere;
    private final PanelAction phaseChoixCarte;
    private final PanelAction phaseSoudoiement;
    private final PanelAction phaseChoixConstruction;
    private final PanelAction phaseCanalSup;
    private final PanelAction phaseSecheresse;
    private final PanelAction phaseDiaDePaga;

    private final PanelInformation panelInformation;

    public JPanel phases = new JPanel();
    public CardLayout cardLayout = new CardLayout();
    public String[] listPhases = { "PHASE D'ENCHERE", "PHASE DE CHOIX DES CARTES", "PHASE DE SOUDOIEMENT", "PHASE DE CHOIX D'UNE CONSTRUCTION", "PHASE DE CANAL SUPPLEMENTAIRE", "PHASE DE SECHERESSE",
            "DIA DE PAGA" };
    int indice = 0;

    public PanelPartieAction(Container parent) {
        super(parent);
        phaseEnchere = new PanelActionEnchere(this, listPhases[0]);
        phaseChoixCarte = new PanelActionChoixCarte(this, listPhases[1]);
        phaseSoudoiement = new PanelActionSoudoiement(this, listPhases[2]);
        phaseChoixConstruction = new PanelActionChoixConstruction(this, listPhases[3]);
        phaseCanalSup = new PanelActionCanalSup(this, listPhases[4]);
        phaseSecheresse = new PanelActionSecheresse(this, listPhases[5]);
        phaseDiaDePaga = new PanelActionDiaDePaga(this, listPhases[6]);

        panelInformation = new PanelInformation(this);

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
        phases.setPreferredSize(new Dimension(800, homeDimension.height - 600));
        phases.setOpaque(false);

        phases.add(phaseEnchere, listPhases[0]);
        phases.add(phaseChoixCarte, listPhases[1]);
        phases.add(phaseSoudoiement, listPhases[2]);
        phases.add(phaseChoixConstruction, listPhases[3]);
        phases.add(phaseCanalSup, listPhases[4]);
        phases.add(phaseSecheresse, listPhases[5]);
        phases.add(phaseDiaDePaga, listPhases[6]);

        add(phases, BorderLayout.CENTER);
        add(panelInformation, BorderLayout.EAST);

        phaseEnchere.initComponent();
        phaseChoixCarte.initComponent();
        phaseSoudoiement.initComponent();
        phaseChoixConstruction.initComponent();
        phaseCanalSup.initComponent();
        phaseSecheresse.initComponent();
        phaseDiaDePaga.initComponent();
        panelInformation.initComponent();

        cardLayout.show(phases, listPhases[indice]);

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
    	if (!santiago.isFinish()) {
    		if (santiago.isPhaseFinie()) {
    			santiago.setPhaseFinie(false);
    			incrementerCardLayout();
    			cardLayout.show(phases, listPhases[indice]);
    		}
    	} else {
    		int [] score = santiago.score();
    		String msg = "";
    		int win = 0;
    		int max = score[win];
    		for (int i = 0; i  < santiago.getListJoueurs().size(); i ++) {
    			msg = msg + santiago.getListJoueurs().get(i).getNom() + " : " + score[i] + "\n";
    			if (score[i] > max) {
    				max = score[i];
    				win = i;
    			}
    		}
    		msg = msg + "\n Le joueur " + santiago.getListJoueurs().get(win).getNom() + " gagne avec : " + max + " points\n";
    		JOptionPane.showMessageDialog(parent, msg, "Résultats", JOptionPane.PLAIN_MESSAGE);
    	}
    }

    private void incrementerCardLayout() {
        indice++;
        if (indice == listPhases.length) {
            indice = 0;
        }
    }
}
