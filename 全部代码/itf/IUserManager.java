package cn.edu.zucc.fresh.itf;

import cn.edu.zucc.fresh.model.BeanUser;
import cn.edu.zucc.fresh.util.BaseException;

public interface IUserManager {
	/**

	 */
	public BeanUser reg(String userid, String username,String usersex,String pwd,String pwd2,String phone,String email, String city) throws BaseException;
	/**

	 */
	public BeanUser login(String userid,String pwd)throws BaseException;
	/**

	 */
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	
	public void changeInfor(BeanUser user, String name,String sex, String phone,String email,String city)throws BaseException;
}
