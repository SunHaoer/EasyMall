package pro.sunhao.service;

import pro.sunhao.domain.Prod;

public interface ProdService {
	
	/**
	 * 添加商品方法
	 * @param prod
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean addProd(Prod prod);
}
