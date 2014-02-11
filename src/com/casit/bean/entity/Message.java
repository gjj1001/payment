package com.casit.bean.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Message {

	private int id;
	private String send_ctn;
	private String send_person;
	private String reply_person;
	private String send_date;
	private String record_path;
	private boolean ifyuyin = false; // 是否是语音消息
	private long recordTime; // 语音消息持续的时间
	private String bitmap; //用户头像
	private String msgId;
	
	@Column
	public String getSend_ctn() {
		return send_ctn;
	}

	public void setSend_ctn(String send_ctn) {
		this.send_ctn = send_ctn;
	}
	@Column(length=10)
	public String getSend_person() {
		return send_person;
	}

	public void setSend_person(String send_person) {
		this.send_person = send_person;
	}
	@Column(length=36)
	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	@Column
	public String getBitmap() {
		return bitmap;
	}

	public void setBitmap(String bitmap) {
		this.bitmap = bitmap;
	}

	public Message(String send_ctn, String send_person, String send_date,
			String bitmap) {
		super();
		this.send_ctn = send_ctn;
		this.send_person = send_person;
		this.send_date = send_date;
		this.bitmap = bitmap;
	}

	public Message() {
		super();
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column
	public String getRecord_path() {
		return record_path;
	}

	public void setRecord_path(String record_path) {
		this.record_path = record_path;
	}
	@Column
	public boolean isIfyuyin() {
		return ifyuyin;
	}

	public void setIfyuyin(boolean ifyuyin) {
		this.ifyuyin = ifyuyin;
	}
	@Column
	public long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(long recordTime) {
		this.recordTime = recordTime;
	}
	@Column(length=10)
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	@Column(length=10)
	public String getReply_person() {
		return reply_person;
	}

	public void setReply_person(String reply_person) {
		this.reply_person = reply_person;
	}
	
}
