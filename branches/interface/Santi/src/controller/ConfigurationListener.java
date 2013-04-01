package controller;

import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import vue.BgButton;
import vue.PanelConfigJoueurs;
import vue.PanelConfigRetour;

public class ConfigurationListener implements MouseListener,
		MouseMotionListener, ItemListener {

	public final Container panelConfig;

	public ConfigurationListener(Container pc) {
		panelConfig = pc;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof BgButton) {
			BgButton b = (BgButton) e.getSource();
			if (b.getText().compareTo("Retour") == 0) {
				((PanelConfigRetour) panelConfig).retour();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String item = (String) e.getItem();
		if (item.compareTo(" ") == 0) {
			((PanelConfigJoueurs) panelConfig).activeNbJoueurTextField(0);
		} else {
			((PanelConfigJoueurs) panelConfig).activeNbJoueurTextField(Integer
					.valueOf(item));
		}
	}
}
