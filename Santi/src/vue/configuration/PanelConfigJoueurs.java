package vue.configuration;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import model.Joueur;
import vue.AbstractPanel;
import vue.components.BgComboBox;
import vue.components.BgTextField;
import vue.components.Bouton;
import controller.ConfigurationListener;

public class PanelConfigJoueurs extends AbstractPanel {

    // Atributs
    private static final long serialVersionUID = 1L;

    private JComboBox<String> nbJoueurs;
    private final BgTextField[] joueursNames = new BgTextField[5];
    private final Bouton[] joueursColor = new Bouton[5];

    @Override
    public void initComponent() {
        // Initialisation des attributs/composants
        super.initComponent();
        if (getParent() != null) {
            // création des composants
            String[] tabJoueurs = new String[] { "3", "4", "5" };
            nbJoueurs = new BgComboBox(tabJoueurs);
            for (int i = 0; i < 5; i++) {
                joueursNames[i] = new BgTextField("Joueur " + (i + 1));
                joueursColor[i] = new Bouton("Color..");
            }

            // Listener
            ConfigurationListener confListener = new ConfigurationListener(this);
            nbJoueurs.addItemListener(confListener);

            // attribut du conteneur this (panelConfiguration)
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN_BORDER), "Joueurs", TitledBorder.CENTER,
                    TitledBorder.TOP, POLICE_30, FG_COLOR));
            setLayout(new FlowLayout());
            setPreferredSize(new Dimension(homeDimension.width, getPreferredSize().height));
            setBackground(BG_COLOR);
            setForeground(FG_COLOR);

            // on ajoute les composants au conteneur
            add(nbJoueurs);

            // ToolTipManager.sharedInstance().setInitialDelay(0);
            // joueursNames[i].setToolTipText("Infos");// Active l'infobulle
            // joueursNames[i].setToolTipText(null); // Désactive
            for (int i = 0; i < joueursNames.length; i++) {
                add(joueursNames[i]);
                joueursNames[i].initComponent();
                add(joueursColor[i]);
                joueursColor[i].addActionListener(confListener);
                if (i < 3) {
                    joueursNames[i].setEnabled(true);
                    joueursNames[i].setEditable(true);
                    joueursColor[i].setEnabled(true);
                } else {
                    joueursNames[i].setEnabled(false);
                    joueursNames[i].setEditable(false);
                    joueursColor[i].setEnabled(false);
                }
            }

            // initialisation des composants

            isInit = true;
        } else {
            System.out.println(getClass().toString() + " Ajouter ce panneau a un conteneur avant de l'initialiser");
        }
    }

    public void activeNbJoueurTextField(int nbJ) {
        for (int i = 0; i < nbJ; i++) {
            joueursNames[i].setEditable(true);
            joueursNames[i].setEnabled(true);
        }
        for (int i = nbJ; i < joueursNames.length; i++) {
            joueursNames[i].setEditable(false);
            joueursNames[i].setEnabled(false);
        }
    }

    public void chooseColor() {
        validate();
    }

    public ArrayList<Joueur> getListJoueurs() {
        int nbJoueur = Integer.valueOf(nbJoueurs.getItemAt(nbJoueurs.getSelectedIndex()));
        ArrayList<Joueur> listJoueurs = new ArrayList<>(nbJoueur);
        for (int i = 0; i < nbJoueur; i++) {
            listJoueurs.add(new Joueur(joueursNames[i].getText(), joueursColor[i].getText()));
        }
        return listJoueurs;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    // @Override
    // public void paintComponent(Graphics g) {
    // // super.paintComponent(g);
    // // attribut du conteneur this (panelConfiguration)
    // setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GREEN_BORDER),
    // "Joueurs", TitledBorder.CENTER,
    // TitledBorder.TOP, POLICE_30, FG_COLOR));
    // // setBackground(BG_COLOR);
    // // setForeground(FG_COLOR);
    // }
}
