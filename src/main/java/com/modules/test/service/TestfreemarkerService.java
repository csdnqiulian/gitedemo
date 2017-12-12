package com.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.persistence.Page;
import com.common.service.CrudService;
import com.modules.test.entity.Testfreemarker;
import com.modules.test.dao.TestfreemarkerDao;

/**
 * 测试Service
 * @author admin
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class TestfreemarkerService extends CrudService<TestfreemarkerDao, Testfreemarker> {

	public Testfreemarker get(String id) {
		return super.get(id);
	}
	
	public List<Testfreemarker> findList(Testfreemarker testfreemarker) {
		return super.findList(testfreemarker);
	}
	
	public Page<Testfreemarker> findPage(Page<Testfreemarker> page, Testfreemarker testfreemarker) {
		return super.findPage(page, testfreemarker);
	}
	
	@Transactional(readOnly = false)
	public void save(Testfreemarker testfreemarker) {
		super.save(testfreemarker);
	}
	
	@Transactional(readOnly = false)
	public void delete(Testfreemarker testfreemarker) {
		super.delete(testfreemarker);
	}
	
}