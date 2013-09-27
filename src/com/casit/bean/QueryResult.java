package com.casit.bean;

import java.util.List;

/**
 * @author Jason
 * List<T> resultList
 * Long totalNumber
 * @param <T>
 */
public class QueryResult<T> {
	private List<T> resultList;
	private Long totalNumber;
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	
}
