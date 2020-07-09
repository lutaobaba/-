package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;
import cn.edu.zucc.fresh.itf.IUserManager;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;

public class UserManager implements IUserManager {

	@Override
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
			BeanUser bu=new BeanUser();
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select user_id,user_pwd from user_information where user_id=? and user_pwd=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, userid);
				pst.setString(2, pwd);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("请输入正确的账号密码");
				rs.close();
				pst.close();
				bu.setUserId(userid);
				bu.setUserPwd(pwd);
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
			return bu;
		}
	
	@Override
	public BeanUser reg(String userid, String username,String usersex,String pwd,String pwd2,String phone,String email, String city) throws BaseException {
		// TODO Auto-generated method stub
		if(userid==null || "".equals(userid)) throw new BusinessException("用户账号不能为空");
		if(username==null || "".equals(username)) throw new BusinessException("用户姓名不能为空");
		if(usersex==null || "".equals(usersex)) throw new BusinessException("用户性别不能为空");
		if(pwd==null || "".equals(pwd)) throw new BusinessException("密码不能为空");
		if(!(pwd.equals(pwd2))) throw new BusinessException("两次密码不一致");
		if(phone==null || "".equals(phone)) throw new BusinessException("电话不能为空");
		if(email==null || "".equals(email)) throw new BusinessException("邮箱不能为空");
		if(city==null || "".equals(city)) throw new BusinessException("所在城市不能为空");
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select *  from user_information where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("账号已存在");
			rs.close();
			pst.close();
			
			sql="insert into user_information(user_id,user_name,user_sex,user_pwd,phone,email,city,registration_time,vip,vip_end) values(?,?,?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, username);
			pst.setString(3, usersex);
			pst.setString(4, pwd);
			pst.setString(5, phone);
			pst.setString(6, email);
			pst.setString(7, city);
			pst.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setBoolean(9, false);
			pst.setTimestamp(10,new java.sql.Timestamp(System.currentTimeMillis()));
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
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(oldPwd==null || "".equals(oldPwd)) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd)) throw new BusinessException("新密码不能为空");
		if(!(newPwd2.equals(newPwd))) throw new BusinessException("两次密码不一致");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_pwd from user_information where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserId());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(!(oldPwd.equals(BeanUser.currentLoginUser.getUserPwd()))) throw new BusinessException("原始密码错误");
				sql="update user_information set user_pwd=？ where user_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newPwd);
				pst.setString(2, BeanUser.currentLoginUser.getUserId());
				pst.execute();
			}
			pst.close();
			rs.close();
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
	}

	@Override
	public void changeInfor(BeanUser user, String name, String sex, String phone, String email, String city)throws BaseException {
		// TODO Auto-generated method stub
		if(name==null || "".equals(name)) throw new BusinessException("新姓名不能为空");
		if(sex==null || "".equals(sex)) throw new BusinessException("新性别不能为空");
		if(phone==null || "".equals(phone)) throw new BusinessException("新电话不能为空");
		if(email==null || "".equals(email)) throw new BusinessException("新游戏不能为空");
		if(city==null || "".equals(city)) throw new BusinessException("新城市不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_pwd from user_information where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,BeanUser.currentLoginUser.getUserId());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				sql="update user_information set user_name=?,user_sex=?,phone=?,email=?,city=? where user_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, sex);
				pst.setString(3, phone);
				pst.setString(4, email);
				pst.setString(5, city);
				pst.setString(6, BeanUser.currentLoginUser.getUserId());
				pst.execute();
			}
			pst.close();
			rs.close();
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
		
		
	}
}
