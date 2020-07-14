package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanCoupon;
import cn.edu.zucc.fresh.util.BaseException;

public interface ICouponManager {
	
	public BeanCoupon addCoupon(String couponid, String content,String useamount,String reliefamount,String startdate,String enddate) throws BaseException;

	public List<BeanCoupon> loadAll()throws BaseException;

	public void deleteCoupon(BeanCoupon coupon)throws BaseException;
}
