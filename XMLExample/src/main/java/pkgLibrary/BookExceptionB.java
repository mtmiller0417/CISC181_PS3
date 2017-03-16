package pkgLibrary;

public class BookExceptionB extends Exception 
{
	@Override
	public String getMessage()
	{
		return "That book was not found in this catalog";
	}
}
