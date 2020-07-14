package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanAddress;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

public interface IAddressManager {

	public BeanAddress addAddress(String addressid,BeanUser user, String province,String city,String area,String address,String username,String phone) throws BaseException;
	/**

	 */
	public List<BeanAddress> loadAll(BeanUser user)throws BaseException;
	/**

	 */
	public void deleteAddress(BeanAddress address)throws BaseException;
}
