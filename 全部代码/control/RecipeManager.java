package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IRecipeManager;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanRecipe;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;

public class RecipeManager implements IRecipeManager{

	@Override
	public BeanRecipe addRecipe(String recipeid, String recipename, String recipeuse, String step)
			throws BaseException {
		// TODO Auto-generated method stub
		if(recipeid==null || "".equals(recipeid)) throw new BusinessException("菜谱编号不能为空");
		if(recipename==null || "".equals(recipename)) throw new BusinessException("菜谱名称不能为空");
		if(recipeuse==null || "".equals(recipeuse)) throw new BusinessException("菜谱用料不能为空");
		if(step==null || "".equals(step)) throw new BusinessException("步骤不能为空");
		
		BeanRecipe br=new BeanRecipe();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from recipe_information where recipe_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, recipeid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("编号已存在");
			rs.close();
			pst.close();
			
			sql="insert into recipe_information(recipe_id,recipe_name,recipe_use,step) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,recipeid);
			pst.setString(2,recipename);
			pst.setString(3,recipeuse);
			pst.setString(4, step);
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
		return br;
	}

	@Override
	public List<BeanRecipe> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanRecipe> result=new ArrayList<BeanRecipe>();
		Connection conn=null;
		  	try {
		  		conn=DBUtil.getConnection();
		  		String sql="select * from recipe_information order by recipe_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		//pst.setString(1, BeanFreshKind.currentLoginUser.getKindId());
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanRecipe br=new BeanRecipe();
		  			br.setRecipeId(rs.getString(1));
		  			br.setRecipeName(rs.getString(2));
		  			br.setRecipeUse(rs.getString(3));
		  			br.setStep(rs.getString(4));
			    	result.add(br);
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
	public void deleteRecipe(BeanRecipe recipe) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="select count(*) from product_recipe_recommendation where recipe_id= ?";
			java.sql.PreparedStatement  pst=conn.prepareStatement(sql);
			pst.setString(1, recipe.getRecipeId());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					rs.close();
					pst.close();
					throw new BusinessException("菜谱已经存在商品，不能删除");
				}
			}
			rs.close();
			pst.close();
			
			sql="delete from recipe_information where recipe_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,recipe.getRecipeId());
			pst.execute();
			pst.close();
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	}

}
