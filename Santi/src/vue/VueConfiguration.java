package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.font.FontRenderContext;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.Santiago;

import model.Plateau;

public class VueConfiguration extends JFrame implements Observer {
    
    private JPanel contentPane; 
    private class PanelConfiguration extends JPanel{
        
        
        public PanelConfiguration(){
            this.setLayout(new BorderLayout());
            JPanel panelField = new JPanel(new FlowLayout());
            this.add(panelField, BorderLayout.NORTH);
            JPanel choixPanel = new JPanel(new FlowLayout());
            this.add(choixPanel, BorderLayout.CENTER);
            
            JLabel label = new JLabel("Santiago endiablé !!");
            JButton boutonDemarrer = new JButton("Demarrer");
            JButton boutonConfiguration = new JButton("Configurer");
            JButton boutonReglage = new JButton("Reglages");
            
            panelField.add(label);
            choixPanel.add(boutonDemarrer);
            choixPanel.add(boutonConfiguration);
            choixPanel.add(boutonReglage);
            
            //On initialise le JLabel
            Font police = new Font("DS-digital", Font.TYPE1_FONT, 30);
            label.setFont(police);
            //this.label.setHorizontalAlignment(JLabel.CENTER);
            
            //On initialise les JButton
            boutonDemarrer.setFont(police);
            boutonDemarrer.setSize(200, 50);
            boutonConfiguration.setFont(police);
            boutonConfiguration.setSize(200, 50);
            boutonReglage.setFont(police);
            boutonReglage.setSize(200, 50);
        }
    };
    
    private Santiago santiago;
    
    public VueConfiguration(Santiago santiago){
        super();
        this.contentPane = new PanelConfiguration();
        this.setContentPane(this.contentPane);
        this.contentPane.setSize(1200, 800);
        
        //On initialise la JFrame
        //this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0,0);
        this.setResizable(true);
        //this.setSize(1200, 800);
        this.setPreferredSize(this.contentPane.getSize());
        
        // On configure Santiago
        this.santiago = santiago;
        
        // On place un écouteur sur le plateau
        this.santiago.addObserver(this);
       
        //this.setLayout(new BorderLayout());
        
        this.setTitle("Santiago");
        this.pack();
        
        //On ajoute le JLabel à la Frame
        //this.getContentPane().add(this.label, BorderLayout.CENTER);
        //this.getContentPane().add(this.boutonDemarrer, BorderLayout.CENTER);
        //this.getContentPane().add(this.boutonConfiguration, BorderLayout.CENTER);
        //this.getContentPane().add(this.boutonReglage, BorderLayout.CENTER);
        this.setVisible(true);
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
        VueConfiguration fen = new VueConfiguration(new Santiago());
    }
}
