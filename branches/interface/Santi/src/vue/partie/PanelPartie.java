package vue.partie;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import vue.AbstractPanel;
import vue.home.PanelHome;

public class PanelPartie extends AbstractPanel {

    private static final long serialVersionUID = 1L;
    PanelHome panelHome;

    public PanelPartie(Container parent) {
        super(parent);
        panelHome = (PanelHome) parent;
    }

    @Override
    public void initComponent() {
        super.initComponent();

        setPreferredSize(homeDimension);
        setBackground(NICE_GREY);

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 600));

        PanelHome ph = new PanelHome(frame);
        frame.setContentPane(ph);
        ph.initComponent();

        PanelPartie partie = new PanelPartie(ph);
        ph.add(partie);
        partie.initComponent();

        PanelPartieAction ppa = new PanelPartieAction(partie);
        partie.add(ppa);
        ppa.initComponent();

        frame.pack();
        frame.setVisible(true);
    }

}
