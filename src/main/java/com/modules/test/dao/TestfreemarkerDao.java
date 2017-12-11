package com.modules.test.dao;

import com.common.persistence.CrudDao;
import com.common.persistence.annotation.MyBatisDao;
import com.modules.test.entity.Testfreemarker;

/**
 * 测试DAO接口
 * @author admin
 * @version 2017-12-10
 */
@MyBatisDao
public interface TestfreemarkerDao extends CrudDao<Testfreemarker> {
	
}