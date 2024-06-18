package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
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

class BookDAOTest extends BaseDAOTest {

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
		
		newBook.setTitle("Introduction to Algorithms, fourth edition 4");
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
		existBook.setBookId(5);
		Category category = new Category("Advanced Java");
		category.setCategoryId(1);
		existBook.setCategory(category);
		
		existBook.setTitle("Introduction to Algorithms, third edition");
		existBook.setAuthor("John H. Cormen");
		existBook.setDescription("Introduction to Algorithms uniquely combines rigor and comprehensiveness. ");
		existBook.setPrice(107.95f);
		existBook.setIsbn("026204630X");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("07/08/2014");
		existBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\Edmar\\Downloads\\dummy-data-books\\books\\Introduction to Algorithm.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageByte);
		
		Book updatedBook = bookDAO.create(existBook);
		
		assertEquals(existBook.getTitle(), "Introduction to Algorithms, third edition");
		
	}

}
