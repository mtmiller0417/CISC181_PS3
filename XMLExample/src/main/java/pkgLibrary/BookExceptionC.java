package pkgLibrary;

public class BookExceptionC extends Exception 
{
	@Override
	public String getMessage()
	{
		return "This Book already exists in the Catalog!";
	}
}
