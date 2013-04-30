package vue.partie;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;

import model.Joueur;
import model.NiveauPartie;
import model.PositionSegment;
import vue.AbstractPanel;

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
    public String[] listPhases = { "PHASE D'ENCHERE", "PHASE DE CHOIX DES CARTES", "PHASE DE SOUDOIEMENT",
            "PHASE DE CHOIX D'UNE CONSTRUCTION", "PHASE DE CANAL SUPPLEMENTAIRE", "PHASE DE SECHERESSE", "DIA_DE_PAGA" };
    int indice = 0;

    public PanelPartieAction(Container parent) {
        super(parent);
        panelPartie = (PanelPartie) parent;
        phaseEnchere = new PanelAction(this, listPhases[0], new String[] { "encherir", "passer son tour" });
        phaseChoixCarte = new PanelAction(this, listPhases[1], new String[] { "valider" });
        phaseSoudoiement = new PanelAction(this, listPhases[2], new String[] { "soudoyer", "soutenir soudoyeur", "passer son tour" });
        phaseChoixConstruction = new PanelAction(this, listPhases[3], new String[] { "choisir proposition",
                "choisir une autre construction", "passer son tour" });
        phaseCanalSup = new PanelAction(this, listPhases[4], new String[] { "valider position", "passer son tour" });
        phaseSecheresse = new PanelAction(this, listPhases[5], new String[] { "Phase suivante" });
        phaseDiaDePaga = new PanelAction(this, listPhases[6], new String[] { "Phase suivante" });
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
        phaseEnchere.add(PanelAction.enchereObjects());

        // FIXME à enlever, pour test
        // ---------------------------
        santiago.setNiveauPartie(NiveauPartie.FACILE);
        ArrayList<Joueur> listjoueurs = new ArrayList<>();
        listjoueurs.add(new Joueur("tull", "bleu"));
        listjoueurs.add(new Joueur("andre", "vert"));
        listjoueurs.add(new Joueur("marc", "gris"));
        santiago.setListJoueurs(listjoueurs);
        santiago.initPartie();
        santiago.devoilerCarte();
        // ---------------------------

        phaseChoixCarte.initComponent();
        phaseChoixCarte.add(phaseChoixCarte.cardChoice());

        // FIXME à enlever, pour test
        // ----------------------------
        santiago.encherePositionCanal(new PositionSegment(0, 0, 0, 1), new Joueur("tull", "bleu"), 3);
        santiago.encherePositionCanal(new PositionSegment(1, 1, 1, 2), new Joueur("andre", "vert"), 5);
        santiago.encherePositionCanal(new PositionSegment(3, 4, 4, 4), new Joueur("marc", "gris"), 4);
        // ----------------------------

        phaseSoudoiement.initComponent();
        phaseSoudoiement.add(phaseSoudoiement.soudoiementChoice());

        phaseChoixConstruction.initComponent();
        phaseChoixConstruction.add(phaseChoixConstruction.choixPropositions());

        phaseCanalSup.initComponent();
        phaseCanalSup.add(PanelAction.positionChoisie());

        phaseSecheresse.initComponent();
        phaseSecheresse.add(PanelAction.secheresseInfo());

        phaseDiaDePaga.initComponent();
        phaseDiaDePaga.add(PanelAction.diaDePaga());

        cardLayout.show(phases, listPhases[5]);

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
