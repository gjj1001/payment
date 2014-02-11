package com.casit.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.Observer;
import com.casit.service.ObserverService;
import com.casit.service.base.DaoSupport;
@Service @Transactional
public class ObserverServiceBean extends DaoSupport implements ObserverService {

	/**查询本人关注的所有关注者
	 * @param uname 本人
	 * @param username 要关注的人
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<Observer> find(String name, int flag) { 
		switch(flag) {
		case 1:
			Query query1 = em.createQuery("select o from Observer o where o.uname=?1");
			query1.setParameter(1, name);	
			List<Observer> observers = new ArrayList<Observer>();
			observers = query1.getResultList();
			return observers;
		case 2:
			Query query2 = em.createQuery("select o from Observer o where o.username=?1");
			query2.setParameter(1, name);	
			List<Observer> fans = new ArrayList<Observer>();
			fans = query2.getResultList();
			return fans;
		}
		return null;
	}
	
	/**查询本人关注的某个关注者
	 * @param uname 本人
	 * @param username 要关注的人
	 */
	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public Observer find(String uname, String username) {
		Query query = em.createQuery("select o from Observer o where o.uname=?1 and o.username=?2");
		query.setParameter(1, uname);		
		query.setParameter(2, username);		
		return (Observer) query.getSingleResult();
	}
	/**删除本人关注的某个关注者
	 * @param uname 本人
	 * @param username 要关注的人
	 */
	@Override
	public void delete(String uname, String username) {
		Query query = em.createQuery("delete from Observer o where o.uname=?1 and o.username=?2");
		query.setParameter(1, uname);
		query.setParameter(2, username);
		query.executeUpdate();
	}
	
	/**添加关注
	 * @param uname 本人
	 * @param username 要关注的人
	 */
	@Override
	public void add(String uname, String username, String headimage, String headimg) {
		Query query = em.createNativeQuery("insert into Observer (uname,headimage,username,headimg) values("
				+"'"+uname+"'"+","+"'"+headimage+"'"+","+"'"+username+"'"+","+"'"+headimg+"'"+")");
		query.executeUpdate();
		
		/*Observer child = new Observer();
		child.setUname(username);
		child.setHeadimage(headimg);
		Set<Observer> observers = new HashSet<Observer>();
		observers = parent.getObserver();
		observers.add(child);
		Query query = em.createQuery("update Observer o set o.uname=?1");
		query.setParameter(1, parent.getUname());
		query.executeUpdate();*/
	}
}
