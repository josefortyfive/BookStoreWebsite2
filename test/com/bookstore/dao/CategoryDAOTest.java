package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import com.bookstore.entity.Category;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;


class CategoryDAOTest {

	private static CategoryDAO categoryDAO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		categoryDAO = new CategoryDAO();
	} 

 

	@DisplayName("Create Category Test")
	@Test
	public void testCreateCategory() {
		Category category = new Category();
		category.setName("Science");
		
		category = categoryDAO.create(category);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}
	
	@DisplayName("Update Category Test")
	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Physics");
		cat.setCategoryId(17);
		
		Category category = categoryDAO.update(cat);
		
		assertEquals(cat.getName(), category.getName());
		
	}
	
	@DisplayName("Find Category")
	@Test
	public void testFindCategory() {
		Integer catId = 2;
		Category cat = categoryDAO.get(catId);
		
		System.out.println("Category: "+cat.getName());
		assertNotNull(cat);
	}
	
	@DisplayName(value = "Not Found Category")
	@Test
	public void testNotFoundCategory() {
		Integer catId = 25;
		Category cat = categoryDAO.get(catId);
		
		System.out.println("Category: "+cat.getName());
		assertNull(cat);
	}
	
	@DisplayName("Delete Category")
	@Test
	public void testDeleteCategory() {
		Integer catId = 7;
		categoryDAO.delete(catId);
	}
	
	@DisplayName("Delete Non Existing Category")
	@Test
	public void testDeleteNonExistingCategory() {
		Integer catId = 7;
		Category category = categoryDAO.get(catId);
	
		assertNull(category);
	}
	
	
	@DisplayName("Find List of Category")
	@Test
	public void testFindListOfCategory() {
		List<Category> listCategory = categoryDAO.listAll();
		
		for(Category category : listCategory) {
			System.out.println("Category Name: "+category.getName());
		}
		assertNotNull(listCategory.size() > 0);
	}
	
	
	@Test
	public void testCount() {
		long totalCategory = categoryDAO.count();
		System.out.println("Number of listed category "+totalCategory);
		assertEquals(9, totalCategory);
	}
	
	@DisplayName("Find name of the category")
	@Test
	public void testFindByName() {
		String name = "Core Java";
		
		Category category = categoryDAO.findByName(name);
		
		System.out.println(category.getName());
		
		assertNotNull(category);
		
	}
	@DisplayName("Find name of the category")
	@Test
	public void testFindByNameNull() {
		String name = "Job";
		
		Category category = categoryDAO.findByName(name);
		
		
		assertNull(category);
		
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}
}
