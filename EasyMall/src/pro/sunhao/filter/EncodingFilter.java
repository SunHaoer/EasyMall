package pro.sunhao.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * filter拦截器，处理全局乱码
 * @author Administrator
 *
 */
public class EncodingFilter implements Filter {
	private String encode;		// web应用配置的编码
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encode = filterConfig.getServletContext().getInitParameter("encode");
		this.encode = encode;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 解决应答乱码
		response.setContentType("text/html;charset=" + encode);
		// 解决请求乱码
		MyRequest req = new MyRequest((HttpServletRequest)request);
/*		
		Map<String, String[]>  parameterMap = request.getParameterMap();
		if(parameterMap != null) {
			for(Entry<String, String[]> entry : parameterMap.entrySet()) {
				String str[] = entry.getValue();
				for(int i = 0; i < str.length; i++) {
					
					str[i] = new String(str[i].getBytes("iso8859-1"), encode);
					//System.out.println("---" + str[i]);
				}
			}
		}
*/
		//System.out.println(response == null);
		chain.doFilter(req, response);
	}	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Request的修饰类
	 * @author Administrator
	 *
	 */
	class MyRequest extends HttpServletRequestWrapper {
		private boolean hasEncode = false;		// 第一次默认没有编解码

		public MyRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			if(getParameterMap().get(name) == null) return null;
			else return getParameterMap().get(name)[0];
		}

		@Override
		public Map<String, String[]> getParameterMap() {		// 修改getParameterMap内的编码
			Map<String, String[]>  parameterMap = this.getRequest().getParameterMap();
			if(parameterMap != null && !hasEncode) {
				for(Entry<String, String[]> entry : parameterMap.entrySet()) {
					String str[] = entry.getValue();
					for(int i = 0; i < str.length; i++) {
						try {
							str[i] = new String(str[i].getBytes("iso8859-1"), encode);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
				hasEncode = true;
			}
			//System.out.println(parameterMap == null);
			return parameterMap;
		}

		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
	}
}

