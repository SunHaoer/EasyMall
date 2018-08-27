package pro.sunhao.service;

import java.sql.ResultSet;
import java.util.List;

import pro.sunhao.dao.ProdDao;
import pro.sunhao.domain.Prod;
import pro.sunhao.domain.ProdCategory;
import pro.sunhao.exception.MsgException;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.util.TransactionManager;

/**
 * 为Prod提供业务逻辑
 * @author Administrator
 *
 */
public class ProdServiceImpl implements ProdService {
	private ProdDao dao = BaseFactory.getFactory().getInstance(ProdDao.class);	
	private Boolean isAdd = false;
	
	@Override
	public boolean addProd(Prod prod) {
		int cid = -1;
		try {
			TransactionManager.startTransaction();
			try {
				cid = dao.getCidByCname(prod.getCname());		// 使用商品种类名称差prod_category表内是否存在该商品种类
			} catch (MsgException e) {
				e.printStackTrace();
				return false;
			}
			if(cid == -1) {			// 用户添加的商品种类不存在
				ProdCategory pc = new ProdCategory(-1, prod.getCname());		
				dao.insertProdCategory(pc);					// 向prod_category中添加一个新的商品种类
				try {
					cid = dao.getCidByCname(prod.getCname());		// 再查一次，获取商品种类cid
				} catch (MsgException e) {
					e.printStackTrace();
					return false;
				}
			}
			prod.setCid(cid);		// 将cid添加到prod对象	
			isAdd = dao.insertProd(prod);
			TransactionManager.commitTransaction();
		} catch (Exception e) {		// 数据库遇到异常则回滚事务
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
		} finally {					// 关闭连接
			TransactionManager.closeConn();
		}
		return isAdd;
	}

	@Override
	public List<Prod> listProd() {
		List<Prod> list = dao.listProd();
		return list;
	}

}
