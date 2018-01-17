package com.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.common.persistence.paging.PageRequest;

/**
 *<p>Title:GenericDao.java</p>
 *<p>Description:Mybatis 泛型DAO接口</p>
 *@version 1.0
 *2013年12月10日
 */
public abstract interface GenericDao<T, PK extends Serializable>  {
	/**
	*<b>Summary: 插入单条数据</b>
	* insert
	* @param statement mapper 域+id
	* @param t 实体javabean
	*/
    public int insert(String statement,T t);  
    
    /**
    *<b>Summary:批量插入数据 </b>
    * insert()
    * @param statement
    * @param t
    * @return
     */
    public int insert(String statement,List<T> t);  
      
    /**
    *<b>Summary: 根据主键id删除数据</b>
    * deleteById
    * @param statement mapper 域+id
    * @param id
    */
    public int deleteById(String statement,PK id);  
    
    /**
    *<b>Summary: 删除数据</b>
    * delete()
    * @param statement
    * @param t
    * @return
     */
    public int delete(String statement,T t);
    
    /**
     *<b>Summary: 批量删除数据</b>
     * batchDeleteByIds
     * @param statement
     * @param list
     * @return
     */
    public int batchDeleteByIds(String statement,List<PK> list);
    
    /**
     * 
    *<b>Summary: </b>
    * batchDeleteByMap(根据map的值删除数据)
    * @param statement
    * @param map
    * @return
     */
    public int batchDeleteByMap(String statement, Map<String, Object> map);
    
    /**
     *<b>Summary: 批量删除数据</b>
     * batchDelete
     * @param statement
     * @param list
     * @return
     */
    public int batchDeleteByStrList(String statement,List<String> list);
    
    /**
    *<b>Summary:数据更新 </b>
    * update
    * @param statement mapper 域+id
    * @param t 实体javabean
    */
    public int update(String statement,T t);
      
    /**
    *<b>Summary: 根据主键查询</b>
    * findById
    * @param statement mapper 域+id
    * @param id
    * @return
    */
    public T findById(String statement,PK id);
    
    /**
     *<b>Summary: 普通查询</b>
     * query
     * @param statement
     * @param param
     * @return
     */
    public Object query(String statement,Object param);
    
    /**
	*<b>Summary: 查询列表数据</b>
	* findAllList
	* @param statement mapper 域+id
	* @param pageRequest
	* @return
	*/
	public List<T> findAllList(String statement,PageRequest<?> pageRequest);  
	
	/**
	*<b>Summary: </b>
	* findAllList(根据传入参数查询匹配数据)
	* @param statement
	* @param id
	* @return
	 */
	public List<T> findAllList(String statement, PK id);
	
	/**
	*<b>Summary: 查询所有数据</b>
	* findAllList()
	* @param statement
	* @return
	*/
	public List<T> findAllList(String statement);
	
	/**
	 * 
	*<b>Summary: </b>
	* findAllList(根据list参数查询数据)
	* @param statement
	* @param list
	* @return
	 */
	public List<T> findAllList(String statement,List<String> list);
	
	/**
	 * 
	*<b>Summary: </b>
	* findAllList(根据传参获取数据)
	* @param statement
	* @param paramMap
	* @return
	 */
	public List<T> findAllList(String statement, Map<String, Object> paramMap);
	
	/**
	 * 
	*<b>Summary: </b>
	* findBean(根据传参获取数据)
	* @param statement
	* @param paramMap
	* @return
	 */
	public T findBean(String statement, Map<String, String> paramMap);
  
	/**
	 * 
	*<b>Summary: </b>
	* batchInsert(批量插入)
	* @param statement
	* @param list
	* @return
	 */
    public int batchInsert(String statement,List<T> list);
    
    /**
     *<b>Summary: 删除数据</b>
     * batchDelete()
     * @param statement
     * @return
      */
     public int batchDelete(String statement);
    
     /**
      * 
     *<b>Summary: </b>
     * batchDelete(删除数据)
     * @param statement
     * @param list
     * @return
      */
     public int batchDelete(String statement,List<Map<String, String>> list);
     
     /**
      * 
     *<b>Summary: </b>
     * batchUpdate(修改数据)
     * @param statement
     * @param list
     * @return
      */
     public int batchUpdate(String statement,List<Map<String, String>> list);
     
     /**
      * 
     *<b>Summary: </b>
     * batchUpdate(修改数据)
     * @param statement
     * @param list
     * @return
      */
     public int batchUpdate(String statement,Map<String, Object> map);
    
}
