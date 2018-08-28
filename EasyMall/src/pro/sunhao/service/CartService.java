package pro.sunhao.service;

import java.util.List;

import pro.sunhao.domain.Prod;

public interface CartService {
	/**
	 * 向购物车添加商品
	 * @param prod
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean addProdToCart(Prod prod, String username);
	
	/**
	 * 获取该用户购物车内的商品列表
	 * @param username
	 * @return list
	 */
	List<Prod> listProdInCart(String username);
	
}
