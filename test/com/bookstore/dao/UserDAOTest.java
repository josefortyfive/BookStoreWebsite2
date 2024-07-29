package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.bookstore.entity.Users;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;


public class UserDAOTest {
	
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupClass() throws Exception {

		userDAO = new UserDAO(); 
	}
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("joson.tatum@gmail.com");
		user1.setFullName("Jason Tatum");
		user1.setPassword("tatum1");

		user1 = userDAO.create(user1);
	
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieledNotSet() {
		Users user1 = new Users();

		user1 = userDAO.create(user1);
				
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user1 = new Users();
		user1.setUserId(11);
		user1.setEmail("marlyn@outlook.com");
		user1.setFullName("Marlene Johson");
		user1.setPassword("guntea");
		
		user1 = userDAO.update(user1); 
		String expected = "guntea";
		String actual = user1.getPassword();
		
		assertEquals(expected, actual);
	}

	@DisplayName("Search user using userId")
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	@DisplayName("Search user using userId")
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 33;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = RollbackException.class)
	public void testDeleteNonExistUser() {
		Integer userId = 6;
		userDAO.delete(userId);
		
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
	
		for(Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size()> 0);
	}
	@Test
	public void testCount() {
		long totalUser = userDAO.count();
		assertEquals(19, totalUser);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "john3@gmail.com";
		Users user = userDAO.findByEmail(email);
	
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "carol@hotmail.com";
		String password = "password1234";
		boolean checkLogin = userDAO.checkLogin(email, password);
		
		assertTrue(checkLogin);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "car2ol@hotmail.com";
		String password = "pass2word1234";
		boolean checkLogin = userDAO.checkLogin(email, password);
		
		assertFalse(checkLogin);
	}
	
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}
}
