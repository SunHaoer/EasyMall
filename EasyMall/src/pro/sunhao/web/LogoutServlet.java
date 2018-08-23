package pro.sunhao.web;

import java.io.IOException;
import java.net.HttpCookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制用户注销
 * @author Administrator
 *
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {		// 销毁用户绑定的session对象
			session.invalidate();
			Cookie cookie = new Cookie("autologin", "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");		// 重定向到首页
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
