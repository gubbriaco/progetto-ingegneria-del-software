package app.exception;

@SuppressWarnings("serial")
public class FactoryException extends RuntimeException {
	
	public FactoryException() {
		super("Factory configuration error.");
	}
	
}
