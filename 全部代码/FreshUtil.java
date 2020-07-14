package cn.edu.zucc.fresh;

import cn.edu.zucc.fresh.control.AddressManager;
import cn.edu.zucc.fresh.control.AdminManager;
import cn.edu.zucc.fresh.control.CouponManager;
import cn.edu.zucc.fresh.control.FullFoleManager;
import cn.edu.zucc.fresh.control.KindManager;
import cn.edu.zucc.fresh.control.ProductManager;
import cn.edu.zucc.fresh.control.RecipeManager;
import cn.edu.zucc.fresh.control.ReviewManager;
import cn.edu.zucc.fresh.control.SettlementManager;
import cn.edu.zucc.fresh.control.ShoppingManager;
import cn.edu.zucc.fresh.control.UserManager;
import cn.edu.zucc.fresh.itf.IAddressManager;
import cn.edu.zucc.fresh.itf.IAdminManager;
import cn.edu.zucc.fresh.itf.ICouponManager;
import cn.edu.zucc.fresh.itf.IFullFoldManager;
import cn.edu.zucc.fresh.itf.IProductManager;
import cn.edu.zucc.fresh.itf.IRecipeManager;
import cn.edu.zucc.fresh.itf.IReviewManager;
import cn.edu.zucc.fresh.itf.ISettlementManager;
import cn.edu.zucc.fresh.itf.IShoppingManager;
import cn.edu.zucc.fresh.itf.IUserManager;
import cn.edu.zucc.fresh.itf.IKindManager;

public class FreshUtil {
	public static IUserManager userManager=new UserManager();//需要换成自行设计的实现类
	public static IAdminManager adminManager=new AdminManager();//需要换成自行设计的实现类\
	public static IProductManager productManager=new ProductManager();//需要换成自行设计的实现类
	public static IKindManager kindManager=new KindManager();//需要换成自行设计的实现类
	public static IAddressManager addressManager=new AddressManager();//需要换成自行设计的实现类
	public static IRecipeManager recipeManager=new RecipeManager();//需要换成自行设计的实现类
	public static IFullFoldManager fullFoldManager=new FullFoleManager();//需要换成自行设计的实现类
	public static IShoppingManager shoppingManager=new ShoppingManager();//需要换成自行设计的实现类
	public static ISettlementManager settlementManager=new SettlementManager();//需要换成自行设计的实现类
	public static ICouponManager couponManager=new CouponManager();//需要换成自行设计的实现类
	public static IReviewManager reviewManager=new ReviewManager();//需要换成自行设计的实现类
}