package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.BookOrder;

public class OrderServices {
	
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}



	public void listAllOrder() throws ServletException, IOException {
			listAllOrder(null);
	}
	
	public void listAllOrder(String message)throws ServletException, IOException  {
		List<BookOrder> listOrder = orderDAO.listAll();
	
		request.setAttribute("listOrder", listOrder);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}



	public void viewOrderDetailForAdmin()  throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		BookOrder order = orderDAO.get(orderId);
		request.setAttribute("order", order);

		String detailPage = "order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
		
	}

}
