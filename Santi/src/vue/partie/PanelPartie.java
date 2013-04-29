package vue.partie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;

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
        setLayout(new BorderLayout());

        PanelPartieAction ppa = new PanelPartieAction(this);
        add(ppa, BorderLayout.SOUTH);
        ppa.initComponent();

        isInit = true;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // frame.setPreferredSize(new Dimension(500, 600));
    //
    // PanelHome ph = new PanelHome(frame);
    // frame.setContentPane(ph);
    // ph.initComponent();
    //
    // PanelPartie partie = new PanelPartie(ph);
    // ph.add(partie);
    // partie.initComponent();
    //
    // PanelPartieAction ppa = new PanelPartieAction(partie);
    // partie.add(ppa);
    // ppa.initComponent();
    //
    // frame.pack();
    // frame.setVisible(true);
    // }

}
