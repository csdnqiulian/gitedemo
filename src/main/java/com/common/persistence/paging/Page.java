package com.common.persistence.paging;

import java.util.List;
import java.util.Map;

public interface Page<T>{
	
	public Map<String, Object> getMap();
	
	public void setMap(Map<String, Object> map);

	public int getPage();
	
	public void setPage(int page);

	public List<T> getRows();
	
	public void setRows(List<T> rows);

	public long getTotal();
	
	public void setTotal(long total);

	public T getBean();
	
	public void setBean(T t);
	
	public int getDefaultLength();
	
	public String getOrderName();
	
	public void setOrderName(String orderName);
	
	public String getOrderType();
	
	public void setOrderType(String orderType);
	
	public String getJsonParam();
	
	public void setJsonParam(String jsonParam);

	public int getLength();
	
	public void setLength(int length);
	
	public long getStart();

	public void setStart(int start);
	
	public long getRecords();
	
	public void setRecords(long records);
	
}
