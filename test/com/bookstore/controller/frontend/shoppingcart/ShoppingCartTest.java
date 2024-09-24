package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;

class ShoppingCartTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}


	@Test
	public void testAddItem() {
		
		ShoppingCart cart = new ShoppingCart();
		
		Book book = new Book();
		book.setBookId(1);
	
		cart.addItem(book);
		cart.addItem(book);
		
		Map<Book, Integer> items = cart.getItems();
		Integer quantity = items.get(book);
	
		assertEquals(2, quantity);
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
