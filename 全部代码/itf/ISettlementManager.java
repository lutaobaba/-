package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanComOrder;
import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

public interface ISettlementManager {

	public List<BeanComOrder> loadAll()throws BaseException;
	
	public List<BeanComOrder> loadMine(BeanUser user)throws BaseException;
}
