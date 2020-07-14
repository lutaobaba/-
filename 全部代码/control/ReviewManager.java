package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IReviewManager;
import cn.edu.zucc.fresh.model.BeanComOrder;
import cn.edu.zucc.fresh.model.BeanProductReview;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;

public class ReviewManager implements IReviewManager{

	@Override
	public BeanProductReview addReview(BeanComOrder bcComOrder, String productid, String content, String star)
			throws BaseException {
		// TODO Auto-generated method stub
		if(productid==null || "".equals(productid)) throw new BusinessException("商品编号不能为空");
		if(content==null || "".equals(content)) throw new BusinessException("评价内容不能为空");
		if(star==null || "".equals(star)) throw new BusinessException("星级不能为空");
		Connection conn=null;
		try {
			String userid=bcComOrder.getUserId();
			conn=DBUtil.getConnection();
			
			String sql="select product_id from product_information where product_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("商品编号不存在");
			rs.close();
			pst.close();
			
			
			sql="insert into product_review(user_id,product_id,content,date,star) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			pst.setString(2,productid);
			pst.setString(3,content);
			pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(5, star);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	@Override
	public List<BeanProductReview> loadReview(BeanComOrder bComOrder) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanProductReview> result=new ArrayList<BeanProductReview>();
		Connection conn=null;
		try {
			String pr=bComOrder.getProductId();
			conn=DBUtil.getConnection();
			String sql="select * from product_review where product_id=? order by user_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, pr);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanProductReview bpr=new BeanProductReview();
				bpr.setUserId(rs.getString(1));
				bpr.setProductId(rs.getString(2));
				bpr.setContent(rs.getString(3));
				bpr.setDate(rs.getDate(4));
				bpr.setStar(rs.getString(5));
			    result.add(bpr);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}

	@Override
	public void deleteReview(BeanProductReview bProductReview) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
