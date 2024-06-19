package com.bookstore.dao;

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

<<<<<<< HEAD
		newBook.setTitle("Introduction to Algorithms, 8 edition");
=======
		newBook.setTitle("Introduction to Algorithms, 7 edition");
>>>>>>> 7065fd9b6ef236671281d37f097ac57b87d7cadb
		newBook.setAuthor("Thomas H. Cormen");
		newBook.setDescription(
				"ome books on algorithms are rigorous but incomplete; others cover masses of material but lack rigor. Introduction to Algorithms uniquely combines rigor and comprehensiveness. ");
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
<<<<<<< HEAD
		existBook.setBookId(1);
		Category category = new Category("Advanced Java");
		category.setCategoryId(2);
		existBook.setCategory(category);

		existBook.setTitle("Effective Java (2nd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? ");
		existBook.setPrice(38.87f);
		existBook.setIsbn("0321356683");
=======
		existBook.setBookId(5);
		Category category = new Category("Advanced Java");
		category.setCategoryId(1);
		existBook.setCategory(category);

		existBook.setTitle("Introduction to Algorithms, fourth edition");
		existBook.setAuthor("John H. Cormen");
		existBook.setDescription("Introduction to Algorithms uniquely combines rigor and comprehensiveness. ");
		existBook.setPrice(107.95f);
		existBook.setIsbn("026204630X");
>>>>>>> 7065fd9b6ef236671281d37f097ac57b87d7cadb

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("07/08/2014");
		existBook.setPublishDate(publishDate);

<<<<<<< HEAD
		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Effective Java.jpg";
=======
		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Introduction to Algorithm.jpg";
>>>>>>> 7065fd9b6ef236671281d37f097ac57b87d7cadb
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageByte);

		bookDAO.update(existBook);

<<<<<<< HEAD
		assertEquals(existBook.getTitle(), "Effective Java (2nd Edition)");
=======
		assertEquals(existBook.getTitle(), "Introduction to Algorithms, fourth edition");
>>>>>>> 7065fd9b6ef236671281d37f097ac57b87d7cadb

	}

	@Test
	public void testDeleteBookSuccess() {
<<<<<<< HEAD
		Integer bookId = 2;
=======
		Integer bookId = 3;
>>>>>>> 7065fd9b6ef236671281d37f097ac57b87d7cadb
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
	public void testCountAll() {
		long totalBook = bookDAO.count();
		assertEquals(4, totalBook);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 7065fd9b6ef236671281d37f097ac57b87d7cadb