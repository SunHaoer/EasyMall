package pro.sunhao.web;

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
 * 显示全部商品列表的servlet
 * @author Administrator
 *
 */
public class ProdListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("ProdListServlet");
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		List<Prod> list = service.listProd();
		request.setAttribute("prods", list);
		request.getRequestDispatcher("/prodlist.jsp").include(request, response);
		//response.sendRedirect(request.getContextPath() + "/prodlist.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
