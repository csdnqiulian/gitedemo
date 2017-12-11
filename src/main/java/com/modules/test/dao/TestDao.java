package com.modules.test.dao;

import java.util.List;

import com.common.persistence.CrudDao;
import com.common.persistence.annotation.MyBatisDao;
import com.modules.test.bean.Test;

@MyBatisDao
public interface TestDao extends CrudDao<Test>{

	public List<Test> findList(Test test);
	
	public void saveTest(Test test);
	
	public Test findTestById(String id);
	
	public void updateTest(Test test);
	
	public void deleteTestById(String id);
	
}
