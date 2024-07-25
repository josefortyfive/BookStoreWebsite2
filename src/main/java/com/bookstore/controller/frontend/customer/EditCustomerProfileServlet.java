package com.bookstore.controller.frontend.customer;

import com.bookstore.service.CustomerServices;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit_customer")
public class EditCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCustomerProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerServices = new CustomerServices(request, response);
		customerServices.showCustomerProfileEditForm();
		
	}

}
