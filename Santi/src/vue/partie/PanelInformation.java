package vue.partie;

import java.awt.Container;
import java.io.PrintStream;
import java.util.Observable;

import javax.swing.JTextArea;

import vue.AbstractPanel;
import vue.components.JTextAreaOutputStream;

public class PanelInformation extends AbstractPanel{

	public PanelInformation(Container parent) {
		super(parent);
	}

	 public void initComponent() {
		JTextArea console = new JTextArea();
		JTextAreaOutputStream outStream = new JTextAreaOutputStream(console);
		JTextAreaOutputStream errStream = new JTextAreaOutputStream(console);
		System.setOut(new PrintStream(outStream));
		System.setErr(new PrintStream(errStream));
	 }
	
	@Override
	public void update(Observable arg0, Object arg1) {
	}

}
