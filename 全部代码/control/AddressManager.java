package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IAddressManager;
import cn.edu.zucc.fresh.model.BeanAddress;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;

public class AddressManager implements IAddressManager{

	@Override
	public BeanAddress addAddress(String addressid, BeanUser user, String province, String city, String area,
			String address, String username, String phone) throws BaseException {
		// TODO Auto-generated method stub
		if(addressid==null || "".equals(addressid)) throw new BusinessException("地址编号不能为空");
		if(province==null || "".equals(province)) throw new BusinessException("省不能为空");
		if(city==null || "".equals(city)) throw new BusinessException("市不能为空");
		if(area==null || "".equals(area)) throw new BusinessException("区不能为空");
		if(address==null || "".equals(address)) throw new BusinessException("地址不能为空");
		if(username==null || "".equals(username)) throw new BusinessException("收货人不能为空");
		if(phone==null || "".equals(phone)) throw new BusinessException("电话不能为空");

		Connection conn=null;
		try {
			String useridString=user.getUserId();
			conn=DBUtil.getConnection();
			
			String sql="select * from delivery_address where address_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, addressid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("地址编号已存在");
			rs.close();
			pst.close();
			
			sql="insert into delivery_address(address_id,user_id,province,city,area,address,user_name,phone) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,addressid);
			pst.setString(2,useridString);
			pst.setString(3,province);
			pst.setString(4,city);
			pst.setString(5,area);
			pst.setString(6,address);
			pst.setString(7,username);
			pst.setString(8,phone);
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
	public List<BeanAddress> loadAll(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanAddress> result=new ArrayList<BeanAddress>();
		Connection conn=null;
		  	try {
		  		String userid=user.getUserId();
		  		conn=DBUtil.getConnection();
		  		String sql="select * from delivery_address where user_id=? order by address_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		pst.setString(1, userid);
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanAddress ba=new BeanAddress();
		  			ba.setAddressId(rs.getString(1));
		  			ba.setUserId(rs.getString(2));
		  			ba.setProvince(rs.getString(3));
		  			ba.setCity(rs.getString(4));
		  			ba.setArea(rs.getString(5));
		  			ba.setAddress(rs.getString(6));
		  			ba.setUserName(rs.getString(7));
		  			ba.setPhone(rs.getString(8));
			    	result.add(ba);
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
	public void deleteAddress(BeanAddress address) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			String sql="delete from delivery_address where address_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, address.getAddressId());
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
