package com.bookstore.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookRatingTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}


	@Test
	public void testAverageRating() {
		Book book = new Book();
		Set<Review> reviews = new HashSet();
		
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		book.setReviews(reviews);
	
		float averageRating = book.getAverageRating();
		
		assertEquals(averageRating, 5.0);
	}
	
	
	@Test
	public void testAverageRating2() {
		Book book = new Book();

		float averageRating = book.getAverageRating();
		
		assertEquals(0.0, averageRating, 0.0);
	}
	
	
	@Test
	public void testAverageRating3() {
		Book book = new Book();
		Set<Review> reviews = new HashSet();
		
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		Review review2 = new Review();
		review2.setRating(4);
		reviews.add(review2);
		
		
		Review review3 = new Review();
		review3.setRating(3);
		reviews.add(review3);
		
		
		book.setReviews(reviews);
	
		float averageRating = book.getAverageRating();
		
		assertEquals(averageRating, 5.0);
	}
	
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}


}
