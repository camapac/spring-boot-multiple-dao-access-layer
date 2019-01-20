package com.sg.oddle.weather.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

public interface IBaseDao<T, ID extends Serializable> {

	public T save(T entity);
	public boolean delete(T entity);
	public List<T> search(Class<T> clazz, CriteriaQuery<T> criteria);
	public T findById(Class<T> entity,ID id);
	public T saveOrUpdate(T entity);
	public boolean saveBulkData(List<T> listObject,Optional<Integer> size);
}
