package in.rba.main.exceptionHandlers;

public class UsernameNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameNotFoundException() {}
	
	public UsernameNotFoundException(String msg) {super(msg);}

}