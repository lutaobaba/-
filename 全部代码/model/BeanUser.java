package cn.edu.zucc.fresh.model;

import java.sql.Date;

import cn.edu.zucc.fresh.model.BeanUser;

public class BeanUser {
	public static BeanUser currentLoginUser=null;
	private String user_id;
	private String user_name;
	private String user_sex;
	private String user_pwd;
	private String phone;
	private String email;
	private String city;
	private Date registration_time; //注册时间
	private boolean vip;
	private Date vip_end; //VIP到期时间

	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public String getUserSex() {
		return user_sex;
	}
	public void setUserSex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUserPwd() {
		return user_pwd;
	}
	public void setUserPwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreateDate() {
		return registration_time;
	}
	public void setCreateDate(Date registration_time) {
		this.registration_time =registration_time;
	}
	public boolean getVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip =vip;
	}
	public Date getVipEndDate() {
		return vip_end;
	}
	public void setVipEndDate(Date vip_end) {
		this.vip_end =vip_end;
	}
}
