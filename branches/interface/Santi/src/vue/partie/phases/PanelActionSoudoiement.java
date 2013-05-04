package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.PositionSegment;
import vue.components.Bouton;

public class PanelActionSoudoiement extends PanelAction {
    private static final long serialVersionUID = 1L;
    private final Bouton soudoyer = new Bouton("Soudoyer");
    private final Bouton soutenir = new Bouton("Soutenir soudoyeur");
    private final Bouton passer = new Bouton("Passer");

    public PanelActionSoudoiement(Container parent) {
        super(parent);
        soudoyer.addActionListener(new SoudoyerListener());
    }

    public PanelActionSoudoiement(Container parent, String name) {
        super(parent, name);
        soudoyer.setPreferredSize(new Dimension(300, 50));
        boutons.add(soudoyer);
        soudoyer.addActionListener(new SoudoyerListener());

        soutenir.setPreferredSize(new Dimension(300, 50));
        boutons.add(soutenir);
        soutenir.addActionListener(new SoudoyerListener());

        passer.setPreferredSize(new Dimension(300, 50));
        boutons.add(passer);
        passer.addActionListener(new SoudoyerListener());
    }

    public PanelActionSoudoiement(Container parent, String name, String[] actions) {
        super(parent, name, actions);
        soudoyer.addActionListener(new SoudoyerListener());
    }

    @Override
    public void initComponent() {
        super.initComponent();

        JPanel soudoiementPanel = new JPanel();
        soudoiementPanel.setLayout(new BoxLayout(soudoiementPanel, BoxLayout.Y_AXIS));

        enchereObjects();
        soudoiementPanel.add(betPanel);

        positionChoisie();
        soudoiementPanel.add(positionChoisiePanel);

        propositions();
        soudoiementPanel.add(propositionsPanel);

        add(soudoiementPanel);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // System.out.println("update panelActionSoudoiement");
        if (!santiago.getEnchereContructeur().isEmpty()) {
            removeAll();
            initComponent();
            validate();
        }
        if (santiago.getPositionSegmentCourant() != null) {
            positionValeur.setText(santiago.getPositionSegmentCourant().toString());
        }
    }

    private class SoudoyerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Bouton bouton = (Bouton) e.getSource();
            PositionSegment canal = santiago.getPositionSegmentCourant();
            if (bouton.getText().compareToIgnoreCase("Soudoyer") == 0 || bouton.getText().compareToIgnoreCase("Soutenir soudoyeur") == 0) {
                if (canal != null) {
                    if (!santiago.encherePositionCanal(canal, Integer.valueOf(betText.getText()))) {
                        JOptionPane.showMessageDialog(parent, "proposition non validé !!");
                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "Séléctionnez un segment");
                }
            } else if (bouton.getText().compareToIgnoreCase("Passer") == 0) {
                santiago.incrementerJoueurCourantSoudoiement();
            }
        }

    }
}
