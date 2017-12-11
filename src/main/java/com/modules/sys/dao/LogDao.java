package com.modules.sys.dao;

import com.common.persistence.CrudDao;
import com.common.persistence.annotation.MyBatisDao;
import com.modules.sys.entity.Log;


/**
 * 日志DAO接口
 * @author admin
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
