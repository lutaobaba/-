package cn.edu.zucc.fresh.model;

public class BeanFoldAss {
	public static final String[] tableTitle={"…Ã∆∑±‡∫≈","¬˙’€±‡∫≈"};
	private String product_id;
	private String fold_id;
	
	public static BeanFoldAss currentLoginUser=null;
	
	public String getCell(int col){
		if(col==0) return""+this.getProductId();
		else if(col==1) return ""+this.getFoldId();
		else return "";
	}
	
	public String getFoldId() {
		return fold_id;
	}
	public void setFoldId(String fold_id) {
		this.fold_id = fold_id;
	}
	public String getProductId() {
		return product_id;
	}
	public void setProductId(String product_id) {
		this.product_id = product_id;
	}
	
}
