package com.casit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.casit.bean.entity.Message;
import com.casit.service.MessageService;
import com.casit.service.base.DaoSupport;
@Service @Transactional
public class MessageServiceBean extends DaoSupport implements MessageService {
	
	/**查询所有聊天纪录
	 * @param uname 本人
	 * @param username 好友
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	public List<Message> find(String uname, String username) {
		Query query1 = em.createQuery("select o from Message o where (o.send_person=?1 and o.reply_person=?2) or (o.send_person=?3 and o.reply_person=?4)");
		query1.setParameter(1, uname);		
		query1.setParameter(2, username);		
		query1.setParameter(3, username);	
		query1.setParameter(4, uname);
		List<Message> messages = new ArrayList<Message>();
		messages = query1.getResultList();
//		Query query2 = em.createQuery("select o from Message o where o.send_person=?1 and o.reply_person=?2");
//		query2.setParameter(1, username);		
//		query2.setParameter(2, uname);
//		messages.addAll(query2.getResultList());
		return messages;
	}
	/**删除某条聊天纪录
	 * @param uname 本人
	 * @param username 好友
	 * @param sendtime 发送时间
	 */
	@Override
	public void delete(String uname, String username, String sendtime) {
		Query query = em.createQuery("delete from Message o where o.send_person=?1 and o.reply_person=?2 and o.send_date=?3");
		query.setParameter(1, uname);
		query.setParameter(2, username);
		query.setParameter(3, sendtime);
		query.executeUpdate();
	}	
	
	@Override
	public void update(String record_path, String send_date) {
		Query query = em.createQuery("update Message o set o.record_path=?1 where o.send_date=?2");
		query.setParameter(1, record_path);
		query.setParameter(2, send_date);
		query.executeUpdate();
	}
}
