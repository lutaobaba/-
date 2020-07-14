package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanRecipe;
import cn.edu.zucc.fresh.util.BaseException;

public interface IRecipeManager {
	
	public BeanRecipe addRecipe(String recipeid, String recipename,String recipeuse,String step) throws BaseException;
	
	public List<BeanRecipe> loadAll()throws BaseException;
	
	public void deleteRecipe(BeanRecipe recipe)throws BaseException;
}
