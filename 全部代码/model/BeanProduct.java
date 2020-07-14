package cn.edu.zucc.fresh.model;

public class BeanProduct {
	public static final String[] tblStepTitle={"商品编号","商品名称","价格","会员价","数量","规格","描述"};
	private String product_id;
	private String kind_id;
	private String product_name;
	private String price;
	private String vip_price;
	private String number;
	private String norm;
	private String details;
	public static BeanProduct currentLoginUser=null;
	
	public String getCell(int col){
		if(col==0) return""+this.getProductId();
		else if(col==1) return ""+this.getProductName();
		else if(col==2) return ""+this.getPrice();
		else if(col==3) return ""+this.getVipPrice();
		else if(col==4) return ""+this.getNumber();
		else if(col==5) return ""+this.getNorm();
		else if(col==6) return ""+this.getDetails();
		else return "";
	}
	
	public String getProductId() {
		return product_id;
	}
	public void setProductId(String product_id) {
		this.product_id = product_id;
	}
	public String getProductName() {
		return product_name;
	}
	public void setProductName(String product_name) {
		this.product_name = product_name;
	}
	public String getKindid() {
		return kind_id;
	}
	public void setKindId(String kind_id) {
		this.kind_id = kind_id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVipPrice() {
		return vip_price;
	}
	public void setVipPricee(String vip_price) {
		this.vip_price = vip_price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNorm() {
		return norm;
	}
	public void setNorm(String norm) {
		this.norm = norm;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
