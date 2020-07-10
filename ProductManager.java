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

public class ProductManager implements IProductManager{
	BeanFreshKind kindid=new BeanFreshKind();
	
	@Override
	public BeanProduct addProduct(String productid, BeanFreshKind kindid,String productname,String price,String vipprice,String number,String norm, String details) throws BaseException {
		// TODO Auto-generated method stub
		if(productid==null || "".equals(productid)) throw new BusinessException("商品编号不能为空");
		if(productname==null || "".equals(productname)) throw new BusinessException("商品名称不能为空");
		if(price==null || "".equals(price)) throw new BusinessException("价格不能为空");
		if(vipprice==null || "".equals(vipprice)) throw new BusinessException("会员价不能为空");
		if(number==null || "".equals(number)) throw new BusinessException("数量不能为空");
		if(norm==null || "".equals(norm)) throw new BusinessException("规格不能为空");
		if(details==null || "".equals(details)) throw new BusinessException("详情不能为空");

		Connection conn=null;
		try {
			String kindidString=kindid.getKindId();
			conn=DBUtil.getConnection();
			
			String sql="select * from product_information where product_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("商品编号已存在");
			rs.close();
			pst.close();
			
			sql="insert into product_information(product_id,kind_id,product_name,price,vip_price,number,norm,details) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,productid);
			pst.setString(2,kindidString);
			pst.setString(3,productname);
			pst.setString(4,price);
			pst.setString(5,vipprice);
			pst.setString(6,number);
			pst.setString(7,norm);
			pst.setString(8,details);
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
	public List<BeanProduct> loadProducts(BeanFreshKind kind) throws BaseException {
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			String kindid=kind.getKindId();
			conn=DBUtil.getConnection();
			String sql="select * from product_information where kind_id=? order by product_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, kindid);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanProduct bp=new BeanProduct();
				bp.setProductId(rs.getString(1));
				bp.setKindId(rs.getString(2));
				bp.setProductName(rs.getString(3));
				bp.setPrice(rs.getString(4));
				bp.setVipPricee(rs.getString(5));
				bp.setNumber(rs.getString(6));
				bp.setNorm(rs.getString(7));
				bp.setDetails(rs.getString(8));
			    result.add(bp);
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
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="delete from product_information where product_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, product.getProductId());
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
