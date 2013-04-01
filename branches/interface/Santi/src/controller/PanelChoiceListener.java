package controller;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import vue.BgButton;
import vue.PanelHome;

public class PanelChoiceListener implements MouseListener,
		MouseMotionListener {
	private final Container panelHome;

	public PanelChoiceListener(Container ph) {
		panelHome = ph;
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
		// TODO Auto-generated method stub
		System.out.println("clicked on the button");
		if (e.getSource() instanceof BgButton) {
			BgButton b = (BgButton) e.getSource();
			if (b.getText().compareTo("Configurer") == 0) {
				((PanelHome) panelHome).configurer();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
