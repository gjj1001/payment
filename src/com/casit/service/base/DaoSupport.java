package com.casit.service.base;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.QueryResult;

@Transactional
public abstract class DaoSupport implements DAO {
	@PersistenceContext protected EntityManager em;
	@Override
	public void save(Object entity) {
		em.persist(entity);

	}

	@Override
	public <T> void delete(Class<T> entityClass, Object entityid) {
		delete(entityClass, new Object[]{entityid});

	}

	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for(Object entityid : entityids) {
			em.remove(em.getReference(entityClass, entityid));
		}

	}

	@Override
	public void update(Object entity) {
		em.merge(entity);

	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityid) {
		return em.find(entityClass, entityid);		
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstIndex, int maxResult, LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass, firstIndex, maxResult,
				null, null, orderby);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstIndex, int maxResult, String whereql, Object[] queryParams) {
		return getScrollData(entityClass, firstIndex, maxResult,
				whereql, queryParams, null);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,
			int firstIndex, int maxResult) {
		return getScrollData(entityClass, firstIndex, maxResult,
				null, null, null);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public <T> QueryResult<T> getScrollData(Class<T> entityClass, int firstIndex, int maxResult, String whereql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityName = getEntityName(entityClass);
		Query query = em.createQuery("select o from "+entityName+" o "+("".equals(whereql)?"":"where "+whereql)+getOrderBy(orderby));
		setQueryParams(query,queryParams);
		if(firstIndex!=-1 && maxResult!=-1) {
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxResult);
		}
		/*Map<String, Object> hints= new HashMap<String, Object>(); 
		hints = query.getHints();
		Iterator<String> it = hints.keySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}*/
		qr.setResultList(query.getResultList());
		query = em.createQuery("select count(o) from "+entityName+" o "+("".equals(whereql)?"":"where "+whereql));
		setQueryParams(query,queryParams);
		qr.setTotalNumber((Long) query.getSingleResult());
		return qr;
	}
	
	/**给位置参数赋值
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParams(Query query, Object[] queryParams) {
		if(queryParams!=null && queryParams.length>0) {
			for(int i=0; i<queryParams.length; i++) {
				query.setParameter(i+1, queryParams[i]);
			}
		}		
	}

	/**组装order by语句
	 * @param orderby
	 * @return
	 */
	protected String getOrderBy(LinkedHashMap<String, String> orderby) {
		StringBuffer jpql = new StringBuffer("");		
		if(orderby!=null && orderby.size()>0) {
			jpql.append(" order by ");
			for(String key : orderby.keySet()) {
				jpql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			jpql.deleteCharAt(jpql.length()-1);
		}
		return jpql.toString();
		
	}
	
	/**获得实体名称
	 * @param entityClass
	 * @return
	 */
	protected <T> String getEntityName(Class<T> entityClass) {
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())) {
			entityName = entity.name();
		}
		return entityName;
		
	}

}
