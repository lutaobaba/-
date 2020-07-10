package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.util.BaseException;

public interface IProductManager {
	/**

	 */
	public BeanProduct addProduct(String productid, BeanFreshKind kindid,String productname,String price,String vPrice,String number,String norm, String details) throws BaseException;
	/**

	 */
	public List<BeanProduct> loadProducts(BeanFreshKind kind)throws BaseException;
	/**

	 */
	public void deleteProduct(BeanProduct product)throws BaseException;
}
