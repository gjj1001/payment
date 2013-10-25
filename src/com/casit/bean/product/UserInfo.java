package com.casit.bean.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3040795419679997144L;
	private String mobile;
	private String username;
	private String pwd;
	private String sex;
	private String birthday;
	private String province;
	private String city;
	private Integer tm = 0; //积分值
	private Integer tp = 0; //功勋值
	private Integer userid;
	private String regtime;
//	private String head_image_url;//头像地址
	private String platform;//第三方登陆平台
//	private List<UserIcon> icon;
	
	public UserInfo(String mobile, String username, String pwd, String sex,
			String birthday, String province, String city, String regtime, String platform) {
		super();
		this.mobile = mobile;
		this.username = username;
		this.pwd = pwd;
		this.sex = sex;
		this.birthday = birthday;
		this.province = province;
		this.city = city;
		this.regtime = regtime;		
		this.platform = platform;
	}
	
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Column(length=14)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(length=10, nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=16, nullable=false)
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Column(length=10)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(length=10)
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Column(length=10)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(length=10)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(length=10)
	public Integer getTm() {
		return tm;
	}
	public void setTm(Integer tm) {
		this.tm = tm;
	}

	@Column(length=36)
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	@Column(length=10)
	public Integer getTp() {
		return tp;
	}
	public void setTp(Integer tp) {
		this.tp = tp;
	}

	@Column(length=10)
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	
	
}
