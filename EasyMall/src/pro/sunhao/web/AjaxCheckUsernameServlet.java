package pro.sunhao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.service.UserService;
import pro.sunhao.service.UserServiceImpl;

/**
 * 用于响应AJAX的前端判断用户名是否为空
 * @author Administrator
 *
 */

public class AjaxCheckUsernameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ServletContext sc = this.getServletContext();
		//String encode = sc.getInitParameter("encode");
		//request.setCharacterEncoding(encode);		// 请求乱码
		//response.setContentType("text/html; charset=" + encode);		// 应答乱码
		String username = request.getParameter("username");			// 获取请求参数
		//username = new String(username.getBytes("iso8859-1"), encode);
		
		
		UserService service = new UserServiceImpl();
		boolean hasUsername = service.hasUsername(username);
		if(hasUsername) {		// 用户名已存在
			response.getWriter().write("用户名已存在");
		} else {				// 用户名不存在
			response.getWriter().write("用户名可以使用");
		}
		
/*		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where username=?";
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				response.getWriter().write("用户名已存在");
			} else {
				response.getWriter().write("用户名可以使用");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
