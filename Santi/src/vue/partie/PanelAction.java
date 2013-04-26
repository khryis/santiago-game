package vue.partie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

import model.Joueur;
import model.PositionSegment;
import vue.AbstractPanel;
import vue.components.Bouton;

public class PanelAction extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    private PanelPartieAction panelPartieAction;
    private ArrayList<Bouton> listBoutons;
    private JPanel boutons = new JPanel();
    private JPanel bet = new JPanel();
    private JPanel cardChoice = new JPanel();
    private JPanel soudoiement = new JPanel();

    private PositionSegment segmentSelected;

    public PanelAction(Container parent) {
        super(parent);
        panelPartieAction = (PanelPartieAction) parent;
        listBoutons = new ArrayList<>();
    }

    public PanelAction(Container parent, String[] actions) {
        super(parent);
        panelPartieAction = (PanelPartieAction) parent;
        listBoutons = new ArrayList<>();
        for (String action : actions) {
            Bouton b = new Bouton(action);
            listBoutons.add(b);
            boutons.add(b);
        }
    }

    // Méthode dans laquelle on ajoute tout les composants voulu au JPanel
    @Override
    public void initComponent() {
        // init des composants
        super.initComponent();

        // attribut du conteneur PanelAction
        setLayout(new GridLayout(2, 1));
        setPreferredSize(homeDimension);
        setBorder(BorderFactory.createLineBorder(Color.GREEN));

        add(boutons);
    }

    public JPanel enchereObjects() {
        JPanel betPanel = new JPanel();

        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        betPanel.add(new JLabel("mise : "));
        betPanel.add(new JTextField("Indiquez votre prix"));
        // TODO remplacer par le max par le solde du joueur, besoin d'un booleen
        // 'A la main' pour un joueur
        betPanel.add(new JSlider(0, 50));

        return betPanel;
    }

    public JPanel cardChoice() {
        JPanel cardChoice = new JPanel();

        cardChoice = new JPanel(new FlowLayout(FlowLayout.CENTER));

        return cardChoice;
    }

    public JPanel propositions() {
        JPanel propositionsPanel = new JPanel();
        propositionsPanel.setLayout(new GridLayout(santiago.getEnchereContructeur().size(), 1));
        ButtonGroup group = new ButtonGroup();
        for (final Map.Entry<PositionSegment, ArrayList<Joueur>> entry : santiago.getEnchereContructeur().entrySet()) {
            JRadioButton radio = new JRadioButton(entry.getKey().toString(), false);
            radio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((JRadioButton) e.getSource()).isSelected()) {
                        segmentSelected = entry.getKey();
                    }
                }

            });
            group.add(new JRadioButton(entry.getKey().toString(), false));
        }
        return propositionsPanel;
    }

    public JPanel soudoiementChoice() {
        JPanel soudoiementPanel = new JPanel(new GridLayout(2, 1));

        JPanel betPanel = enchereObjects();
        betPanel.setPreferredSize(new Dimension(getPreferredSize().width, betPanel.getPreferredSize().height));
        soudoiementPanel.add(betPanel);
        JPanel propositionsPanel = propositions();
        soudoiementPanel.add(propositionsPanel);
        return soudoiementPanel;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
