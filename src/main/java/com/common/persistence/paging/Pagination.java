package com.common.persistence.paging;

import java.util.List;
import java.util.Map;


/**
 *<p>Title:Pagination.java</p>
 *<p>Description:分页实现类</p>
 */
public class Pagination<T> implements Page<T> {
	
	private int defaultLength = 10; //默认每页10条记录
	
	private int page = 1;		//当前页，从1开始
	private int length;		//每页显示记录条数
	private long start;		//分页开始序号
	private String orderName;	//排序字段
	private String orderType;	//排序类型，asc/desc
	private String jsonParam;	//查询条件 json串
	private long records;	//总记录条数
	private long total; 	//总页数
	private T bean;	//搜索条件实例化的对象
	private Map<String,Object> map;//搜索条件实例化的map
	private List<T> rows;	//返回的数据集
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public T getBean() {
		return bean;
	}
	
	public void setBean(T bean) {
		this.bean = bean;
	}
	public int getDefaultLength() {
		return defaultLength;
	}
	public void setDefaultLength(int defaultLength) {
		this.defaultLength = defaultLength;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		//如果传入的排序为空，则默认升序
		if(orderType == null || "".equals(orderType)){
			this.orderType = "asc";
		} else {
			//如果传入的排序非法，则默认升序
			if(!"asc".equalsIgnoreCase(orderType) && !"desc".equalsIgnoreCase(orderType)){
				this.orderType = "asc";
			} else {
				this.orderType = orderType;				
			}
		}
	}
	public String getJsonParam() {
		return jsonParam;
	}
	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}
	public void setStart(long start) {
		this.start = start;
		this.page = (int) (start/length + 1);	//设置当前页
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length == 0 ? this.defaultLength : length;
	}
	public long getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public long getRecords() {
		return records;
	}
	public void setRecords(long totalRecords) {
		this.records = totalRecords;
		long totalPage = totalRecords%length==0 ? totalRecords/length : totalRecords/length+1;
		setTotal(totalPage);
	}
	@Override
	public String toString() {
		return "Pagination [length=" + length + ", start="
				+ start + ", orderName=" + orderName
				+ ", orderType=" + orderType + ", jsonParam=" + jsonParam
				+ ", records=" + records + ", bean=" + bean ;
	}

	
}
