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
	public static IUserManager userManager=new UserManager();//��Ҫ����������Ƶ�ʵ����
	public static IAdminManager adminManager=new AdminManager();//��Ҫ����������Ƶ�ʵ����\
	public static IProductManager productManager=new ProductManager();//��Ҫ����������Ƶ�ʵ����
	public static IKindManager kindManager=new KindManager();//��Ҫ����������Ƶ�ʵ����
	public static IAddressManager addressManager=new AddressManager();//��Ҫ����������Ƶ�ʵ����
	public static IRecipeManager recipeManager=new RecipeManager();//��Ҫ����������Ƶ�ʵ����
	public static IFullFoldManager fullFoldManager=new FullFoleManager();//��Ҫ����������Ƶ�ʵ����
	public static IShoppingManager shoppingManager=new ShoppingManager();//��Ҫ����������Ƶ�ʵ����
	public static ISettlementManager settlementManager=new SettlementManager();//��Ҫ����������Ƶ�ʵ����
	public static ICouponManager couponManager=new CouponManager();//��Ҫ����������Ƶ�ʵ����
	public static IReviewManager reviewManager=new ReviewManager();//��Ҫ����������Ƶ�ʵ����
}