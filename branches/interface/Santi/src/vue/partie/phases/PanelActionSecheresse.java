package vue.partie.phases;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.components.Bouton;

public class PanelActionSecheresse extends PanelAction {
    private static final long serialVersionUID = 1L;

    private final Bouton suivant = new Bouton("Phase suivante");

    public PanelActionSecheresse(Container parent) {
        super(parent);
    }

    public PanelActionSecheresse(Container parent, String name) {
        super(parent, name);
        suivant.setPreferredSize(new Dimension(300, 50));
        boutons.add(suivant);
        suivant.addActionListener(new SuivantListener());
    }

    public PanelActionSecheresse(Container parent, String name, String[] actions) {
        super(parent, name, actions);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        add(PanelAction.secheresseInfo());
    }

    public class SuivantListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Bouton b = (Bouton) e.getSource();
            if (b.getText().compareToIgnoreCase("Phase suivante") == 0) {
                santiago.secheresse();
            }
        }
    }
}
