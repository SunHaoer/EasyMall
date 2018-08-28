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
	
	/**
	 * 更新商品数量
	 * @param pid
	 * @param pnum
	 * @return true(更新成功) || false(更新失败)
	 */
	boolean updatePnum(int pid, int pnum);

	/**
	 * 根据id删除商品，如果该种类只有此一个商品，则删除商品种类
	 * @param pid
	 * @return true(删除成功) || false(删除失败)
	 */
	boolean deleteProd(Integer pid);
}
