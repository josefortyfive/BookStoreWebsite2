package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		customerDAO = new CustomerDAO();

	}

	public void listCustomer() throws ServletException, IOException {
		listCustomer(null);
	}
	public void listCustomer(String message) throws ServletException, IOException {
		
		List<Customer> listCustomers = customerDAO.listAll();
		request.setAttribute("listCustomers", listCustomers);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "customer_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		
		if(existCustomer != null) {
			String message = "Could not create account, " +email + " already exist";
			listCustomer(message); 
			
		} else {
			String fullname = request.getParameter("fullname");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String zipcode = request.getParameter("zipcode");
			String country = request.getParameter("country");
			
			Customer newCustomer = new Customer();
			newCustomer.setEmail(email);
			newCustomer.setFullname(fullname);
			newCustomer.setPassword(password);
			newCustomer.setPhone(phone);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setZipcode(zipcode);
			newCustomer.setCountry(country);
			
			customerDAO.create(newCustomer);
			
			String message = "New customer has been created";
			listCustomer(message);
		}
	}

	public void editCustomer() throws ServletException, IOException  {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		
		String editPage = "customer_form.jsp";

		request.setAttribute("customer", customer);
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException{
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		
		Customer existCustomer = customerDAO.findByEmail(email);
		
		String message = null; 
		if(existCustomer != null && existCustomer.getCustomerId() != customerId ) {
			message = "Could not update the detail! " + email + " email already in use."; 
		
		} else {
			
			String fullname = request.getParameter("fullname");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String zipcode = request.getParameter("zipcode");
			String country = request.getParameter("country");
			
			Customer customerById = customerDAO.get(customerId);
			customerById.setCustomerId(customerId);
			customerById.setEmail(email);
			customerById.setFullname(fullname);
			customerById.setPassword(password);
			customerById.setPhone(phone);
			customerById.setAddress(address);
			customerById.setCity(city);
			customerById.setZipcode(zipcode);
			customerById.setCountry(country);
			
			customerDAO.update(customerById);
			
			message = "Customer's details has been updated";
			
		}
		listCustomer(message);
		
	}

	public void deleteCustomer() throws ServletException, IOException{
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		
		Customer customer = customerDAO.get(customerId);
		
		String message = "Customer has been deleted successfully";
		
		if(customer == null) {
			message = "Could not find customer with Id " +customerId + " customer might have already been delete";
			request.setAttribute("message", message);
		} else {
			
			customerDAO.delete(customerId);
			listCustomer(message);
		}
		
	}
}
