package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vue.IConstante;
import vue.components.Bouton;
import vue.configuration.PanelConfigJoueurs;

public class ConfigJoueurListener implements ItemListener, ActionListener {

    public final PanelConfigJoueurs panelConfigJoueur;
    public final static HashSet<Object> colors = new HashSet<>();
    private final static HashSet<Object> colorChoose = new HashSet<>();

    public ConfigJoueurListener(Container pc) {
        panelConfigJoueur = (PanelConfigJoueurs) pc;
        colors.add("");
        colors.add("blanc");
        colors.add("gris");
        colors.add("noir");
        colors.add("violet");
        colors.add("bleu");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String item = (String) e.getItem();
        if (item.compareTo(" ") == 0) {
            panelConfigJoueur.activeNbJoueurTextField(0);
        } else {
            panelConfigJoueur.activeNbJoueurTextField(Integer.valueOf(item));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = "";
        String avant = ((Bouton) e.getSource()).getText();
        if (!avant.equalsIgnoreCase(IConstante.TEXT_COULEUR)) {
            colors.add(avant);
        }
        input = (String) JOptionPane.showInputDialog(new JFrame(), "Choisir une couleur", "Customized Dialog",
                JOptionPane.QUESTION_MESSAGE, null, colors.toArray(), "");
        if (colors.contains(input)) {
            colorChoose.add(input);
            if (!input.equalsIgnoreCase("")) {
                colors.remove(colors.remove(input));
            }
        }
        ((Bouton) e.getSource()).setText(input);
    }
}
