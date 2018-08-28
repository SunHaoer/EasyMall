package pro.sunhao.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.factory.UserServiceFactory;
import pro.sunhao.service.UserService;
import pro.sunhao.service.UserServiceImpl;
import pro.sunhao.util.JDBCUtils;
import sun.awt.RepaintArea;


/**
 * 控制用户登录
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 处理乱码
		String encode = this.getServletContext().getInitParameter("encode");
		//request.setCharacterEncoding(encode);
		//response.setContentType("text/html; charset=" + encode);
		// 获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remname = request.getParameter("remname");
		String autoLogin = request.getParameter("autologin");
		// 表单验证(不需要)
		
		// 实现记住用户名
		Cookie cookie = new Cookie("remname", URLEncoder.encode(username, encode));
		//System.out.println(cookie.getValue());
		//Cookie cookie = new Cookie("remname", username);
		if(remname != null) {			// 勾选了记住用户名
			cookie.setMaxAge(60 * 60 * 24 * 30);
		} else {						// 没勾选记住用户名
			cookie.setMaxAge(0);
		}
		cookie.setPath(request.getContextPath() + "/");
		response.addCookie(cookie);
		
		//UserService service = new UserServiceImp();
		//UserService service = UserServiceFactory.getUserServiceFactory().getInstance();
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		User user = null;
		try {
			user = service.login(username, password);
		} catch (MsgException e) {
			e.printStackTrace();
		}
		if(user != null) {		// 登录成功
			cookie = new Cookie("autologin", URLEncoder.encode(username, encode) + "#" + password);
			if("true".equals(autoLogin)) {	//自动登录
				cookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				cookie.setMaxAge(0);
			}
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
			
			HttpSession session =  request.getSession();
			session.setAttribute("user", user);		// 保存用户登录状态
			// 保证关闭浏览器重启后session依旧生效
			Cookie c = new Cookie("JSESSIONID", session.getId());
			c.setMaxAge(60 * 30);
			c.setPath(request.getContextPath() + "/");
			response.getWriter().write("登录成功");
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");	
			return;			
		} else {				// 登录失败
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;			
		}
		
/*		
		// 实现登录逻辑
		String sql = "select * from user where username=? and password=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			// 根据登录结果返回对应内容
			if(rs.next()) {				// 用户名密码正确
				HttpSession session =  request.getSession();
				session.setAttribute("user", username);		// 保存用户登录状态
				// 保证关闭浏览器重启后session依旧生效
				Cookie c = new Cookie("JESSION", session.getId());
				c.setMaxAge(60 * 30);
				c.setPath(request.getContextPath() + "/");
				response.getWriter().write("登录成功");
				response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");	
				return;
			} else {					// 用户名密码不正确
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "登录失败");
			request.getRequestDispatcher("/login.jsp").include(request, response);
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
