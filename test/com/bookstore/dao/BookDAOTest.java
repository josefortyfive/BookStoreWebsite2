package com.bookstore.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {

	private static BookDAO bookDAO;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpClass();
		bookDAO = new BookDAO(entityManager);
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateBook() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(1);
		newBook.setCategory(category);

		newBook.setTitle("Introduction to Algorithms, 7 edition");
		newBook.setAuthor("Thomas H. Cormen");
		newBook.setDescription("ome books on algorithms are rigorous but incomplete; others cover masses of material but lack rigor. Introduction to Algorithms uniquely combines rigor and comprehensiveness. ");
		newBook.setPrice(107.95f);
		newBook.setIsbn("026204630X");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("07/08/2014");
		newBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Introduction to Algorithm.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageByte);

		Book createBook = bookDAO.create(newBook);

		assertTrue(createBook.getBookId() > 0);

	}

	@Test
	public void testUpdateBook() throws IOException, ParseException {
		Book existBook = new Book();
		existBook.setBookId(1);
		Category category = new Category("Advanced Java");
		category.setCategoryId(1);
		existBook.setCategory(category);

		existBook.setTitle("Introduction to Algorithms, fourth edition");
		existBook.setAuthor("John H. Cormen");
		existBook.setDescription("Introduction to Algorithms uniquely combines rigor and comprehensiveness. ");
		existBook.setPrice(107.95f);
		existBook.setIsbn("026204630X");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("07/08/2014");
		existBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageByte);

		bookDAO.update(existBook);

		assertEquals(existBook.getTitle(), "Effective Java (2nd Edition)");

	}

	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 2;
		bookDAO.delete(bookId);
		assertTrue(true);
	}

	@Test
	public void testGetFound() {
		Integer bookId = 1;
		Book book = bookDAO.get(bookId);

		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());

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
	public void testFindByTitleExist() {
		String title = "Java";
		List<Book> result = bookDAO.search(title);
		
		for(Book book : result) {
			System.out.println(book.getTitle() + " - " +book.getAuthor());
		}
		assertEquals(7, result.size());
	}
	
	@Test
	public void testFindByAuthor() {
		String author = "Joshua";
		List<Book> result = bookDAO.search(author);
		
		for(Book book : result) {
			System.out.println(book.getAuthor());
		}
		assertEquals(1, result.size());
	
	}
	
	@Test
	public void testFindByDescription() {
		String description = "Java EE";
		List<Book> result = bookDAO.search(description);
		
		for(Book book : result) {
			System.out.println(book.getDescription());
		}
		assertEquals(1, result.size());
	
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDAO.listNewBook();
		
		for(Book book : listNewBooks) {
			
			System.out.println(book.getTitle() + " "+ book.getPublishDate());
		}
		
		assertEquals(4, listNewBooks.size());
	}
	
	
	
	@Test
	public void testListByCategory() {
		int categoryId = 1;
		
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		
		System.out.println(listBooks.toString());
		
		assertTrue(listBooks.size() > 0);
	}
	@Test
	public void testCountAll() {
		long totalBook = bookDAO.count();
		assertEquals(4, totalBook);
	}
}