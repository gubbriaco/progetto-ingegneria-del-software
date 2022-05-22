package gui.decorator;

public abstract class FinestraDecorator implements FinestraServiceIF {
	
	
	private FinestraServiceIF finestraServiceIf;
	
	public FinestraDecorator(FinestraServiceIF finestraServiceIf) {
		this.finestraServiceIf = finestraServiceIf;
	}
	
	
	@Override public void decorate() {
		finestraServiceIf.decorate();
	}
	

}
