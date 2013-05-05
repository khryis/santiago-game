package vue.partie.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import javax.swing.BorderFactory;
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
import vue.partie.PanelPartie;
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

    public JPanel cardPanel = new JPanel();
    public JButton[] cards;

    public JPanel propositionsPanel = new JPanel();

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

        Graphics g = getGraphics();

        if (g != null) {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

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
        // TODO pouvoir rendre le compteur des carte selectionné Rouge
        cardPanel = new JPanel();
        LayoutManager boxLayout = new BoxLayout(cardPanel, BoxLayout.X_AXIS);
        cardPanel.setLayout(boxLayout);
        cardPanel.setOpaque(true);
        cardPanel.setOpaque(false);

        if (santiago != null) {
            if (santiago.getPlateau() != null) {
                if (santiago.getPlateau().getCartesDevoilees() != null) {
                    ArrayList<Carte> carteDevoilees = santiago.getPlateau().getCartesDevoilees();
                    cards = new JButton[carteDevoilees.size()];
                    for (int i = 0; i < carteDevoilees.size(); i++) {
                        cards[i] = new CardButton(carteDevoilees.get(i));
                        cardPanel.add(cards[i]);
                        cards[i].addActionListener(new CliclOnCard());
                        cardPanel.add(Box.createRigidArea(new Dimension(30, 0)));
                    }
                }
            }
        }

        return cardPanel;
    }

    public void propositions() {
        propositionsPanel = new JPanel();
        propositionsPanel.setLayout(new BoxLayout(propositionsPanel, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();
        for (final Map.Entry<PositionSegment, ArrayList<Joueur>> entry : santiago.getEnchereContructeur().entrySet()) {
            final PositionSegment canal = entry.getKey();
            JRadioButton radio = new JRadioButton(canal.toString() + " , soudoiement actuel : " + santiago.valeurPourUneProposition(canal), false);
            if (canal.equals(santiago.getPositionSegmentCourant())) {
                radio.setSelected(true);
            }
            radio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((JRadioButton) e.getSource()).isSelected()) {
                        santiago.setPositionSegmentCourant(canal);
                    }
                }

            });
            group.add(radio);
            propositionsPanel.add(radio);
        }
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

    public static JPanel finDePartie() {
        JPanel finDePartie = new JPanel();

        finDePartie.setLayout(new FlowLayout(FlowLayout.CENTER));
        finDePartie.add(new JLabel("Cette belle partie est maintenant finie"));

        return finDePartie;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO
    }

    private class CliclOnCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            for (JButton b : cards) {
                b.setBorderPainted(false);
            }
            if (e.getSource() instanceof CardButton) {
                CardButton cb = (CardButton) e.getSource();
                if (cb.getParent().getParent().getParent() instanceof PanelActionChoixCarte) {

                    Carte carte = cb.getCarte();
                    cb.setBorderPainted(true);
                    cb.setBorder(BorderFactory.createLineBorder(Color.black, 3));
                    santiago.setCarteChoisie(carte);
                    // FIXME à voir
                    ((PanelPartie) panelPartieAction.getParent()).changeBoardSelected();
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
