package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.ISettlementManager;
import cn.edu.zucc.fresh.model.BeanComOrder;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.model.BeanShopping;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;
import cn.edu.zucc.fresh.model.BeanCoupon;

public class SettlementManager implements ISettlementManager{

	public double totalprice1() {
		double sum = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select s.number,p.price \r\n" + 
					"from shopping_car s,product_information p\r\n" + 
					"where s.user_id = ?\r\n" + 
					"and s.product_id = p.product_id";
			
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserId());
			java.sql.ResultSet rs = pst.executeQuery();
			
			while(rs.next()) 
				sum = sum + rs.getDouble(1)*rs.getDouble(2);

			rs.close();
			pst.close();
			
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.rollback();
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
		return sum;
	}

	//找满折商品算钱
		public double totalprice4() {
			double sum4=0;
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				conn.setAutoCommit(false);
				
				String sql = "select s.number,p.price,fi.number,fi.discount \r\n" + 
						"from shopping_car s,product_information p,full_fold_association fa,full_fold_information fi\r\n" + 
						"where s.user_id = ?\r\n" + 
						"and s.product_id = p.product_id and fa.product_id = s.product_id and fa.fold_id = fi.fold_id";
				
				java.sql.PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, BeanUser.currentLoginUser.getUserId());
				java.sql.ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					if(rs.getDouble(1)>rs.getDouble(3)) {
						sum4=sum4+rs.getDouble(1)*rs.getDouble(2)*rs.getDouble(4)*0.1;
					}
					else {
						sum4=sum4+rs.getDouble(1)*rs.getDouble(2);
					}
				}
				rs.close();
				pst.close();
				
				conn.commit();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(conn != null)
					try {
						conn.rollback();
						conn.close();
					} catch(SQLException e) {
						e.printStackTrace();
					}
			}
			
			return sum4;
		}
		//不是满折商品算钱
		public double totalprice5() {
			List<BeanShopping> bShoppings= new ArrayList<BeanShopping>();
			int count=0;
			double sum5 = 0;
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				conn.setAutoCommit(false);
				
				String sql="select s.product_id\r\n" + 
						"from shopping_car s\r\n" + 
						"where s.user_id = ?\r\n" + 
						"and NOT EXISTS\r\n" + 
						"(SELECT full_fold_association.product_id \r\n" + 
						"FROM full_fold_association \r\n" + 
						"WHERE full_fold_association.product_id =s.product_id \r\n" + 
						")";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, BeanUser.currentLoginUser.getUserId());
				java.sql.ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					BeanShopping p =new BeanShopping();
					p.setProductId(rs.getString(1));
					bShoppings.add(p);
					count++;
				}
				
				for(int i=0;i<count;i++) {
					sql="select s.number,p.price \r\n" + 
							"from shopping_car s,product_information p \r\n" + 
							"where s.user_id = ? \r\n" + 
							"and s.product_id = p.product_id and p.product_id=? ";
					pst=conn.prepareStatement(sql);
					pst.setString(1, BeanUser.currentLoginUser.getUserId());
					pst.setString(2, bShoppings.get(i).getProductId());
					rs=pst.executeQuery();
					if(rs.next()) {
							sum5= sum5 + rs.getDouble(1)*rs.getDouble(2);
					}
				}
				
				
				rs.close();
				pst.close();
				conn.commit();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(conn != null)
					try {
						conn.rollback();
						conn.close();
					} catch(SQLException e) {
						e.printStackTrace();
					}
			}
			return sum5;
		}
	
	public void addComOrder(String addressid,String couponid) throws BaseException {
		if(addressid==null || "".equals(addressid)) throw new BusinessException("地址编号不能为空");
		// TODO Auto-generated method stub
		List<BeanShopping> car = new ArrayList<BeanShopping>();
		double oldm=totalprice1();
		double newm=0;
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			//自动生成订单号
			int orderid =1;
			String sql = "select max(order_id) from commodity_orders";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())
				orderid = rs.getInt(1) + 1;
			else
				orderid = 1;
			
			rs.close();
			pst.close();
			
			//找优惠券减免金额
			sql="select relief_amount from coupon where coupon_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, couponid);
			rs=pst.executeQuery();
			if(rs.next()) {
				BeanCoupon coupon=new BeanCoupon();
				coupon.setReliefAmount(rs.getDouble(1));
				newm=totalprice4()+totalprice5()-rs.getDouble(1);
			}
			else {
				newm=totalprice4()+totalprice5();
			}
			rs.close();
			pst.close();
			
			//找出购物车商品
			int count=0;
			sql="select product_id from shopping_car";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				BeanShopping bs = new BeanShopping();
				bs.setProductId(rs.getString(1));
				car.add(bs);
				count++;
			}
			rs.close();
			pst.close();
			
			//入库订单表
			sql="insert into commodity_orders(order_id,product_id,user_id,address_id,old_money,new_money,coupon_id,request_time,order_status) value(?,?,?,?,?,?,?,?,?)";
			for(int i=0;i<count;i++) {
				pst=conn.prepareStatement(sql);
				pst.setInt(1,orderid);
				pst.setString(2, car.get(i).getProductId());
				pst.setString(3,BeanUser.currentLoginUser.getUserId());
				pst.setString(4,addressid);
				pst.setDouble(5,oldm);
				pst.setDouble(6,newm);
				pst.setString(7,couponid);
				pst.setDate(8, new java.sql.Date(System.currentTimeMillis()));
				pst.setString(9, "已送达");
				pst.execute();
			}
			pst.close();
			
			sql="select product_id,number from shopping_car where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserId());
			rs=pst.executeQuery();
			while(rs.next()){
				String sql1="update product_information set number=number-? where product_id=? ";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setDouble(1, rs.getDouble(2));
				pst1.setString(2, rs.getString(1));
				pst1.execute();
				pst1.close();
			}
			
			sql = "delete from shopping_car where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUserId());
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
		return;
	}
	@Override
	public List<BeanComOrder> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanComOrder> result=new ArrayList<BeanComOrder>();
		Connection conn=null;
		  	try {
		  		conn=DBUtil.getConnection();
		  		String sql="select * from commodity_orders order by order_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanComOrder bco=new BeanComOrder();
		  			bco.setOrderId(rs.getString(1));
		  			bco.setProductId(rs.getString(2));
		  			bco.setUserId(rs.getString(3));
		  			bco.setAddressId(rs.getString(4));
		  			bco.setOldMoney(rs.getString(5));
		  			bco.setNewMoney(rs.getString(6));
		  			bco.setCouponId(rs.getString(7));
		  			bco.setRequestTime(rs.getDate(8));
		  			bco.setOrderStatus(rs.getString(9));
			    	result.add(bco);
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
	public List<BeanComOrder> loadMine(BeanUser user) throws BaseException {
		// TODO Auto-generated method stub
		List<BeanComOrder> result=new ArrayList<BeanComOrder>();
		Connection conn=null;
		  	try {
		  		conn=DBUtil.getConnection();
		  		String sql="select * from commodity_orders where user_id=? order by order_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		pst.setString(1, user.getUserId());
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanComOrder bco=new BeanComOrder();
		  			bco.setOrderId(rs.getString(1));
		  			bco.setProductId(rs.getString(2));
		  			bco.setUserId(rs.getString(3));
		  			bco.setAddressId(rs.getString(4));
		  			bco.setOldMoney(rs.getString(5));
		  			bco.setNewMoney(rs.getString(6));
		  			bco.setCouponId(rs.getString(7));
		  			bco.setRequestTime(rs.getDate(8));
		  			bco.setOrderStatus(rs.getString(9));
			    	result.add(bco);
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
}
