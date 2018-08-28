package pro.sunhao.service;

import java.util.ArrayList;
import java.util.List;

import pro.sunhao.dao.CartDao;
import pro.sunhao.dao.ProdDao;
import pro.sunhao.domain.Prod;
import pro.sunhao.factory.BaseFactory;

public class CartServiceImpl implements CartService {
	private CartDao dao = BaseFactory.getFactory().getInstance(CartDao.class);	

	@Override
	public boolean addProdToCart(Prod prod, String username) {
		boolean isAdd = false;
		//dao.addProdToCartByProd(prod.getId(), prod.getName(), username);
		//System.out.println(prod.getId() + " " + prod.getName() + " " + username);
		String str = dao.getCidByPidInCart(prod.getName(), username);				// 查询商品在购物车的编号
		if("#".equals(str)) {			// 该商品不存在
			isAdd = dao.addProdToCartByProd(prod.getId(), prod.getName(), username);
		} else {
			int id = Integer.parseInt(str.split("#")[0]);		// 购物车中的商品id
			int num = Integer.parseInt(str.split("#")[1]) + 1;		// 购物车中该商品的数量
			System.out.println(num + " " + id);
			isAdd = dao.updatePnumByPidInCart(id, num, username);
			//System.out.println(isAdd);
		}	
		return isAdd;
	}

	@Override
	public List<Prod> listProdInCart(String username) {
		List<Prod> list = new ArrayList<Prod>();
		list = dao.getListProdInCart(username);
		return list;
	}
}
