package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object bookId) {
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	public Book findByTitle(String title) {
		List<Book> resultBook =super.findWithNamedQuery("Book.findByTitle", "title", title);
		
		if(!resultBook.isEmpty()) {
			return resultBook.get(0);
		}
		return null;
	}
	
	public List<Book> listByCategory(int categoryId){
		return super.findWithNamedQuery("Book.findByCategory", "catId",categoryId);
		
	}
	public List<Book> search(String keyword){
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}
	
	public List<Book> listNewBook(){
		 return super.findWithNamedQuery("Book.listNew", 0, 4);	
	}
	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}

	public long countByCategoryId(int categoryId) {
		return super.countWithNamedQuery("Book.countByCategory", "catId", categoryId);
	}
}