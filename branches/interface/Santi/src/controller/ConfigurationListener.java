package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vue.components.Bouton;
import vue.configuration.PanelConfigJoueurs;

public class ConfigurationListener implements MouseListener, MouseMotionListener, ItemListener, ActionListener {

    public final Container panelConfig;
    public final static ArrayList<Object> colors = new ArrayList<>();
    private final static ArrayList<Object> colorChoose = new ArrayList<>();

    public ConfigurationListener(Container pc) {
        panelConfig = pc;
        colors.add("");
        colors.add("blanc");
        colors.add("gris");
        colors.add("noir");
        colors.add("violet");
        colors.add("bleu");
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String item = (String) e.getItem();
        if (item.compareTo(" ") == 0) {
            ((PanelConfigJoueurs) panelConfig).activeNbJoueurTextField(0);
        } else {
            ((PanelConfigJoueurs) panelConfig).activeNbJoueurTextField(Integer.valueOf(item));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = "";
        s = (String) JOptionPane.showInputDialog(new JFrame(), "Choisir une couleur", "Customized Dialog", JOptionPane.QUESTION_MESSAGE,
                null, colors.toArray(), colors.get(0));
        if (!s.isEmpty()) {
            colorChoose.add(colors.get(colors.indexOf(s)));
            colors.remove(colors.indexOf(s));
        } else {
            if (colorChoose.contains(((Bouton) e.getSource()).getText())) {
                s = ((Bouton) e.getSource()).getText();
                colors.add(s);
                colorChoose.remove(s);
            }
        }
        ((Bouton) e.getSource()).setText(s);

        ((PanelConfigJoueurs) panelConfig).chooseColor();
    }
}
