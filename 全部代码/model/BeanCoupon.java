package cn.edu.zucc.fresh.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BeanCoupon {
	public static final String[] tableTitles={"优惠券编号","优惠内容","适用金额","减免金额","起始日期","结束日期"};
	private String coupon_id;
	private String content;
	private Double use_amount;
	private Double relief_amount;
	private Date start_date;
	private Date end_date;
	public static BeanFreshKind currentLoginUser=null;
	
	public String getCell(int col){
		if(col==0) return""+this.getCouponId();
		else if(col==1) return ""+this.getContent();
		else if(col==2) return ""+this.getUseAmount();
		else if(col==3) return ""+this.getReliefAmount();
		else if(col==4) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start_date);
		else if(col==5) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end_date);
		else return "";
	}
	
	public String getCouponId() {
		return coupon_id;
	}
	public void setCouponId(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getUseAmount() {
		return use_amount;
	}
	public void setUseAmount(Double use_amount) {
		this.use_amount = use_amount;
	}
	public double getReliefAmount() {
		return relief_amount;
	}
	public void setReliefAmount(Double relief_amount) {
		this.relief_amount = relief_amount;
	}
	public Date getStartDate() {
		return start_date;
	}
	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEndDate() {
		return end_date;
	}
	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}
}
