package com.duongvantien.dao;

import com.duongvantien.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author customize toandk
 */
public abstract class GenericDAO<E, Id extends Serializable> extends  HibernateUtil{
	private Class<E> persistentClass;

	public Class<E> getPersistentClass() {
		return persistentClass;
	}

	public GenericDAO(Class<E> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void initDao() {

	}

	public E findById(Id id) {
		Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("id", id));
		Object obj = criteria.uniqueResult();
		if (obj == null) {
			return null;
		}
		return (E) obj;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	// exampleInstance la mau Object
	// excludeProperty la mot mang String chua ten cac property ma ta ko muon
	// dua vao tieu chi tim kiem
	public List<E> findByExample(E exampleInstance, String[] excludeProperty) {
		Criteria crit = getCurrentSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	// return number of row when Searching
	public int count(E exampleInstance, String[] excludeProperty, boolean isLike) {
		Criteria crit = getCurrentSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		if (isLike) {
			example.enableLike(MatchMode.ANYWHERE).ignoreCase();
		}
		return (Integer) crit.add(example).setProjection(Projections.rowCount()).uniqueResult();
	}

	public int count() {
		return (Integer) getCurrentSession().createCriteria(this.getPersistentClass())
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	public int count(Criterion... criterion) {
		Criteria crit = getCurrentSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return (Integer) crit.setProjection(Projections.rowCount()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public E save(E entity) {
		getCurrentSession().saveOrUpdate(entity);
		getCurrentSession().flush();

		return entity;
	}

	public void delete(E entity) {
		getCurrentSession().delete(entity);
		getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criterion... criterion) {
		Criteria crit = getCurrentSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

}
