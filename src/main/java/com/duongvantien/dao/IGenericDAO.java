package com.duongvantien.dao;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<E, Id extends Serializable> extends Serializable {
	public E findById(Id id);

	public List<E> findAll();

	public List<E> findByExample(E exampleInstance, String[] excludeProperty);

	public int count(E exampleInstance, String[] excludeProperty, boolean isLike);

	public int count();

	public int count(Criterion... criterion);

	public E save(E entity);

	public void delete(E entity);

	public List<E> findByCriteria(Criterion... criterion);

}
