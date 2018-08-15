package pro.sunhao.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.util.JDBCUtils;
import pro.sunhao.util.WebUtils;

/**
 * 注册
 * @author Administrator
 *
 */
public class RegistServlet extends HttpServlet {
	
	/**
	 * 1.获取表单请求的参数
	 * 2.表单验证
	 * 3.执行对应逻辑(向数据库插入数据)
	 * 4.根据执行结果响应不同信息
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.处理乱码
		request.setCharacterEncoding("utf-8");		// 请求乱码
		response.setContentType("text/html; charset=utf-8");		// 应答乱码
		// 2.接收表单参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String valistr = request.getParameter("valistr");
		// 3.各种验证
		if(WebUtils.isEmpty(username)) {		// 用户名非空验证
			request.setAttribute("msg", "用户名不能为空");			// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;
		}
		if(WebUtils.isEmpty(password)) {
			request.setAttribute("msg", "密码不能为空");			// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;			
		}
		if(WebUtils.isEmpty(password2)) {
			request.setAttribute("msg", "确认密码不能为空");			// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;			
		}
		if(WebUtils.isEmpty(nickname)) {
			request.setAttribute("msg", "昵称不能为空");				// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;		}
		if(WebUtils.isEmpty(email)) {
			request.setAttribute("msg", "email不能为空");			// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;			
		}
		if(WebUtils.isEmpty(valistr)) {
			request.setAttribute("msg", "验证码不能为空");			// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;			
		}
		//System.out.println(password + "=" + password2 + "=" +  password.equals(password2));
		if(!password.equals(password2)) {							// 两次密码一致验证
			request.setAttribute("msg", "两次密码不一致");			// 添加错误信息
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 转发到regist.jsp
			return;					
		}
		String regex="^\\w+@\\w+(\\.\\w+)+$";						// 邮箱格式验证
		if(!email.matches(regex)){
			request.setAttribute("msg", "请输入正确的邮箱");			// 产生错误信息，添加到request作用域
			request.getRequestDispatcher("/regist.jsp").forward(request, response);			// 请求转发给regist.jsp
			return;
		}		
			// 验证码验证

			
		String sql1 = "select * from user where username=?";	// 用户名没有重复验证	
		Connection conn1 = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn1 = JDBCUtils.getConn();				// 获取数据库连接
			ps1 = conn1.prepareStatement(sql1);			// 传入sql语句
			ps1.setString(1, username);					// 传入需要查询的对象
			rs1 = ps1.executeQuery();					// 执行查询
			if(rs1.next()) {		// 查到了数据，用户名已存在
				request.setAttribute("msg", "用户名已存在");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn1, ps1, rs1);
		}

		// 4.执行业务逻辑 -- 将用户注册信息添加到数据库
		String sql2 = "insert into user values(null, ?, ?, ?, ?)";
		Connection conn2 = null;
		PreparedStatement ps2  = null;
		ResultSet rs2 = null;
		try {
			conn2 = JDBCUtils.getConn();
			ps2 = conn2.prepareStatement(sql2);
			ps2.setString(1, username);
			ps2.setString(2, password);
			ps2.setString(3, nickname);
			ps2.setString(4, email);
			int i = ps2.executeUpdate();
			// 5.根据业务逻辑执行结果，返回对应页面	
			if(i > 0) {		// 注册成功
				response.getWriter().write("注册成功");
				response.addHeader("refresh", "3, url=" + request.getContextPath() + "/index.jsp");
			} else {		// 注册失败
				request.setAttribute("msg", "注册失败");
				request.getRequestDispatcher("/regist.jsp").include(request, response);
			}			
		} catch (Exception e) {
			e.printStackTrace();	// 响应注册失败
		} finally {
			JDBCUtils.close(conn2, ps2, rs2);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
