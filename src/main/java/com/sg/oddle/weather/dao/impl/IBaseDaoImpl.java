package com.sg.oddle.weather.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sg.oddle.weather.dao.IBaseDao;

@Repository
public class IBaseDaoImpl<T,ID extends Serializable> implements IBaseDao<T,ID> {
	
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	

	private Session getSession() {
//		return entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T save(T entity) {
		getSession().save(entity);
		return entity;
	}

	

	@Override
	public boolean delete(T entity) {
		getSession().delete(entity);
		return true;
	}


	@Override
	public List<T> search(Class<T> clazz, CriteriaQuery<T> criteria) {
		return getSession().createQuery(criteria).getResultList();
	}

	@Override
	public T findById(Class<T> entity, ID id) {
		return getSession().get(entity, id);
	}

	@Override
	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public boolean saveBulkData(List<T> listObject,Optional<Integer> size) {
		int i = 0;
		for (T t: listObject) {
			i++;
			getSession().save(t);
			if (size.isPresent() && i % size.get() == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		return false;
	}

}
