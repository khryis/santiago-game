package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;

import vue.home.PanelHome;

public class SantiagoFrame extends JFrame implements Observer, KeyListener, WindowListener {

    private static final long serialVersionUID = 1L;
    private final PanelHome panelHome;

    public SantiagoFrame() {
        super();

        // initialisation des composants
        panelHome = new PanelHome(this);

        // Listener
        addKeyListener(this);
        addWindowListener(this);

        // atribut de la frame
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUndecorated(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Screen size
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // Screen insets
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        // Get the real width/height
        int width = (int) (screen.getWidth() - insets.left - insets.right);
        int height = (int) (screen.getHeight() - insets.top - insets.bottom);

        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        setLocation(insets.left, insets.top);

        // // Menu
        // JMenuBar menu = new JMenuBar();
        // JMenu fichier = new JMenu("Fichier");
        // JMenuItem save = new JMenuItem("Sauvegarder");
        // save.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // // sauvegarder le fichier
        // }
        // });
        // JMenuItem load = new JMenuItem("Charger");
        // load.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // // charger le fichier
        // }
        // });
        // JMenuItem fermer = new JMenuItem("Fermer l'application");
        // fermer.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // dispose();
        // }
        // });
        // fichier.add(save);
        // fichier.add(load);
        // fichier.addSeparator();
        // fichier.add(fermer);
        // menu.add(fichier);
        // JMenu aide = new JMenu("Aide");
        // JMenuItem regles = new JMenuItem("Règles");
        // regles.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // // afficher les règles du jeu
        // }
        // });
        // JMenuItem apropos = new JMenuItem("A propos de");
        // apropos.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent arg0) {
        // // pane WTF
        // }
        // });
        // aide.add(regles);
        // aide.add(apropos);
        // menu.add(aide);
        // this.setJMenuBar(menu);

        // on ajoute les composants au conteneur principal
        setContentPane(panelHome);
        panelHome.initComponent();

        // Set visible the Frame
        pack();
        setVisible(true);
        requestFocus();
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SantiagoFrame();
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setState(Frame.ICONIFIED);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        setState(Frame.NORMAL);
        requestFocus();
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
