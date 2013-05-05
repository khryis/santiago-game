package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.components.Bouton;
import vue.home.PanelHome;

public class PanelActionFinDePartie extends PanelAction {
    private static final long serialVersionUID = 1L;

    private final Bouton resultat = new Bouton("Résultat");
    private final Bouton retour = new Bouton("Retour");

    public PanelActionFinDePartie(Container parent) {
        super(parent);
    }

    public PanelActionFinDePartie(Container parent, String name) {
        super(parent, name);
        resultat.setPreferredSize(new Dimension(300, 50));
        boutons.add(resultat);
        resultat.addActionListener(new ResultatListener());

        retour.setPreferredSize(new Dimension(300, 50));
        boutons.add(retour);
        retour.addActionListener(new RetourListener());
    }

    public PanelActionFinDePartie(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        add(PanelAction.finDePartie());
    }

    public class ResultatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Bouton b = (Bouton) e.getSource();
            if (b.getText().compareToIgnoreCase("résultat") == 0) {
                int[] score = santiago.score();
                String msg = "";
                int win = 0;
                int max = score[win];
                for (int i = 0; i < santiago.getListJoueurs().size(); i++) {
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
    }

    public class RetourListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Bouton b = (Bouton) e.getSource();
            if (b.getText().compareToIgnoreCase("retour") == 0) {
                ((PanelHome) parent.getParent().getParent().getParent()).retourPartie();
            }
        }
    }

}
