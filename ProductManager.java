package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IProductManager;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.*;
import cn.edu.zucc.fresh.itf.IProductManager;

public class ProductManager implements IProductManager{
	
	
	@Override
	public BeanProduct addProduct(String productid, String kindid,String productname,String price,String vipprice,String number,String norm, String details) throws BaseException {
		// TODO Auto-generated method stub
		if(productid==null || "".equals(productid)) throw new BusinessException("商品编号不能为空");
		if(kindid==null || "".equals(kindid)) throw new BusinessException("类别编号不能为空");
		if(productname==null || "".equals(productname)) throw new BusinessException("商品名称不能为空");
		if(price==null || "".equals(price)) throw new BusinessException("价格不能为空");
		if(vipprice==null || "".equals(vipprice)) throw new BusinessException("会员价不能为空");
		if(number==null || "".equals(number)) throw new BusinessException("数量不能为空");
		if(norm==null || "".equals(norm)) throw new BusinessException("规格不能为空");
		if(details==null || "".equals(details)) throw new BusinessException("详情不能为空");
		
		BeanProduct bp=new BeanProduct();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product_information where product_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("商品编号已存在");
			rs.close();
			pst.close();
			
			sql="insert into product_information(product_id,product_name,price,vip_price,number,norm,details) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,productid);
			//pst.setString(2,kindid);
			pst.setString(2,productname);
			pst.setString(3,price);
			pst.setString(4,vipprice);
			pst.setString(5,number);
			pst.setString(6,norm);
			pst.setString(7,details);
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
		return bp;
	}
	@Override
	public List<BeanProduct> loadProducts(BeanFreshKind kind) throws BaseException {
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			String kindid=kind.getKindId();
			conn=DBUtil.getConnection();
			String sql="select step_id,plan_id,step_order,step_name,plan_begin_time,plan_end_time,real_begin_time,real_end_time "
					+ "from tbl_step "
					+ "where plan_id=? order by step_order";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//pst.setInt(1, kindid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
//				BeanProduct bs=new BeanProduct();
//				bs.setKindId(rs.getString(1));
//				bs.setPlan_id(rs.getInt(2));
//				bs.setStep_order(rs.getInt(3));
//				bs.setStep_name(rs.getString(4));
//				bs.setPlan_begin_time(rs.getTimestamp(5));
//				bs.setPlan_end_time(rs.getTimestamp(6));
//				bs.setReal_begin_time(rs.getTimestamp(7));
//				bs.setReal_end_time(rs.getTimestamp(8));
//			    result.add(bs);
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
	public void deleteProduct(BeanProduct product) throws BaseException {
		// TODO Auto-generated method stub
		
	}
}
