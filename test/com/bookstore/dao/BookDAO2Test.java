package com.bookstore.dao;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAO2Test extends BaseDAOTest {
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpClass2();
		bookDAO = new BookDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownClass2();
	}

	@Test
	public void testCreateBook() throws IOException, ParseException {
		Book newBook = new Book();
		Category category = new Category("Advanced Java");
		category.setCategoryId(2);
		newBook.setCategory(category);
		newBook.setTitle("Effective Java(3rd Edition)");
		newBook.setAuthor("Joashua Bloch");
		newBook.setDescription("Introduction to Algorithm in Java");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2009");
		newBook.setPublishDate(publishDate);
		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Introduction to Algorithm.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageByte);
		Book createBook = bookDAO.create(newBook);
		assertTrue(createBook.getBookId() > 0);
	}

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(1);
		
		Category category = new Category("Core Java");
		category.setCategoryId(1);
		existBook.setCategory(category);
		existBook.setTitle("Effective Java(5th Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("new Coverage of generics, enum, annotations, autoboxing");
		existBook.setPrice(40.99f);
		existBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Introduction to Algorithm.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageByte);
		
		Book updatedBook = bookDAO.update(existBook);
		assertEquals(existBook.getTitle(), "Effective Java(3rd Edition)");
	}

	@Test(expected = Exception.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDAO.delete(bookId);
	}

	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 1;
		bookDAO.delete(bookId);
		assertTrue(true);
	}

	@Test
	public void testGetBookFail() {
		Integer bookId = 1;
		Book book = bookDAO.get(bookId);
		assertNull(book);
	}

	@Test
	public void testGetBookFound() {
		Integer bookId = 2;
		Book book = bookDAO.get(bookId);
		assertNotNull(book);
	}

	@Test
	public void testListBookAll() {
		List<Book> listBook = bookDAO.listAll();
		for (Book book : listBook) {
			System.out.println(book.getTitle());
		}
		assertTrue(listBook.size() > 0);
	}

	
	  @Test 
	  public void testFindByTitleNotExist() { String title = "Thinking Java";
	  Book book = bookDAO.findByTitle(title); assertNull(book); }
	  
	  @Test 
	  public void testFindByTitle() { 
		  String title = "Effective Java(3rd Edition)"; 
		  Book book = bookDAO.findByTitle(title); 
		  System.out.println(book.getTitle());
		  assertNotNull(book); 
	  }
	 

	@Test
	public void testCountAll() {
		long totalBook = bookDAO.count();
		assertEquals(1, totalBook);
	}
}