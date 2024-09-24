package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;

class ShoppingCartTest {

	private static ShoppingCart cart;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 cart = new ShoppingCart();

			Book book = new Book(1);
			
			book.setPrice(10);
			
			cart.addItem(book);
			cart.addItem(book);
	}


	@Test
	public void testAddItem() {
		
		Map<Book, Integer> items = cart.getItems();
		Integer quantity = items.get(new Book(1));
	
		assertEquals(2, quantity);
	}
	
	@Test
	public void testRemoveItem() {
	
		cart.removeItem(new Book(1));
		
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
	
		Book book2 = new Book(2);
		cart.addItem(new Book(1));
		cart.removeItem(new Book(1));
		
		assertNull(cart.getItems().get(book2));
	}
	
	@Test
	public void testGetTotalQuantity() {
		Book book3 = new Book(3);
		cart.addItem(book3);
		cart.addItem(book3);
		cart.addItem(book3);
		
		assertEquals(5, cart.getTotalQuantity());	
		
	}
	
	@Test
	public void testGetTotalAmount1() {
		ShoppingCart cart = new ShoppingCart();
		
		assertEquals(0.0f, cart.getTotalAmount() , 0.0f);
	}

	@Test
	public void testGetTotalAmount2() {
		assertEquals(20.0f, cart.getTotalAmount(), 0.0f);
	}

	@Test
	public void testClear() {
		cart.clear();
		
		assertEquals(0, cart.getTotalQuantity());
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
