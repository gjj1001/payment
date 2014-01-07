package com.casit.service;

import java.util.List;

import com.casit.bean.entity.Comment;
import com.casit.service.base.DAO;

public interface CommentService extends DAO {

	List<Comment> find(String obj, String parameter);

	void delete(String comtime);

}
