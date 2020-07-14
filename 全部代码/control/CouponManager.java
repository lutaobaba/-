package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.ICouponManager;
import cn.edu.zucc.fresh.model.BeanCoupon;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;

public class CouponManager implements ICouponManager{

	@Override
	public BeanCoupon addCoupon(String couponid, String content, String useamount, String reliefamount, String startdate,
			String enddate) throws BaseException {
		// TODO Auto-generated method stub
		if(couponid==null || "".equals(couponid)) throw new BusinessException("优惠券编号不能为空");
		if(content==null || "".equals(content)) throw new BusinessException("优惠内容不能为空");
		if(useamount==null || "".equals(useamount)) throw new BusinessException("适用金额不能为空");
		if(reliefamount==null || "".equals(reliefamount)) throw new BusinessException("减免金额不能为空");
		if(startdate==null || "".equals(startdate)) throw new BusinessException("起始时间不能为空");
		if(enddate==null || "".equals(enddate)) throw new BusinessException("结束时间不能为空");
		
		BeanCoupon bc=new BeanCoupon();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from coupon where coupon_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, couponid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("编号已存在");
			rs.close();
			pst.close();
			
			sql="insert into coupon(coupon_id,content,use_amount,relief_amount,start_date,end_date) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,couponid);
			pst.setString(2,content);
			pst.setString(3,useamount);
			pst.setString(4,reliefamount);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pst.setTimestamp(5,new Timestamp(format.parse(startdate).getTime()));
			pst.setTimestamp(6,new Timestamp(format.parse(enddate).getTime()));
			pst.execute();
			pst.close();
		} catch (Exception e) {
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
		return bc;
	}

	@Override
	public List<BeanCoupon> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		  	try {
		  		conn=DBUtil.getConnection();
		  		String sql="select * from coupon order by coupon_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanCoupon bcp=new BeanCoupon();
		  			bcp.setCouponId(rs.getString(1));
		  			bcp.setContent(rs.getString(2));
		  			bcp.setUseAmount(rs.getDouble(3));
		  			bcp.setReliefAmount(rs.getDouble(4));
		  			bcp.setStartDate(rs.getDate(5));
		  			bcp.setEndDate(rs.getDate(6));
			    	result.add(bcp);
		  		}
		  }catch (SQLException e) {
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
	public void deleteCoupon(BeanCoupon coupon) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="delete from coupon where coupon_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, coupon.getCouponId());
			pst.execute();
			pst.close();
			
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
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
	}

}
