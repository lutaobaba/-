package cn.edu.zucc.fresh.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BeanProductReview {
	public static BeanProductReview currentLoginAdmin=null;
	public static final String[] tblStepTitle={"用户编号","商品编号","评价","评价日期","星级"};
	private String user_id;
	private String product_id;
	private String content;
	private Date date;
	private String star;
	
	public String getCell(int col){
		if(col==0) return""+this.getUserId();
		else if(col==1) return ""+this.getProductId();
		else if(col==2) return ""+this.getContent();
		else if(col==3) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		else if(col==4) return ""+this.getStar();
		else return "";
	}
	
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	public String getProductId() {
		return product_id;
	}
	public void setProductId(String product_id) {
		this.product_id = product_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
}
