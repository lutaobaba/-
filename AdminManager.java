package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;
import cn.edu.zucc.fresh.itf.IAdminManager;
import cn.edu.zucc.fresh.itf.IUserManager;
import cn.edu.zucc.fresh.model.BeanAdmin;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;

public class AdminManager implements IAdminManager{

	@Override
	public BeanAdmin login(String adminid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
			BeanAdmin ba=new BeanAdmin();
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select admin_id,admin_pwd from admin_information where admin_id=? and admin_pwd=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, adminid);
				pst.setString(2, pwd);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("请输入正确的账号密码");
				rs.close();
				pst.close();
				ba.setAdminId(adminid);
				ba.setAdminPwd(adminid);
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
			return ba;
		}

	@Override
	public BeanAdmin reg(String adminid,String adminname,String pwd, String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(adminid==null || "".equals(adminid)) throw new BusinessException("用户账号不能为空");
		if(adminname==null || "".equals(adminname)) throw new BusinessException("用户姓名不能为空");
		if(pwd==null || "".equals(pwd)) throw new BusinessException("密码不能为空");
		if(!(pwd.equals(pwd2))) throw new BusinessException("两次密码不一致");
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from admin_information where admin_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, adminid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("账号已存在");
			rs.close();
			pst.close();
			
			sql="insert into admin_information(admin_id,admin_name,admin_pwd) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, adminid);
			pst.setString(2, adminname);
			pst.setString(3, pwd);
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
	public void changePwd(BeanAdmin admin, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		
	}
}
