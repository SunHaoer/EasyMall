package pro.sunhao.service;

import java.util.List;

import pro.sunhao.domain.Prod;

public interface ProdService {
	
	/**
	 * 添加商品方法
	 * @param prod
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean addProd(Prod prod);
	
	/**
	 * 返回商品列表
	 * @return 商品列表
	 */
	List<Prod> listProd();
}
