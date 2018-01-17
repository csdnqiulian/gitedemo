package com.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.dao.GenericDao;
import com.common.persistence.paging.PageRequest;

/**
 *<p>Title:GenericDaoImpl.java</p>
 *<p>Description:Mybatis 泛型DAO实现</p>
 *2013年12月10日
 */
@Repository("genericDao")
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(String statement, T t) {
		return sqlSessionTemplate.insert(statement, t);
	}
	
	@Override
	public int insert(String statement, List<T> t) {
		return sqlSessionTemplate.insert(statement, t);
	}

	@Override
	public int deleteById(String statement, PK id) {
		return sqlSessionTemplate.delete(statement, id);
	}
	
	@Override
	public int delete(String statement, T t) {
		return sqlSessionTemplate.delete(statement, t);
	}
	
	@Override
	public int batchDeleteByIds(String statement, List<PK> list) {
		return sqlSessionTemplate.delete(statement, list);
	}
	
	@Override
	public int batchDeleteByMap(String statement, Map<String, Object> map) {
		return sqlSessionTemplate.delete(statement, map);
	}

	@Override
	public int update(String statement, T t) {
		return sqlSessionTemplate.update(statement, t);
	}

	@Override
	public T findById(String statement, PK id) {
		return sqlSessionTemplate.selectOne(statement, id);
	}
	
	@Override
	public Object query(String statement,Object param){
		return sqlSessionTemplate.selectOne(statement, param);
	}

	@Override
	public List<T> findAllList(String statement, PageRequest<?> pageRequest) {
		return sqlSessionTemplate.selectList(statement, pageRequest);
	}

	@Override
	public int batchInsert(String statement, List<T> list) {
		return sqlSessionTemplate.insert(statement, list);
	}

	@Override
	public int batchDelete(String statement) {
		return sqlSessionTemplate.delete(statement);
	}

	@Override
	public int batchDeleteByStrList(String statement, List<String> list) {
		return sqlSessionTemplate.delete(statement,list);
	}


	
	@Override
	public List<T> findAllList(String statement, PK id) {
		return sqlSessionTemplate.selectList(statement, id);
	}
	
	@Override
	public List<T> findAllList(String statement){
		return sqlSessionTemplate.selectList(statement);
	}
	
	@Override
	public List<T> findAllList(String statement,List<String> list){
		return sqlSessionTemplate.selectList(statement,list);
	}

	@Override
	public List<T> findAllList(String statement, Map<String, Object> paramMap) {
		return sqlSessionTemplate.selectList(statement, paramMap);
	}
	
	@Override
	public T findBean(String statement, Map<String, String> paramMap){
		return sqlSessionTemplate.selectOne(statement, paramMap);
	}
	
	@Override
	public int batchDelete(String statement, List<Map<String, String>> list) {
		return sqlSessionTemplate.delete(statement, list);
	}

	@Override
	public int batchUpdate(String statement, List<Map<String, String>> list) {
		return sqlSessionTemplate.update(statement, list);
	}

	@Override
	public int batchUpdate(String statement, Map<String, Object> map) {
		return sqlSessionTemplate.update(statement, map);
	}
}
