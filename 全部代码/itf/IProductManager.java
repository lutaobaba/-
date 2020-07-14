package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanFoldAss;
import cn.edu.zucc.fresh.model.BeanFoldInfor;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.model.BeanProductRecipe;
import cn.edu.zucc.fresh.model.BeanRecipe;
import cn.edu.zucc.fresh.model.BeanShopping;
import cn.edu.zucc.fresh.util.BaseException;

public interface IProductManager {
	
	public BeanProduct addProduct(String productid, BeanFreshKind kindid,String productname,String price,String vPrice,String number,String norm, String details) throws BaseException;
	
	public List<BeanProduct> loadProducts(BeanFreshKind kind)throws BaseException;
	
	public void deleteProduct(BeanProduct product)throws BaseException;
	
	public BeanProductRecipe addRecipe(String productid, BeanRecipe Recipe,String des) throws BaseException;
	
	public List<BeanProductRecipe> loadRecipe(BeanRecipe recipe)throws BaseException;
	
	public void deleteRecipe(BeanProductRecipe productRecipe)throws BaseException;
	
	public BeanFoldAss addASS(String productid,BeanFoldInfor bfInfor)throws BaseException;
	
	public List<BeanFoldAss> loadAss(BeanFoldInfor bFoldInfor)throws BaseException;
	
	public void deleteAss(BeanFoldAss bfAss)throws BaseException;
	
	public List<BeanProduct> loadShoppings(BeanShopping shop)throws BaseException;
}
