package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import pkgMain.XMLReader;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
	
	
	public int getId() {
		return id;
	}
	
/////////////////////////////////////////////////////////////////////////////
public Book getBook(String id) throws BookExceptionB
{
	Catalog c = ReadXMLFile();
	
	for(Book b: c.getBooks())
	{
		if(id.equals(b.getId()))
		{
			return b;
		}
	}
	throw new BookExceptionB();
}
public static void addBook(Catalog c, Book InputBook)throws BookExceptionC
{
	int i = c.getId();
	for(Book b: c.getBooks())
	{
		if((InputBook.getId()).equals(b.getId()))
			{
				throw new BookExceptionC();
			}
	}
	ArrayList<Book> newCatalog = c.getBooks();

	newCatalog.add(InputBook);

	c.setBooks(newCatalog);
	
	//Write to file?
	/*
	 * WriteXMLFile(c);
	 */

}
///////////////////////////////////////////////////////////////////////////	
//*************************************************************************************************//

private static Catalog ReadXMLFile() {

	Catalog cat = null;

	String basePath = new File("").getAbsolutePath();
	basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
	File file = new File(basePath);

	System.out.println(file.getAbsolutePath());
	try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
		System.out.println(cat);
	} catch (JAXBException e) {
		e.printStackTrace();
	}

	return cat;

}

private static void WriteXMLFile(Catalog cat) {
	try {

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(cat, file);
		jaxbMarshaller.marshal(cat, System.out);

	} catch (JAXBException e) {
		e.printStackTrace();
	}
}
//******************************************************************************************************//
	

	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	

	
	


	
	
	
	
}
