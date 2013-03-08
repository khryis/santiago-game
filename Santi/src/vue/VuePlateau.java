package vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Plateau;


public class VuePlateau extends JFrame implements Observer {
    
    private JLabel label = new JLabel();
    private Plateau plateau;
    
    public VuePlateau(Plateau plateau){
        //On initialise la JFrame
        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(600, 400);
        
        //on initialise le plateau
        this.plateau = plateau;
        
        // On place un écouteur sur le plateau
        this.plateau.addObserver(this);
        
        //On initialise le JLabel
        Font police = new Font("DS-digital", Font.TYPE1_FONT, 30);
        this.label.setFont(police);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        //On ajoute le JLabel à la Frame
        this.getContentPane().add(this.label, BorderLayout.CENTER);
        this.setVisible(true);
        this.label.setText("Bonjour tout le monde !!");
    }
    
    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        VuePlateau fen = new VuePlateau(new Plateau(0, 3));
    }
    
}
