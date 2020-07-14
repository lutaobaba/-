package cn.edu.zucc.fresh.itf;

import java.util.List;

import cn.edu.zucc.fresh.model.BeanFreshKind;
import cn.edu.zucc.fresh.util.BaseException;

public interface IKindManager {

	public BeanFreshKind addKind(String kindid, String kindname,String kinddes) throws BaseException;

	public List<BeanFreshKind> loadAll()throws BaseException;
	
	public void deleteKind(BeanFreshKind kind)throws BaseException;
}
