package cn.edu.zucc.fresh.model;

public class BeanProductRecipe {
	public static BeanProductRecipe currentLoginAdmin=null;
	public static final String[] tblStepTitle={"ÉÌÆ·±àºÅ","²ËÆ×±àºÅ","ÃèÊö"};
	private String product_id;
	private String recipe_id;
	private String des;
	
	public String getCell(int col){
		if(col==0) return""+this.getProductId();
		else if(col==1) return ""+this.getRecipeId();
		else if(col==2) return ""+this.getDes();
		else return "";
	}
	
	public String getProductId() {
		return product_id;
	}
	public void setProductId(String product_id) {
		this.product_id = product_id;
	}
	public String getRecipeId() {
		return recipe_id;
	}
	public void setRecipeId(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
}
