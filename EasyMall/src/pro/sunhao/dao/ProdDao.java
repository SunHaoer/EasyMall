package pro.sunhao.dao;

import java.util.List;

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
	
	/**
	 * 查询所有商品信息的方法
	 * @return 商品信息列表
	 */
	List<Prod> listProd();
	
	/**
	 * 根据商品id更新商品数量
	 * @param pid
	 * @param pnum
	 * @return true(更新成功) || false(更新失败)
	 */
	boolean updatePnumByPid(int pid, int pnum);
	
	/**
	 * 根据商品编号查商品种类编号
	 * @param pid
	 * @return 商品种类编号cid
	 */
	int getCidByPid(Integer pid);
	
	/**
	 * 根据商品种类编号，获取商品列表
	 * @param cid
	 * @return 该种类的商品列表
	 */
	List<Prod> listProdByCid(int cid);
	
	/**
	 * 根据商品种类编号删除商品种类
	 * @param cid
	 * @return true(删除成功) || false(删除失败)
	 */
	boolean deleteProdCategroyByCid(int cid);
	
	/**
	 * 根据商品编号删除商品
	 * @param pid
	 * @return true(删除成功) || false(删除失败)
	 */
	boolean deleteProdByPid(Integer pid);
}
