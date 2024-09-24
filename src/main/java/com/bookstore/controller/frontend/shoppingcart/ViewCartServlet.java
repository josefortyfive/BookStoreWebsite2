package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;

@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ViewCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		if(cartObject == null) {
			ShoppingCart shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		Book book = new Book();
		book.setTitle("Effective Java (3rd Edition)");
		book.setPrice(30);
		
		ShoppingCart shoppingCart = (ShoppingCart)request.getSession().getAttribute("cart");
		//shoppingCart.addItem(book);
		
		String cartPage = "frontend/shopping_cart.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(cartPage);
		dispatcher.forward(request, response);
	}
}
