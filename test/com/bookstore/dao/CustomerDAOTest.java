package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Customer;

class CustomerDAOTest {

	private static CustomerDAO customerDAO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	} 

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("tomas@gmail.com");
		customer.setFullname("Thomas Jefferson");
		customer.setCity("Monticello");
		customer.setCountry("United States");
		customer.setAddress("100 North Avenue");
		customer.setPassword("president");
		customer.setPhone("18001900");
		customer.setZipcode("10000");
		
		Customer savedCustomer = customerDAO.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testUpdateCustomer() {
		
		Customer customer = customerDAO.get(1);
		
		customer.setFullname("Thomas Jefferson");
	
		customer = customerDAO.update(customer);
		
		String actual = "Tomas Jefferson";
		String expected = customer.getFullname();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void testGetCustomer() {
		int customerId = 2;
		assertNotNull(customerDAO.get(customerId));
		
		
	}
	@Test 
	public void testDeleteCustomer() {
		Integer customerId = 1;
		customerDAO.delete(customerId);
		
		Customer customer = customerDAO.get(customerId);
		
		assertNull(customer);
		
	}
	
	@Test
	public void testCustomerListAll() {
		List<Customer> listCustomer = customerDAO.listAll();
		
		for(Customer customer : listCustomer ) {
			System.out.println(customer.getFullname()+" "+customer.getEmail());
			
		}
		
		assertTrue(listCustomer.size() > 0);
	}
	
	@Test
	public void testCountCustomer() {
		long customerCount = customerDAO.count();
		
		System.out.println("Number of customer " + customerCount);
		assertEquals(customerCount, 1);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "george@gmail.com";
		Customer customer = customerDAO.findByEmail(email);
		
		System.out.println("Email address: " +customer.getEmail());
		
		assertEquals(customer.getEmail(), email);
	}
	
	@Test
	public void testFindByEmailNotFound() {
		String email = "georg1e@gmail.com";
		Customer customer = customerDAO.findByEmail(email);
		
		assertNull(customer);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "georg1e@gmail.com";
		String password = "secret";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	
	@Test
	public void testCheckLoginFail() {
		String email = "georg1e2@gmail.com";
		String password = "secret";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNull(customer);
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}
}
