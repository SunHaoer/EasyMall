package pro.sunhao.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.ProdService;


/**
 * 响应用户删除商品的servlet
 * @author Administrator
 *
 */
public class ManageDeleteProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer pid = Integer.parseInt(request.getParameter("pid"));
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		service.deleteProd(pid);
		response.getWriter().write("删除成功");
		response.setHeader("refresh", "2;url=" + request.getContextPath() + "/backend/_right.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
