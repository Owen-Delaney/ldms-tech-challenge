package owen.delaney.ldms.tech.challenge.exception;

public class LoanNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6511884932745889348L;

	public LoanNotFoundException(Long id) {
		super("Could not find a loan with the ID " + id);
	}
}
