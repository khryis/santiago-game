package vue.partie;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.PrintStream;
import java.util.Observable;

import javax.swing.JTextArea;

import vue.AbstractPanel;
import vue.components.JTextAreaOutputStream;

public class PanelInformation extends AbstractPanel {

    public PanelInformation(Container parent) {
        super(parent);
    }

    @Override
    public void initComponent() {
        super.initComponent();

        setPreferredSize(new Dimension(homeDimension.width - 800, homeDimension.height - 600));
        setOpaque(false);
        ((FlowLayout) getLayout()).setHgap(0);
        ((FlowLayout) getLayout()).setVgap(0);

        JTextArea console = new JTextArea();
        console.setPreferredSize(new Dimension(homeDimension.width - 800, homeDimension.height - 600));
        JTextAreaOutputStream outStream = new JTextAreaOutputStream(console);
        JTextAreaOutputStream errStream = new JTextAreaOutputStream(console);
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));

        add(console);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
    }

}
