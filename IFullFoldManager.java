package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanFoldInfor;
import cn.edu.zucc.fresh.util.BaseException;

public interface IFullFoldManager {
	public BeanFoldInfor addFold(String foldid, String content,String number,String discount,String start,String end) throws BaseException;

	public List<BeanFoldInfor> loadAll()throws BaseException;

	public void deleteFold(BeanFoldInfor foldInfor)throws BaseException;
	
	
}
