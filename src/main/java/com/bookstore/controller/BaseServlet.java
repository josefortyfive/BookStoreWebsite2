package com.bookstore.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	@Override
	public void init() throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite2");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void destroy() {
		entityManager.close();
		entityManagerFactory.close();
	}

	
}
