package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

import jakarta.persistence.EntityManager;

public class CategoryServices {
	private EntityManager entityManager;
	private CategoryDAO categoryDAO;

	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
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

		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response);

	}

	public void createCategory() throws IOException, ServletException {

		String categoryName = request.getParameter("name");

		Category existCategory = categoryDAO.findByName(categoryName);

		if (existCategory != null) {
			String message = "Could not create category " + categoryName + ", category name already exist";
			request.setAttribute("message", message);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(categoryName);
			categoryDAO.create(newCategory);
			listCategory("Category created Successfully");
		}
	}

	public void editCategory() throws IOException, ServletException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);

		request.setAttribute("category", category);

		String editPage = "category_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws IOException, ServletException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");

		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);

		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Could not update the category " + categoryName + " already exist";
			request.setAttribute("message", message);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {

			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category sucessfully been updated";
			listCategory(message);

		}

	}
	
	public void deleteCategory() throws IOException, ServletException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		
		Category category = categoryDAO.get(categoryId);
		
		String message = "Category successfully been deleted";
		
		if(category == null) {
			message = "Could not find the category with the Id" +categoryId;
			request.setAttribute("message", message);
		} else {
			categoryDAO.delete(categoryId);
			listCategory(message);
		}
		
	}
}
