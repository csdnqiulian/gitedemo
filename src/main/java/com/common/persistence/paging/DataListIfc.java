package com.common.persistence.paging;
/**
 *<p>Title:DataListIfc.java</p>
 *<p>Company:</p>
 */
public interface DataListIfc<T> {

	/**
	*<b>Summary: </b>
	* getPagingDataList(分页查询)
	* @param pageRequest
	* @return
	 */
	public Page<T> getPagingDataList(PageRequest<T> pageRequest);
	  
	/**
	*<b>Summary: </b>
	* getPagingDataList(不分页查询)
	* @param pageRequest
	* @return
	 */
	public Page<T> getAllDataList(PageRequest<T> pageRequest);
	
}
