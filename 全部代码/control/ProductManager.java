package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IProductManager;
import cn.edu.zucc.fresh.model.BeanFoldAss;
import cn.edu.zucc.fresh.model.BeanFoldInfor;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanProduct;
import cn.edu.zucc.fresh.model.BeanProductRecipe;
import cn.edu.zucc.fresh.model.BeanRecipe;
import cn.edu.zucc.fresh.model.BeanShopping;
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
			
			String sql="select product_id,number from product_information where product_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			java.sql.ResultSet rs=pst.executeQuery();
			//if(rs.next()) throw new BusinessException("商品编号已存在");
			if(rs.next()) {
				sql="update product_information set number = number + ? where product_id=?";
				pst=conn.prepareStatement(sql);
				pst.setDouble(1,Double.parseDouble(number));
				pst.setString(2, productid);
				pst.execute();
				rs.close();
				pst.close();
			}
			else {
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
	@Override
	public List<BeanProductRecipe> loadRecipe(BeanRecipe recipe) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanProductRecipe> result=new ArrayList<BeanProductRecipe>();
		Connection conn=null;
		try {
			String pr=recipe.getRecipeId();
			conn=DBUtil.getConnection();
			String sql="select * from product_recipe_recommendation where recipe_id=? order by product_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, pr);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanProductRecipe bpr=new BeanProductRecipe();
				bpr.setProductId(rs.getString(1));
				bpr.setRecipeId(rs.getString(2));
				bpr.setDes(rs.getString(3));
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
	public BeanProductRecipe addRecipe(String productid, BeanRecipe Recipe, String des)
			throws BaseException {
		// TODO Auto-generated method stub
		if(productid==null || "".equals(productid)) throw new BusinessException("商品编号不能为空");
		if(des==null || "".equals(des)) throw new BusinessException("描述不能为空");
		Connection conn=null;
		try {
			String prid=Recipe.getRecipeId();
			conn=DBUtil.getConnection();
			
			String sql="select product_id from product_recipe_recommendation where product_id=? and recipe_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			pst.setString(2, Recipe.getRecipeId());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("商品编号已存在");
			rs.close();
			pst.close();
			
			sql="select product_id from product_information where product_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("商品编号不存在");
			rs.close();
			pst.close();
			
			
			sql="insert into product_recipe_recommendation(product_id,recipe_id,des) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,productid);
			pst.setString(2,prid);
			pst.setString(3,des);
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
	public void deleteRecipe(BeanProductRecipe productRecipe) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="delete from product_recipe_recommendation where product_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,productRecipe.getProductId());
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
	@Override
	public BeanFoldAss addASS(String productid, BeanFoldInfor bfInfor) throws BaseException {
		// TODO Auto-generated method stub
		if(productid==null || "".equals(productid)) throw new BusinessException("商品编号不能为空");
		Connection conn=null;
		try {
			String bfid=bfInfor.getFoldId();
			conn=DBUtil.getConnection();
			
			String sql="select product_id from product_information where product_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, productid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("商品编号不存在");
			rs.close();
			pst.close();
			
			
			sql="insert into full_fold_association(product_id,fold_id) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,productid);
			pst.setString(2, bfid);
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
	public List<BeanFoldAss> loadAss(BeanFoldInfor bFoldInfor) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanFoldAss> result=new ArrayList<BeanFoldAss>();
		Connection conn=null;
		try {
			String bfiString=bFoldInfor.getFoldId();
			conn=DBUtil.getConnection();
			String sql="select * from full_fold_association where fold_id=? order by product_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, bfiString);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanFoldAss bfAss=new BeanFoldAss();
				bfAss.setProductId(rs.getString(1));
				bfAss.setFoldId(rs.getString(2));
			    result.add(bfAss);
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
	public void deleteAss(BeanFoldAss bfAss) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="delete from full_fold_association where product_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,bfAss.getProductId());
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
	@Override
	public List<BeanProduct> loadShoppings(BeanShopping shop) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			String shopString=shop.getProductId();
			conn=DBUtil.getConnection();
			String sql="select * from product_information where product_id=? order by product_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, shopString);
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
}
