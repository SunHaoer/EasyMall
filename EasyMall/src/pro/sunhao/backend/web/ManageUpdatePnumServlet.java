package pro.sunhao.backend.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.ProdService;

/**
 * 响应用户更新商品数量的servlet
 * @author Administrator
 *
 */
public class ManageUpdatePnumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		int pid = Integer.parseInt(request.getParameter("pid"));
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		boolean isUpdate = service.updatePnum(pid, pnum);
		if(isUpdate) {
			response.getWriter().write("修改成功");
		} else {
			response.getWriter().write("修改失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
