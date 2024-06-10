package com.bookstore.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class JpaDAO<E> {

	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public E create(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();

		return entity;
	}

	public E update(E entity) {
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	public E find(Class<E> type, Object id) {

		E entity = entityManager.find(type, id);

		if (entity != null) {
			entityManager.refresh(entity);
		}

		return entity;
	}

	public void delete(Class<E> type, Object id) {
		entityManager.getTransaction().begin();

		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<E> findWithNamedQuery(String queryName) {

		Query query = entityManager.createNamedQuery(queryName);

		return query.getResultList();
	}

	public List<E> findWithNamedQuery(String queryName, int firstResult, int maxResult) {

		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		List<E> result = query.getResultList();

		entityManager.close();

		return result;
	}

	public List<Object[]> findWithNamedQueryObjects(String queryName, int firstResult, int maxResult) {

		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		List<Object[]> result = query.getResultList();

		entityManager.close();

		return result;
	}

	public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		Query query = entityManager.createNamedQuery(queryName);

		query.setParameter(paramName, paramValue);

	
		List<E> result = query.getResultList();

		entityManager.close();

		return result;
	}

	public long countWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (long) query.getSingleResult();
	}

}
