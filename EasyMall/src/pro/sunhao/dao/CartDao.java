package pro.sunhao.dao;

import java.util.List;

import pro.sunhao.domain.Prod;

public interface CartDao {
	/**
	 * 根据商品名称和商品编号，添加进购物车
	 * @param prod
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean addProdToCartByProd(int pid, String pname, String username);
	
	/**
	 * 根据商品名称查询当前购物车中是否存在该商品
	 * @param id
	 * @param username
	 * @return pid + "#" + pnum (存在该商品则返回pid和pnum) || "#" (不存在该商品)
	 */
	String getCidByPidInCart(String pname, String username);
	
	/**
	 * 更新购物车中该商品的数量
	 * @param id
	 * @param num
	 * @param username
	 */
	boolean updatePnumByPidInCart(int id, int num, String username);
	
	/**
	 * 获取用户购物车内的商品列表
	 * @param username
	 * @return list
	 */
	List<Prod> getListProdInCart(String username);
}
