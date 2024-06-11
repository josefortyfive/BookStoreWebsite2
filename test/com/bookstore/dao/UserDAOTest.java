package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.bookstore.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;


public class UserDAOTest extends BaseDAOTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupClass() throws Exception {

		BaseDAOTest.setUpClass();
		userDAO = new UserDAO(entityManager);
	}
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("david.beckham@gmail.com");
		user1.setFullName("Tommy Johnson");
		user1.setPassword("1234pass");

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
		user1.setFullName("Marlene Joe");
		user1.setPassword("gone1");
		
		user1 = userDAO.update(user1); 
		String expected = "gone1";
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
		Integer userId = 6;
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
		assertEquals(10, totalUser);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "john3@gmail.com";
		Users user = userDAO.findByEmail(email);
	
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
