package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanComOrder;
import cn.edu.zucc.fresh.model.BeanProductReview;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

public interface IReviewManager {
	
	public BeanProductReview addReview(BeanComOrder bComOrder,String productid,String content,String star) throws BaseException;
	
	public List<BeanProductReview> loadReview(BeanComOrder bComOrder)throws BaseException;
	
	public void deleteReview(BeanProductReview bProductReview)throws BaseException;
	
}
