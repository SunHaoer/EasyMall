package pro.sunhao.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.UserService;

/**
 * 实现自动登录的filter
 * @author Administrator
 *
 */
public class AutoLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =  (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if(req.getSession() == null || req.getSession().getAttribute("user") == null) {	// 未登录状态
			//System.out.println(req.getSession().getAttribute("user"));
			//System.out.println("没有登录");
			Cookie[] cookies = req.getCookies();
			Cookie autoLoginCookie = null;
			if(cookies != null) {
				for(Cookie c : cookies) {
					if("autologin".equals(c.getName())) {		
						autoLoginCookie = c;
					}
				}				
			}
			if(autoLoginCookie != null) {			// 存在autoLogin
				//System.out.println("我要自动登录");
				//System.out.println(autoLoginCookie.getValue());
				String username = autoLoginCookie.getValue().split("#")[0];
				username = URLEncoder.encode(username, req.getServletContext().getInitParameter("encode"));
				String password = autoLoginCookie.getValue().split("#")[1];
				//System.out.println(username + " " + password);
				UserService service = BaseFactory.getFactory().getInstance(UserService.class);
				try {
					User user = service.login(username, password);
					if(user != null) {
						System.out.println("登录成功");
						req.getSession().setAttribute("user", user);
					}
				} catch (MsgException e) {
					e.printStackTrace();
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
