package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanShopping;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

public interface IShoppingManager {
	
	public BeanShopping addProduct(String productid,BeanUser user,String number) throws BaseException;
	/**

	 */
	public List<BeanShopping> loadAll(BeanUser user)throws BaseException;
	/**

	 */
	public void deleteProduct(BeanShopping product)throws BaseException;

}
