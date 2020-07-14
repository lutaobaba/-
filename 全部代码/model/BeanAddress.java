package cn.edu.zucc.fresh.model;

public class BeanAddress {
	public static BeanAddress currentLoginAdmin=null;
	public static final String[] tblStepTitle={"地址编号","省","市","区","详细地址","收货人","电话"};
	private String address_id;
	private String user_id;
	private String province;
	private String city;
	private String area;
	private String address;
	private String user_name;
	private String phone;
	
	public String getCell(int col){
		if(col==0) return""+this.getAddressId();
		else if(col==1) return ""+this.getProvince();
		else if(col==2) return ""+this.getCity();
		else if(col==3) return ""+this.getArea();
		else if(col==4) return ""+this.getAddress();
		else if(col==5) return ""+this.getUserName();
		else if(col==6) return ""+this.getPhone();
		else return "";
	}

	public String getAddressId() {
		return address_id;
	}
	public void setAddressId(String address_id) {
		this.address_id = address_id;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
