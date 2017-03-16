package pkgLibrary;

public class BookException extends Exception {

	@Override
	public String getMessage()
	{
		return "No book with that matching ID were found!";
	}
		
}
