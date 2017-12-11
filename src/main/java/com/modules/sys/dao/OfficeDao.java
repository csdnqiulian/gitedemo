package com.modules.sys.dao;

import com.common.persistence.TreeDao;
import com.common.persistence.annotation.MyBatisDao;
import com.modules.sys.entity.Office;


/**
 * 机构DAO接口
 * @author admin
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
