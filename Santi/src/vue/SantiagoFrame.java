package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
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
        setPreferredSize(new Dimension(toolkit.getScreenSize().width, toolkit.getScreenSize().height));
        setLayout(new BorderLayout());
        setLocation(0, 0);

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
