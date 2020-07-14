package cn.edu.zucc.fresh.model;

public class BeanAdmin {
	public static BeanAdmin currentLoginAdmin=null;
	private String admin_id;
	private String admin_name;
	private String admin_pwd;

	public String getAdminId() {
		return admin_id;
	}
	public void setAdminId(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdminName() {
		return admin_name;
	}
	public void setAdminName(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdminPwd() {
		return admin_pwd;
	}
	public void setAdminPwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	
}