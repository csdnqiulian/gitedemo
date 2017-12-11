package com.modules.test.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.service.CrudService;
import com.modules.test.bean.Test;
import com.modules.test.dao.TestDao;

@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {
	@Transactional(readOnly = true)
	public List<Test> findList(Test test){
		return dao.findList(test);
	}
	
	@Transactional(readOnly = false)
	public void saveTest(Test test){
		  if(test.getId()==""){
			  test.setId(UUID.randomUUID().toString());
			  dao.saveTest(test);
		  } else {
			  dao.updateTest(test);
		  }
	}
	
	@Transactional(readOnly=true)
	public Test findTestById(String id){
		return dao.findTestById(id);
	}
	
	@Transactional(readOnly = false)
	public void deleteTestById(String id){
		dao.deleteTestById(id);
	}
}
