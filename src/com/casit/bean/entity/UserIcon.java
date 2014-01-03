package com.casit.bean.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserIcon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9028632267659722640L;
	private Integer iconId;
	private String name;
	private String img;
//	private UserInfo userInfo;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIconId() {
		return iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	@Column(length=12)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=36)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
