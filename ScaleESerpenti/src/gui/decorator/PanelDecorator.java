package gui.decorator;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class PanelDecorator extends JPanel {
	
	protected final JPanel toDecorate;
	
	public PanelDecorator(JPanel toDecorate) {
		this.toDecorate = toDecorate;
	}
	
	public abstract JPanel decorate();

}
