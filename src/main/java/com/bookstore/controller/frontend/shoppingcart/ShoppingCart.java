package com.bookstore.controller.frontend.shoppingcart;

import java.util.Map;

import com.bookstore.entity.Book;

import java.util.HashMap;

public class ShoppingCart {
	private Map<Book, Integer> cart = new HashMap<>();

	public void addItem(Book book) {
		if(cart.containsKey(book)) {
			Integer quantity = cart.get(book) + 1;
			cart.put(book, quantity);
			
		} else {
			cart.put(book, 1);
			
		}
	}
	
	public Map<Book, Integer> getItems(){
		return this.cart;
	}
}
