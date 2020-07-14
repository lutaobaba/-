package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IShoppingManager;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanShopping;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;

public class ShoppingManager implements IShoppingManager{

	@Override
	public BeanShopping addProduct(String productid, BeanUser user, String number) throws BaseException {
		// TODO Auto-generated method stub
		if(productid==null || "".equals(productid)) throw new BusinessException("商品编号不能为空");
		if(number==null || "".equals(number)) throw new BusinessException("数量不能为空");
		Connection conn=null;
		try {
			String userid=user.getUserId();
			conn=DBUtil.getConnection();
			
			String sql="select product_id,number from product_information where product_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("商品编号不存在");
			rs.close();
			pst.close();
			
			sql="select number from product_information where product_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			rs=pst.executeQuery();
			if(rs.next()) {
				if(rs.getDouble(1)<Double.parseDouble(number)||rs.getDouble(1)<=0)throw new BusinessException("商品库存不足");
			}
			rs.close();
			pst.close();
			
			sql="select s.number,p.number from shopping_car s,product_information p where s.product_id=p.product_id and s.product_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			rs=pst.executeQuery();
			if(rs.next()) {
				if(rs.getDouble(1)+Double.parseDouble(number)>rs.getDouble(2))throw new BusinessException("购物车已存在商品，再购买将导致商品库存不足");
				else {
					sql="update shopping_car set number = number+? where product_id=?";
					pst=conn.prepareStatement(sql);
					pst.setDouble(1, Double.parseDouble(number));
					pst.setString(2, productid);
					pst.execute();
					rs.close();
					pst.close();
				}
			}
			else {
				sql="insert into shopping_car(user_id,product_id,number) values(?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1,userid);
				pst.setString(2,productid);
				pst.setString(3,number);
				pst.execute();
				rs.close();
				pst.close();
			}
			
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
	public List<BeanShopping> loadAll(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanShopping> result=new ArrayList<BeanShopping>();
		Connection conn=null;
		  	try {
		  		String useridString=user.getUserId();
		  		conn=DBUtil.getConnection();
		  		
		  		String sql="select product_id,number from shopping_car where user_id=? order by user_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		pst.setString(1, useridString);
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanShopping bs=new BeanShopping();
		  			bs.setProductId(rs.getString(1));
		  			bs.setNumber(rs.getString(2));
			    	result.add(bs);
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
	public void deleteProduct(BeanShopping product) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="delete from shopping_car where product_id = ?";
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
