package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Santiago;

public abstract class AbstractPanel extends JPanel implements Observer, IConstante {
    private static final long serialVersionUID = 1L;
    private static int NBPANEL = 0;
    private final int id;
    // Attributs récurrent d'une instance Panel
    protected Container parent;
    protected Santiago santiago;
    protected Dimension homeDimension;
    protected boolean isInit;

    // Contructeur général
    public AbstractPanel(Container parent) {
        super();
        id = NBPANEL;
        NBPANEL++;
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
        // Screen size
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // Screen insets
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        // Get the real width/height
        int width = (int) (screen.getWidth() - insets.left - insets.right);
        int height = (int) (screen.getHeight() - insets.top - insets.bottom);
        setPreferredSize(new Dimension(width, height));
        homeDimension = new Dimension(width, height);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((homeDimension == null) ? 0 : homeDimension.hashCode());
        result = prime * result + (isInit ? 1231 : 1237);
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((santiago == null) ? 0 : santiago.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractPanel other = (AbstractPanel) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
