package vue;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Santiago;

public abstract class AbstractPanel extends JPanel implements Observer, IConstante {
    private static final long serialVersionUID = 1L;

    // Attributs récurrent d'une instance Panel
    protected Santiago santiago;
    protected Dimension homeDimension;
    protected boolean isInit;

    // Contructeur général
    public AbstractPanel() {
        super();
        santiago = null;
        homeDimension = null;
        isInit = false;
    }

    public Santiago getSantiago() {
        return santiago;
    }

    public Dimension getDimension() {
        return homeDimension;
    }

    public boolean isInit() {
        return isInit;
    }

    // Méthode dans laquelle on ajoute tout les composants voulu au JPanel
    public void initComponent() {
        if (getParent() != null) {
            if (getParent() instanceof AbstractPanel) {
                homeDimension = getParent().getSize();
                santiago = ((AbstractPanel) getParent()).getSantiago();
            } else {
                homeDimension = getParent().getSize();
                santiago = new Santiago();
            }
        } else {
            System.out.println(getClass().toString() + " Ajouter ce panneau a un conteneur avant de l'initialiser");
        }
    }

    // Méthode qui est lancé lorsque le model effectue un Notify()
    @Override
    public abstract void update(Observable arg0, Object arg1);

    // @Override
    // public void paintComponent(Graphics g) {
    // super.paintComponent(g);
    // }

}
