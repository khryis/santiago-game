package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Carte;
import model.Joueur;
import model.PositionSegment;
import vue.AbstractPanel;
import vue.components.Bouton;
import vue.components.CardButton;
import vue.partie.PanelPartieAction;

public class PanelAction extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    private final PanelPartieAction panelPartieAction;
    private final ArrayList<Bouton> listBoutons;
    public final JPanel boutons = new JPanel();
    private String nameAction;

    public JPanel positionChoisiePanel = new JPanel();
    public JLabel positionValeur = new JLabel();

    public JPanel betPanel = new JPanel();
    public JTextField betText = new JTextField();
    public JSlider betSlider = new JSlider();

    public JPanel cardChoisiePanel = new JPanel();
    public JLabel cardChoisie = new JLabel();

    // private final JPanel soudoiement = new JPanel();
    //
    // private PositionSegment segmentSelected;
    // private Carte carteSelected;

    public PanelAction(Container parent) {
        super(parent);
        panelPartieAction = (PanelPartieAction) parent;
        listBoutons = new ArrayList<>();
    }

    public PanelAction(Container parent, String name) {
        super(parent);
        panelPartieAction = (PanelPartieAction) parent;
        this.nameAction = name;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(homeDimension);

        JPanel title = new JPanel();
        JTextField text = new JTextField(this.nameAction);
        text.setFont(POLICE_30);
        title.add(text);

        add(title);
        add(boutons);
    }

    public void enchereObjects() {
        betPanel = new JPanel();

        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        betPanel.add(new JLabel("mise : "));
        betText = new JTextField("0", 5);
        betPanel.add(betText);
        // TODO remplacer par le max par le solde du joueur, besoin d'un booleen
        // 'A la main' pour un joueur
        Joueur joueurParole = santiago.joueurPlaying();
        betSlider = new JSlider(0, joueurParole.getSolde());
        betSlider.setValue(0);
        betSlider.addChangeListener(new SliderChangeListener());

        betPanel.add(betSlider);
    }

    public void positionChoisie() {
        positionChoisiePanel = new JPanel();

        positionChoisiePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        positionChoisiePanel.add(new JLabel("Position choisie : "));
        positionValeur = new JLabel("", 10);
        positionChoisiePanel.add(positionValeur);
    }

    public void selectedCard() {
        cardChoisiePanel = new JPanel();

        cardChoisiePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardChoisiePanel.add(new JLabel("Carte séléctionné : "));
        cardChoisie = new JLabel("", 10);
        cardChoisiePanel.add(cardChoisie);
    }

    public JPanel cardsObjects() {
        JPanel cardPanel = new JPanel();
        LayoutManager boxLayout = new BoxLayout(cardPanel, BoxLayout.X_AXIS);
        cardPanel.setLayout(boxLayout);

        if (santiago != null) {
            if (santiago.getPlateau() != null) {
                if (santiago.getPlateau().getCartesDevoilees() != null) {
                    ArrayList<Carte> carteDevoilees = santiago.getPlateau().getCartesDevoilees();
                    for (Carte carte : carteDevoilees) {
                        JButton button = new CardButton(carte);
                        cardPanel.add(button);
                        button.addActionListener(new CliclOnCard());
                        cardPanel.add(Box.createRigidArea(new Dimension(30, 0)));
                    }
                }
            }
        }

        return cardPanel;
    }

    public JPanel propositions() {
        JPanel propositionsPanel = new JPanel();
        propositionsPanel.setLayout(new BoxLayout(propositionsPanel, BoxLayout.Y_AXIS));
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

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO
    }

    private class CliclOnCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() instanceof CardButton) {
                if (((CardButton) e.getSource()).getParent().getParent().getParent() instanceof PanelActionChoixCarte) {
                    Carte carte = ((CardButton) e.getSource()).getCarte();
                    santiago.setCarteChoisie(carte);
                }
            }
        }
    }

    private class SliderChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            int pesos = betSlider.getValue();
            betText.setText(String.valueOf(pesos));
        }
    }
}
