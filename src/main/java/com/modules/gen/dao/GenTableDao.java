package com.modules.gen.dao;

import com.common.persistence.CrudDao;
import com.common.persistence.annotation.MyBatisDao;
import com.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
	
}
