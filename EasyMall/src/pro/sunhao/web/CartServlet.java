package pro.sunhao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.domain.Prod;
import pro.sunhao.domain.User;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.CartService;
import pro.sunhao.service.ProdService;

public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {			// 未登录
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		Prod prod = new Prod();
		String strs[] = request.getParameter("prod").split(",");
		prod.setId(Integer.parseInt(strs[0].split("=")[1]));
		prod.setName(strs[1].split("=")[1]);
		prod.setPrice(Double.parseDouble(strs[2].split("=")[1]));
		prod.setCid(Integer.parseInt(strs[3].split("=")[1]));
		prod.setPnum(Integer.parseInt(strs[4].split("=")[1]));
		prod.setImgurl(strs[5].split("=")[1]);
		prod.setDescription(strs[6].split("=")[1]);
		prod.setCname(strs[7].split("=")[1].substring(0, strs[7].split("=")[1].length() - 2));
		//System.out.println(prod.toString());
		
		CartService service = BaseFactory.getFactory().getInstance(CartService.class);
		service.addProdToCart(prod, user.getUsername());
		response.sendRedirect("ProdListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
