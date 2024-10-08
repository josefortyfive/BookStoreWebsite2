package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

			Customer newCustomer = new Customer();
			updateCustomerFieldsForm(newCustomer);
			
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
			

			Customer customerById = customerDAO.get(customerId);
			updateCustomerFieldsForm(customerById);
			
			customerById.setCustomerId(customerId);
			
			
			customerDAO.update(customerById);
			
			message = "Customer's details has been updated";
			
		}
		listCustomer(message);
		
	}

	private void updateCustomerFieldsForm(Customer customer ) {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		
		if(email != null&& !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFullname(fullname);
		
		if(password != null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipcode);
		customer.setCountry(country);
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

	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		String message = null;
		if(existCustomer != null) {
			message = "Could not register account, " +email + " already exist";
			
		} else {

			Customer newCustomer = new Customer();
			updateCustomerFieldsForm(newCustomer);
			customerDAO.create(newCustomer);
			
			message = "You have registered successfully"
					+ "<a href='admin/login.jsp'>Click Here </a> to Signin";
		}
		
		String messagePage = "frontend/message.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
		
	}

	public void showLogin() throws ServletException, IOException {
		
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if (customer == null) {
			String message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showLogin();
			
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			
			Object objRedirectURL = session.getAttribute("redirectURL");
			
			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showCustomerProfile();
			}
		}
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
		
		
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
		
	}

	public void updateCustomerProfile() throws ServletException, IOException{
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();
	}
}


