package pro.sunhao.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.domain.Prod;
import pro.sunhao.domain.User;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.CartService;

public class UserCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartService service = BaseFactory.getFactory().getInstance(CartService.class);
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user);
		if(user == null) {		// 未登录
			response.sendRedirect("/login.jsp");
		} else {
			List<Prod> list = service.listProdInCart(user.getUsername());
			System.out.println(list);
			request.setAttribute("prods", list);
			request.getRequestDispatcher("/cart/cart.jsp").include(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
