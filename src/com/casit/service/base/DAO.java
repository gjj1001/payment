package com.casit.service.base;

import java.util.LinkedHashMap;

import com.casit.bean.QueryResult;

/**
 * @author Jason
 *
 */
public interface DAO {
	/**保存实体
	 * @param entity
	 */
	public void save(Object entity);
	/**删除实体
	 * @param entityid
	 */
	public <T> void delete(Class<T> entityClass, Object entityid);
	/**删除实体数组
	 * @param entityids
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityids);
	/**更新实体
	 * @param entity
	 */
	public void update(Object entity);
	/**获取实体
	 * @param entityClass
	 * @param entityid
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Object entityid);
	
	/**获取分页数据
	 * @param entityClass
	 * @param firstIndex
	 * @param maxResult
	 * @return
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult,
			String whereql, Object[] queryParams, LinkedHashMap<String, String> orderby);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult,
			LinkedHashMap<String, String> orderby);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult,
			String whereql, Object[] queryParams);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,  int firstIndex, int maxResult);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass);
}
