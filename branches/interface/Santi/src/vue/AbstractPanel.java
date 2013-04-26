package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Santiago;

public abstract class AbstractPanel extends JPanel implements Observer, IConstante {
    private static final long serialVersionUID = 1L;

    // Attributs récurrent d'une instance Panel
    protected Container parent;
    protected Santiago santiago;
    protected Dimension homeDimension;
    protected boolean isInit;

    // Contructeur général
    public AbstractPanel(Container parent) {
        super();
        santiago = null;
        homeDimension = null;
        isInit = false;
        this.parent = parent;
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
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setPreferredSize(new Dimension(toolkit.getScreenSize().width, toolkit.getScreenSize().height));
        homeDimension = new Dimension(toolkit.getScreenSize().width, toolkit.getScreenSize().height);
        if (getParent() != null) {
            santiago = Santiago.getSantiago();
            santiago.addObserver(this);
        } else {
            System.out.println(getClass().toString() + " Ajouter ce panneau a un conteneur avant de l'initialiser");
            santiago = Santiago.getSantiago();
            santiago.addObserver(this);
        }
    }

    // Méthode qui est lancé lorsque le model effectue un Notify()
    @Override
    public abstract void update(Observable arg0, Object arg1);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(BG_TRANSPARENT);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

}
