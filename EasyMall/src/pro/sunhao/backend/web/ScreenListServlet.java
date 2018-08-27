package pro.sunhao.backend.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.domain.Prod;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.ProdService;

/**
 * 筛选商品的servlet
 * @author Administrator
 *
 */
public class ScreenListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list = service.listProd();	
		String name = request.getParameter("name").trim();
		String category = request.getParameter("category").trim();
		String minprice = request.getParameter("minprice").trim();
		String maxprice = request.getParameter("maxprice").trim();
		System.out.println(name + "-" + category + "-" + minprice + "-" + maxprice);
		
		if(!"".equals(name)) {				// 筛选商品名称
			for(int i = 0; i < list.size(); i++) {
				Prod prod = list.get(i);
				if(!name.equals(prod.getName())) {
					list.remove(i);
				}
			}			
		}
		if(!"".equals(category)) {			// 筛选商品种类
			System.out.println(category);
			for(int i = 0; i < list.size(); i++) {
				Prod prod = list.get(i);
				if(!category.equals(prod.getCname())) {
					list.remove(i);
				}
			}						
		}
		if(!"".equals(minprice)) {			// 筛选最低价格
			try {
				double price = Double.parseDouble(request.getParameter("minprice"));
				for(int i = 0; i < list.size(); i++) {
					Prod prod = list.get(i);
					if(prod.getPnum() < price) {
						list.remove(i);
					}
				}							
			} catch (Exception e) {
			}
		}
		if(!"".equals(maxprice)) {			// 筛选最高价格
			try {
				double price = Double.parseDouble(request.getParameter("maxprice"));
				for(int i = 0; i < list.size(); i++) {
					Prod prod = list.get(i);
					if(prod.getPnum() < price) {
						list.remove(i);
					}
				}							
			} catch (Exception e) {
			}
		}
		//System.out.println(list);
		request.setAttribute("prods", list);
		request.getRequestDispatcher("/prodlist.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
