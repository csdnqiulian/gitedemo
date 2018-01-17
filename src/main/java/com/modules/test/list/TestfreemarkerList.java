package com.modules.test.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.common.dao.GenericDao;
import com.common.persistence.paging.DataListIfc;
import com.common.persistence.paging.Page;
import com.common.persistence.paging.PageRequest;
import com.modules.test.entity.Testfreemarker;

@Repository("testfreemarkerList")
public class TestfreemarkerList implements DataListIfc<Testfreemarker>{
	
	private final String NAMESPACE = "com.modules.test.dao.TestfreemarkerDao";
	
	private final String FINDTESTFREEMARKERALLLIST = NAMESPACE+"."+"findList";

	@Autowired
	private GenericDao<Testfreemarker,String> genericDao;
	
	@Override
	public Page<Testfreemarker> getPagingDataList(PageRequest<Testfreemarker> pageRequest) {
		String jsonParam = pageRequest.getPageVO().getJsonParam();
		Page<Testfreemarker> page = pageRequest.getPageVO();
		Testfreemarker bean = JSONObject.parseObject(jsonParam, Testfreemarker.class);
		if(bean == null){
			bean = new Testfreemarker();
		}
		page.setBean(bean);//设置查询参数
		
		List<Testfreemarker> dataList = new ArrayList<Testfreemarker>();
		dataList = genericDao.findAllList(FINDTESTFREEMARKERALLLIST, pageRequest);
		page.setRows(dataList);//设置查询结果
		return page;
	}

	@Override
	public Page<Testfreemarker> getAllDataList(PageRequest<Testfreemarker> pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
