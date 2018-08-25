package pro.sunhao.dao;

import pro.sunhao.domain.Prod;
import pro.sunhao.domain.ProdCategory;
import pro.sunhao.exception.MsgException;

public interface ProdDao {
	
	/**
	 * 通过商品种类名称获取商品种类id的方法
	 * @param cname 商品种类名
	 * @return 商品种类的id || -1(不存在)
	 * @throws MsgException
	 */
	int getCidByCname(String cname) throws MsgException;
	
	/**
	 * 添加商品种类的方法
	 * @param pc
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean insertProdCategory(ProdCategory pc);
	
	/**
	 * 添加商品信息的方法
	 * @param prod
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean insertProd(Prod prod);
}
