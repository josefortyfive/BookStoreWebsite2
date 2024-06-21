package com.bookstore.service;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jakarta.persistence.EntityManager;

public class BookServices {

	private EntityManager entityManager;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BookServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
	}

	public void listBook()throws ServletException, IOException {
		listBook(null);
	}
	public void listBook(String message) throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "book_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void showBookNewForm() throws ServletException, IOException {
		
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String listPage = "book_form.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}

	public void createBook()throws ServletException, IOException  {
		
		String title = request.getParameter("title");
		
		
		Book existBook = bookDAO.findByTitle(title);
		if(existBook != null) {
			String message = "Could not create the book '" +title+ "' the book already exist";
			listBook(message);
			return;
		}

		Book newBook = new Book();
		readBookFields(newBook);
		
		Book createBook = bookDAO.create(newBook);
		
		if(createBook.getBookId() > 0) {
			 String message = "A new Book has been successfully created!";
			 listBook(message);
		}
	}
	
	public void readBookFields(Book book) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch(ParseException ex) {
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy");
		}
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(publishDate);
		

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);
		
		book.setPrice(price);
		
		Part part = request.getPart("bookImage");
		
		if(part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int)size];
			
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			book.setImage(imageBytes);
		}
		
	}

	public void editBook() throws ServletException, IOException {
		
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);
		
		String editPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void update() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.get(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);
		
		if(!existBook.equals(bookByTitle)) {
			String message = "Could not update the book. Book Already exist";
			listBook(message);
			return;
		}
		
		
		readBookFields(existBook);
		bookDAO.update(existBook);
		
		String message = "Book has been updated successfully!";
		listBook(message);
	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(bookId);
		
		String message = "Book has been delete successfully";
		listBook(message);
		
	}

	public void listBookByCategory() throws ServletException, IOException {
		
		int categoryId = Integer.parseInt(request.getParameter("id"));
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		Category category = categoryDAO.get(categoryId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listBooks", listBooks);
		request.setAttribute("category", category);
		
		String listPage = "frontend/book_list_by_category.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void viewBookDetail()throws ServletException, IOException {
		
		int bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("book", book);
		
		String detailPage = "frontend/book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
		
	}
}
