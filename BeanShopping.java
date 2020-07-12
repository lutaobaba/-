package cn.edu.zucc.fresh.model;

public class BeanShopping {
	public static BeanShopping currentLoginuser=null;
	public static final String[] tblStepTitle={"商品编号","数量"};
	private String user_id;
	private String product_id;
	private String number;
	
	public String getCell(int col){
		if(col==0) return""+this.getProductId();
		else if(col==1) return ""+this.getNumber();
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
