package com.casit.bean.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PubContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348084822457494151L;
	private Integer pubId;
	private String content;
	private String imageFile;
	private String sendtime;
	private String headimage;
	private String username;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getPubId() {
		return pubId;
	}
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}
	@Column(nullable=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}	
	@Column(length=36)
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	@Column
	public String getHeadimage() {
		return headimage;
	}
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	@Column(length=10)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
