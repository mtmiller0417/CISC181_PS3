package pkgEmpty;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;
import pkgLibrary.BookExceptionB;
import pkgLibrary.BookExceptionC;
import pkgLibrary.Catalog;

public class BookTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test (expected = BookExceptionB.class)//Exception is expected in this case
	public void NegativeGetBookTest()throws BookExceptionB
	{
		Catalog c = ReadXMLFile();		
		c.getBook("bk001");
	}
	
	@Test //No Exception is expected in this example
	public void PositiveGetBookTest()throws BookExceptionB
	{	
		Catalog c = ReadXMLFile();	
		c.getBook("bk111");
	}
	
	@Test (expected = BookExceptionC.class)//Exception is expected in this case
	public void NegativeAddBookTest()throws BookExceptionC, ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
		Date d = sdf.parse("2000/12/16");
		Book b = new Book("bk102", "Ralls, Kim", "Midnight Rain", 
				"Fantasy", 6.55, d, "A former architect battles corporate zombies, "
						+ "an evil sorceress, and her own childhood to become "
						+ "queen of the world."	);
		Catalog c = ReadXMLFile();
		Catalog.addBook(c, b);
	}
	
	@Test //No Exception is expected from this example
	public void PositiveAddBookTest()throws BookExceptionC, ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");
		Date d = sdf.parse("2012/12/21");
		Book b = new Book("bk113", "Matthew Miller", "What is Science?", 
				"Educational", 19.99, d, "Description of what science is."	);
		Catalog c = ReadXMLFile();
		Catalog.addBook(c, b);
	}

	

	
	
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
	
}
