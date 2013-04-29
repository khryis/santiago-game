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

import model.Carte;
import model.Joueur;
import model.PositionSegment;
import vue.AbstractPanel;
import vue.components.Bouton;

public class PanelAction extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    private final PanelPartieAction panelPartieAction;
    private final ArrayList<Bouton> listBoutons;
    private final JPanel boutons = new JPanel();
    private String nameAction;

    // private final JPanel bet = new JPanel();
    // private final JPanel card = new JPanel();
    // private final JPanel soudoiement = new JPanel();
    //
    // private PositionSegment segmentSelected;
    // private Carte carteSelected;

    public PanelAction(Container parent) {
        super(parent);
        panelPartieAction = (PanelPartieAction) parent;
        listBoutons = new ArrayList<>();
    }

    public PanelAction(Container parent, String name, String[] actions) {
        super(parent);
        panelPartieAction = (PanelPartieAction) parent;
        this.nameAction = name;
        listBoutons = new ArrayList<>();
        for (String action : actions) {
            Bouton b = new Bouton(action);
            b.setPreferredSize(new Dimension(300, 50));
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
        setLayout(new GridLayout(3, 1));
        setPreferredSize(homeDimension);
        setBorder(BorderFactory.createLineBorder(Color.GREEN));

        JPanel title = new JPanel();
        title.add(new JTextField(this.nameAction));

        add(title);
        add(boutons);
    }

    public static JPanel enchereObjects() {
        JPanel betPanel = new JPanel();

        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        betPanel.add(new JLabel("mise : "));
        betPanel.add(new JTextField("", 5));
        // TODO remplacer par le max par le solde du joueur, besoin d'un booleen
        // 'A la main' pour un joueur
        betPanel.add(new JSlider(0, 50));

        return betPanel;
    }

    public static JPanel positionChoisie() {
        JPanel positionChoisiePanel = new JPanel(new FlowLayout());

        positionChoisiePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        positionChoisiePanel.add(new JLabel("position choisie : "));
        positionChoisiePanel.add(new JLabel("", 10));

        return positionChoisiePanel;
    }

    public static JPanel selectedObjects() {
        JPanel selectedObject = new JPanel();

        selectedObject.setLayout(new FlowLayout(FlowLayout.CENTER));
        selectedObject.add(new JLabel("Carte séléctionné : "));
        selectedObject.add(new JLabel("", 10));

        return selectedObject;
    }

    public JPanel cardsObjects() {
        JPanel cardPanel = new JPanel();

        cardPanel.setLayout(new GridLayout(1, 5));
        if (santiago != null) {
            if (santiago.getPlateau() != null) {
                if (santiago.getPlateau().getCartesDevoilees() != null) {
                    ArrayList<Carte> carteDevoilees = santiago.getPlateau().getCartesDevoilees();
                    for (Carte carte : carteDevoilees) {
                        cardPanel.add(new JTextField(carte.getType().toString()));
                    }
                }
            }
        }

        return cardPanel;
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
                        // TODO Action
                        // segmentSelected = entry.getKey();
                    }
                }

            });
            group.add(radio);
            propositionsPanel.add(radio);
        }
        return propositionsPanel;
    }

    public static JPanel secheresseInfo() {
        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPanel.add(new JLabel("Les cartes non irriguée perdent un marqueur, ou deviennent désertes.."));

        return infoPanel;
    }

    public static JPanel diaDePaga() {
        JPanel diaDePaga = new JPanel();

        diaDePaga.setLayout(new FlowLayout(FlowLayout.CENTER));
        diaDePaga.add(new JLabel("Tous les joueurs reçoivent 3 pesos"));

        return diaDePaga;
    }

    public JPanel cardChoice() {
        JPanel cardChoice = new JPanel();

        cardChoice = new JPanel(new GridLayout(3, 1));

        JPanel positionPanel = positionChoisie();
        positionPanel.setPreferredSize(new Dimension(panelPartieAction.getPreferredSize().width, positionPanel.getPreferredSize().height));
        cardChoice.add(positionPanel);

        JPanel selectedPanel = selectedObjects();
        selectedPanel.setPreferredSize(new Dimension(panelPartieAction.getPreferredSize().width, selectedPanel.getPreferredSize().height));
        cardChoice.add(selectedPanel);

        JPanel cardPanel = cardsObjects();
        cardPanel.setPreferredSize(new Dimension(panelPartieAction.getPreferredSize().width, cardPanel.getPreferredSize().height));
        cardChoice.add(cardPanel);

        return cardChoice;
    }

    public JPanel soudoiementChoice() {
        JPanel soudoiementPanel = new JPanel(new GridLayout(3, 1));

        JPanel betPanel = enchereObjects();
        betPanel.setPreferredSize(new Dimension(panelPartieAction.getPreferredSize().width, betPanel.getPreferredSize().height));
        soudoiementPanel.add(betPanel);

        JPanel positionPanel = positionChoisie();
        positionPanel.setPreferredSize(new Dimension(panelPartieAction.getPreferredSize().width, positionPanel.getPreferredSize().height));
        soudoiementPanel.add(positionPanel);

        JPanel propositionsPanel = propositions();
        soudoiementPanel.add(propositionsPanel);

        return soudoiementPanel;
    }

    public JPanel choixPropositions() {
        JPanel choixPanel = new JPanel(new GridLayout(2, 1));

        JPanel positionPanel = positionChoisie();
        positionPanel.setPreferredSize(new Dimension(panelPartieAction.getPreferredSize().width, positionPanel.getPreferredSize().height));
        choixPanel.add(positionPanel);

        JPanel propositionsPanel = propositions();
        choixPanel.add(propositionsPanel);

        return choixPanel;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }
}
