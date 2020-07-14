package cn.edu.zucc.fresh.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BeanComOrder {
	public static BeanComOrder currentLoginUser=null;
	public static final String[] tableTitle={"订单编号","商品编号","收货人编号","地址编号","原价","折后价","优惠券编号","送达时间","订单状态"};
	private String order_id;
	private String product_id;
	private String user_id;
	private String address_id;
	private String old_money;
	private String new_money;
	private String coupon_id;
	private Date request_time;
	private String order_status;
	
	public String getCell(int col){
		if(col==0) return""+this.getOrderId();
		else if(col==1) return ""+this.getProductId();
		else if(col==2) return ""+this.getUserId();
		else if(col==3) return ""+this.getAddressId();
		else if(col==4) return ""+this.getOldMoney();
		else if(col==5) return ""+this.getNewMoney();
		else if(col==6) return ""+this.getCouponId();
		else if(col==7) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(request_time);
		else if(col==8) return ""+this.getOrderStatus();
		else return "";
	}

	public String getOrderId() {
		return order_id;
	}
	public void setOrderId(String order_id) {
		this.order_id = order_id;
	}
	public String getProductId() {
		return product_id;
	}
	public void setProductId(String product_id) {
		this.product_id = product_id;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	
	public String getAddressId() {
		return address_id;
	}
	public void setAddressId(String address_id) {
		this.address_id = address_id;
	}
	public String getOldMoney() {
		return old_money;
	}
	public void setOldMoney(String old_money) {
		this.old_money = old_money;
	}
	public String getNewMoney() {
		return new_money;
	}
	public void setNewMoney(String new_money) {
		this.new_money = new_money;
	}
	public String getCouponId() {
		return coupon_id;
	}
	public void setCouponId(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public Date getRequestTime() {
		return request_time;
	}
	public void setRequestTime(Date request_time) {
		this.request_time = request_time;
	}
	public String getOrderStatus() {
		return order_status;
	}
	public void setOrderStatus(String order_status) {
		this.order_status = order_status;
	}
	
}
