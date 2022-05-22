package gui.decorator.concrete;

import gui.decorator.FinestraDecorator;
import gui.decorator.FinestraServiceIF;

public class ScrollDecorator extends FinestraDecorator {

	public ScrollDecorator(FinestraServiceIF finestraServiceIf) {
		super(finestraServiceIf);
		
	}
	
	
	@Override public void decorate() {
		super.decorate();
		decorateWithScroll();
		//TODO decora la finestra con una JSCROLLBAR ->
		//-> per il Terminale o la legenda nella finestra principale
	}
	
	private void decorateWithScroll() {
		
	}

}
