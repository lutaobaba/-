package cn.edu.zucc.fresh.model;

public class BeanRecipe {
	public static final String[] tblTitle={"²ËÆ×±àºÅ","²ËÆ×Ãû³Æ","²ËÆ×ÓÃÁÏ","²½Öè"};
	private String recipe_id;
	private String recipe_name;
	private String recipe_use;
	private String step;

	public static BeanRecipe currentLoginUser=null;
	
	public String getCell(int col){
		if(col==0) return""+this.getRecipeId();
		else if(col==1) return ""+this.getRecipeName();
		else if(col==2) return ""+this.getRecipeUse();
		else if(col==3) return ""+this.getStep();
		else return "";
	}
	
	public String getRecipeId() {
		return recipe_id;
	}
	public void setRecipeId(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getRecipeName() {
		return recipe_name;
	}
	public void setRecipeName(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	public String getRecipeUse() {
		return recipe_use;
	}
	public void setRecipeUse(String recipe_use) {
		this.recipe_use = recipe_use;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
}
