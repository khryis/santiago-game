package vue.reglages;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;

import vue.AbstractPanel;
import vue.home.PanelHome;

public class PanelReglages extends AbstractPanel {
    private static final long serialVersionUID = 1L;
    PanelHome panelHome;

    public PanelReglages(Container parent) {
        super(parent);
        panelHome = (PanelHome) parent;
    }

    @Override
    public void initComponent() {
        super.initComponent();

        setPreferredSize(new Dimension(homeDimension.width, homeDimension.height / 2));
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }

}
