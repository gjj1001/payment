package com.casit.bean.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private String regtime; //注册时间
//	private String head_image_url;//头像地址
	private String platform;//第三方登陆平台
	/*private Set<UserInfo> friends = new HashSet<UserInfo>();
	private UserInfo myself;
	private Integer flag;
	private boolean vip;//专家
	private boolean vender;//厂商
*/	
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

	/*@Column(length=2)
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}*/


	/*@Column
	public String getHead_image_url() {
		return head_image_url;
	}
	public void setHead_image_url(String head_image_url) {
		this.head_image_url = head_image_url;
	}*/

	/*@OneToMany(cascade={CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy="myself")
	public Set<UserInfo> getFriends() {
		return friends;
	}
	public void setFriends(Set<UserInfo> friends) {
		this.friends = friends;
	}

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="myselfid")
	public UserInfo getMyself() {
		return myself;
	}
	public void setMyself(UserInfo myself) {
		this.myself = myself;
	}*/


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

	/*@Column(length=2)
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Column(length=2)
	public boolean isVender() {
		return vender;
	}
	public void setVender(boolean vender) {
		this.vender = vender;
	}*/

	
	
}
