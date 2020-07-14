package cn.edu.zucc.fresh.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BeanFoldInfor {
	public static final String[] tableTitle={"满折编号","内容","适用商品数量","折扣","起始日期","结束日期"};
	private String fold_id;
	private String content;
	private String number;
	private String discount;
	private Timestamp start_time;
	private Timestamp end_time; 

	public static BeanFoldInfor currentLoginUser=null;
	
	public String getCell(int col){
		if(col==0) return""+this.getFoldId();
		else if(col==1) return ""+this.getContent();
		else if(col==2) return ""+this.getNumber();
		else if(col==3) return ""+this.getDiscount();
		else if(col==4) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start_time);
		else if(col==5) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end_time);
		else return "";
	}
	
	public String getFoldId() {
		return fold_id;
	}
	public void setFoldId(String fold_id) {
		this.fold_id = fold_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Timestamp getStartTime() {
		return start_time;
	}
	public void setStartTime(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEndTime() {
		return end_time;
	}
	public void setEndTime(Timestamp end_time) {
		this.end_time = end_time;
	}
}
