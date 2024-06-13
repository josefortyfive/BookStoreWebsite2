package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CategoryServices {
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CategoryServices(EntityManager entityManager, HttpServletRequest request,HttpServletResponse response ) {
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO(entityManager);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
	
		requestDispatcher.forward(request, response);


	}
	
	public void createCategory() throws IOException, ServletException {
		
		String categoryName = request.getParameter("name");
		
		Category existCategory = categoryDAO.findByName(categoryName);
		
		if(existCategory != null) {
			String message = "Could not create category " +categoryName+ ", category name already exist";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(categoryName);
			categoryDAO.create(newCategory);
			listCategory("Category created Successfully");
		}
	}
}
