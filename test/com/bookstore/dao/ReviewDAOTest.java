package com.bookstore.dao;

import com.bookstore.entity.Review;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReviewDAOTest {

	private static ReviewDAO reviewDAO;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		reviewDAO = new ReviewDAO(); 
		
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(4);
		
		Customer customer = new Customer();
		customer.setCustomerId(5);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("Nice Book");
		review.setRating(4);
		review.setComment("Cool Book");
		
		Review savedReview = reviewDAO.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	
	}
	
	@Test
	public void testCreateReview02() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(4);
		
		Customer customer = new Customer();
		customer.setCustomerId(5);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("Nice Book");
		review.setRating(4);
		review.setComment("Cool Book");
		
		Review savedReview = reviewDAO.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	
	}
	
	@Test
	public void testGet() {
		Integer reviewId = 5;
		Review review = reviewDAO.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testGetNotFound() {
		Integer reviewId = 1;
		Review review = reviewDAO.get(reviewId);
		
		assertNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId = 5;
		Review review = reviewDAO.get(reviewId);
		review.setHeadline("Excellent book!");
		
		Review updateReview = reviewDAO.update(review);
		
		assertEquals(review.getHeadline(), updateReview.getHeadline());
	}
	
	@Test
	public void testDeleteReview() {
		Integer reviewId = 5;
		
		reviewDAO.delete(reviewId);
		
	}
	
	@Test
	public void testListAll() {
		List<Review> listReview = reviewDAO.listAll();
		
		for(Review review : listReview) {
				System.out.println("->" +review.getReviewId() + " " +review.getBook().getTitle() + " - " +review.getCustomer().getFullname()) ;
		}
		assertTrue(listReview.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalReviews = reviewDAO.count();
		
		System.out.println("Number of reviews " +totalReviews);
		
		assertTrue(totalReviews > 0 );
	}
	
	@Test
	public void testfindByCustomerAndBookNotFound() {
		Integer customerId = 100;
		Integer bookId = 99;
		
		Review result = reviewDAO.findByCustomerAndBook(customerId, bookId);
		
		assertNull(result);
	}
	
	
	@Test
	public void testFindByCustomerAndBookFound() {
		Integer customerId = 2;
		Integer bookId = 4;
		
		Review result = reviewDAO.findByCustomerAndBook(customerId, bookId);
		
		assertNotNull(result);
	}
	
	
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}



}
