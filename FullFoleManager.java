package cn.edu.zucc.fresh.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.fresh.itf.IFullFoldManager;
import cn.edu.zucc.fresh.model.BeanFoldInfor;
import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.util.BaseException;
import cn.edu.zucc.fresh.util.BusinessException;
import cn.edu.zucc.fresh.util.DBUtil;
import cn.edu.zucc.fresh.util.DbException;

public class FullFoleManager implements IFullFoldManager{

	@Override
	public BeanFoldInfor addFold(String foldid, String content, String number, String discount,String start, String end)
			throws BaseException {
		// TODO Auto-generated method stub
		if(foldid==null || "".equals(foldid)) throw new BusinessException("满折编号不能为空");
		if(content==null || "".equals(content)) throw new BusinessException("满折内容不能为空");
		if(number==null || "".equals(number)) throw new BusinessException("适用商品数量不能为空");
		if(discount==null || "".equals(discount)) throw new BusinessException("折扣不能为空");
		if(start==null || "".equals(start)) throw new BusinessException("起始时间不能为空");
		if(end==null || "".equals(end)) throw new BusinessException("结束时间不能为空");
		
		BeanFoldInfor bf=new BeanFoldInfor();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from full_fold_information where fold_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, foldid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("编号已存在");
			rs.close();
			pst.close();
			
			sql="insert into full_fold_information(fold_id,content,number,discount,start_date,end_date) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,foldid);
			pst.setString(2,content);
			pst.setString(3,number);
			pst.setString(4,discount);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			pst.setTimestamp(5,new Timestamp(format.parse(start).getTime()));
			pst.setTimestamp(6,new Timestamp(format.parse(end).getTime()));
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
		return bf;
	}

	@Override
	public List<BeanFoldInfor> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<BeanFoldInfor> result=new ArrayList<BeanFoldInfor>();
		Connection conn=null;
		  	try {
		  		conn=DBUtil.getConnection();
		  		String sql="select * from full_fold_information order by fold_id";
		  		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		  		//pst.setString(1, BeanFreshKind.currentLoginUser.getKindId());
		  		java.sql.ResultSet rs=pst.executeQuery();
		  		while(rs.next()) {
		  			BeanFoldInfor bf=new BeanFoldInfor();
		  			bf.setFoldId(rs.getString(1));
		  			bf.setContent(rs.getString(2));
		  			bf.setNumber(rs.getString(3));
		  			bf.setDiscount(rs.getString(4));
		  			bf.setStartTime(rs.getTimestamp(5));
		  			bf.setEndTime(rs.getTimestamp(6));
			    	result.add(bf);
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
	public void deleteFold(BeanFoldInfor foldInfor) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
